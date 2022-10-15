package com.foxteam.device.protocol.core.worker;

import com.foxteam.device.protocol.core.annotation.FoxEdgeOperate;
import com.foxteam.device.protocol.core.channel.FoxEdgeChannelService;
import com.foxteam.device.protocol.core.exception.ProtocolException;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateMethodEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateTemplateEntity;
import com.foxteam.device.protocol.core.method.FoxEdgeMethodTemplate;
import com.foxteam.device.protocol.core.method.FoxEdgeOperateMethod;

import javax.naming.CommunicationException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量操作工作者
 */
public class FoxEdgeBulkOperateWorker {
    /**
     * 模板类型的工作者函数：基于配置的模板，对设备进行操作
     * 参数填写：FoxEdgeControllerEntity.DeviceConfigEntity configTemplate
     * 步骤：
     * 1、从配置中获得操作模板列表
     * 2、根据操作模板列表对设备逐个进行下列操作
     * 2.1、根据模板操作项中的设备操作名称中，取出设备操作函数
     * 2.2、构造操作函数需要的参数，以模板公共参数为底，然后覆盖该设备自己的参数
     * 2.3、对设备进行操作，并解码，得到返回Map值
     * 2.4、将返回的Map追加到下一次的设备参数中
     * 2.4、累计设备的通信成功次数
     * 3、返回结果状态
     *
     * @param bulkOperateEntity 控制器参数实体
     */
    public static void workByTemplate(FoxEdgeBulkOperateTemplateEntity bulkOperateEntity) {
        FoxEdgeBulkOperateEntity.DeviceConfigEntity configEntity = bulkOperateEntity.getConfigEntity();
        FoxEdgeBulkOperateEntity.DeviceConfigEntity configTemplate = bulkOperateEntity.getConfigTemplate();
        FoxEdgeBulkOperateEntity.DeviceStatusEntity statusEntity = bulkOperateEntity.getStatusEntity();
        FoxEdgeBulkOperateEntity.DeviceEntity deviceEntity = bulkOperateEntity.getDeviceEntity();
        FoxEdgeChannelService channelService = bulkOperateEntity.getChannelService();

        // 检查：模板
        List<Object> templateList = (List<Object>) configTemplate.getParams().get(FoxEdgeBulkOperateEntity.DeviceConfigEntity.TEMPLATE_OPERATE_LIST_KEY);
        if (templateList == null) {
            return;
        }

        String deviceName = deviceEntity.getDeviceName();
        String deviceType = deviceEntity.getDeviceType();

        Map<String, Object> values = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        for (Object template : templateList) {
            try {
                // 组织参数
                Map<String, Object> param = (Map<String, Object>) template;
                params.putAll(param);
                params.putAll(configEntity.getParams());

                String operateName = (String) params.get(FoxEdgeBulkOperateEntity.DeviceConfigEntity.TEMPLATE_OPERATE_NAME_KEY);

                // 根据操作名称，获得对应的编码/解码函数
                Map<String, FoxEdgeOperateMethod> methodPairs = FoxEdgeMethodTemplate.inst().getSmplExchangeMethod().get(deviceType);
                FoxEdgeOperateMethod methodPair = methodPairs.get(operateName);
                if (methodPair == null) {
                    continue;
                }

                // 只有状态操作才可以进行批量操作
                if (!FoxEdgeOperate.status.equals(methodPair.getMode())) {
                    continue;
                }

                Object send;
                Object recv;

                // 编码
                send = methodPair.getEncoderMethod().invoke(null, params);

                try {
                    // 发送数据到设备
                    recv = channelService.exchange(deviceName, deviceType, send, methodPair.getTimeout());

                    // 通信正常
                    statusEntity.setCommFailedCount(0);
                    statusEntity.setCommSucessTime(System.currentTimeMillis());
                } catch (Exception e) {
                    // 通信异常
                    statusEntity.setCommFailedCount(statusEntity.getCommFailedCount() + 1);
                    statusEntity.setCommFailedTime(System.currentTimeMillis());
                    continue;
                }

                // 对返回数据进行解码
                Object data = methodPair.getDecoderMethod().invoke(null, recv, params);

                // 将结果数据，累积到参数中，下一个编码函数可能随使用到
                params.putAll((Map<String, Object>) data);
                values.putAll((Map<String, Object>) data);

                // 更新通信状态
                statusEntity.setCommFailedCount(0);
                statusEntity.setCommSucessTime(System.currentTimeMillis());
            } catch (InvocationTargetException ie) {
                // 通知外部异常:这种异常是函数内部抛出异常，但函数上并声明的异常，它真正的异常信息在TargetException
                bulkOperateEntity.setException(new ProtocolException("ProtocolException: " + ie.getTargetException().toString()));
                continue;
            } catch (Exception e) {
                // 通知外部异常
                bulkOperateEntity.setException(e);
                continue;
            }
        }

        Map<String, Object> result = bulkOperateEntity.getValueEntity().getValues();
        result.put(FoxEdgeOperate.status, values);
    }

    /**
     * 单步操作设备：参数来自FoxEdgeControllerEntity.OperateEntity
     *
     * @param bulkOperateEntity
     */
    public static void workByOneStep(FoxEdgeBulkOperateMethodEntity bulkOperateEntity) {
        FoxEdgeBulkOperateEntity.DeviceConfigEntity configEntity = bulkOperateEntity.getConfigEntity();
        FoxEdgeBulkOperateEntity.DeviceStatusEntity statusEntity = bulkOperateEntity.getStatusEntity();
        FoxEdgeBulkOperateEntity.DeviceEntity deviceEntity = bulkOperateEntity.getDeviceEntity();
        FoxEdgeChannelService channelService = bulkOperateEntity.getChannelService();
        FoxEdgeBulkOperateMethodEntity.OperateEntity operateEntity = bulkOperateEntity.getOperateEntity();

        String deviceName = deviceEntity.getDeviceName();
        String deviceType = deviceEntity.getDeviceType();

        Map<String, Object> values = new HashMap<>();
        Map<String, Object> params = operateEntity.getParams();
        int timeout = operateEntity.getTimeout();


        try {
            // 组织参数
            params.putAll(configEntity.getParams());

            String operateName = operateEntity.getOperateName();

            Map<String, Object> data = FoxEdgeOperateWorker.exchange(deviceName, deviceType, operateName, params, timeout, channelService);

            // 通信正常
            statusEntity.setCommFailedCount(0);
            statusEntity.setCommSucessTime(System.currentTimeMillis());

            // 将结果数据，累积到参数中，下一个编码函数可能随使用到
            params.putAll(data);
            values.putAll(data);
        } catch (CommunicationException ce) {
            // 通信异常
            statusEntity.setCommFailedCount(statusEntity.getCommFailedCount() + 1);
            statusEntity.setCommFailedTime(System.currentTimeMillis());
        } catch (Exception e) {
            bulkOperateEntity.setException(e);
        }

        // 将状态组合起来
        Map<String, Object> result = bulkOperateEntity.getValueEntity().getValues();
        appendResult(values, result);
    }

    private static void appendResult(Map<String, Object> values, Map<String, Object> result) {
        if (result.isEmpty()) {
            result.putAll(values);
        } else {
            Map<String, Object> status = (Map<String, Object>) result.get(FoxEdgeOperate.status);
            if (status == null) {
                status = new HashMap<>();
                result.put(FoxEdgeOperate.status, status);
            }

            Map<String, Object> temp = (Map<String, Object>) values.get(FoxEdgeOperate.status);
            if (temp != null) {
                status.putAll(temp);
            }
        }
    }
}
