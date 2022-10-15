package com.foxteam.device.protocol.core.worker;

import com.foxteam.device.protocol.core.annotation.FoxEdgeBulkOperate;
import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.method.FoxEdgeMethodTemplate;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateMethodEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateTemplateEntity;
import com.foxteam.device.protocol.core.method.FoxEdgeOperateMethod;

import java.util.Map;

@FoxEdgeDeviceType(value = "FoxEdge Default Device", manufacturer = "Fox Edge")
public class FoxEdgeBulkDefaultWorker {
    public static final String DEFAULT_DEVICE_TYPE = "FoxEdge Default Device";

    @FoxEdgeBulkOperate(name = "默认的批量读取数据操作")
    public static void workThread(FoxEdgeBulkOperateEntity operateEntity) {
        if (operateEntity instanceof FoxEdgeBulkOperateTemplateEntity) {
            // 按模板定义的流程进行批量操作
            FoxEdgeBulkOperateWorker.workByTemplate((FoxEdgeBulkOperateTemplateEntity) operateEntity);
        }
        if (operateEntity instanceof FoxEdgeBulkOperateMethodEntity) {
            // 对注解上标识了isPolling的解码器函数进行逐个操作
            workBySteps((FoxEdgeBulkOperateMethodEntity) operateEntity);
        }
    }

    /**
     * 对标识了isPolling的解码器进行逐个遍历操作
     *
     * @param operateEntity 控制器参数实体
     */
    private static void workBySteps(FoxEdgeBulkOperateMethodEntity operateEntity) {
        // 准备参数和清空数据
        operateEntity.getOperateEntity().getParams().putAll(operateEntity.getConfigEntity().getParams());
        operateEntity.getValueEntity().getValues().clear();

        // 取出该设备类型对应的解码器列表
        String deviceType = operateEntity.getDeviceEntity().getDeviceType();
        Map<String, FoxEdgeOperateMethod> methodPairs = FoxEdgeMethodTemplate.inst().getSmplExchangeMethod().get(deviceType);
        if (methodPairs == null) {
            return;
        }

        for (Map.Entry<String, FoxEdgeOperateMethod> entry : methodPairs.entrySet()) {
            String operateName = entry.getKey();
            FoxEdgeOperateMethod methodPair = entry.getValue();

            // 是否是轮询操作
            if (!methodPair.isPolling()) {
                continue;
            }

            // 操作和对象名称
            operateEntity.getOperateEntity().setOperateName(operateName);
            FoxEdgeBulkOperateWorker.workByOneStep(operateEntity);
        }
    }
}
