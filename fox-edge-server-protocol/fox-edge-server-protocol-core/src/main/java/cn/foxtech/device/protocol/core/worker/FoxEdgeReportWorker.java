package cn.foxtech.device.protocol.core.worker;

import cn.foxtech.device.protocol.core.annotation.FoxEdgeOperate;
import cn.foxtech.device.protocol.core.method.FoxEdgeMethodTemplate;
import cn.foxtech.device.protocol.core.method.FoxEdgeReportMethod;
import cn.foxtech.device.protocol.core.constants.FoxEdgeConstant;
import cn.foxtech.device.protocol.core.exception.ProtocolException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoxEdgeReportWorker {
    /**
     * 对数据进行分析解码
     *
     * @param deviceType 设备类型
     * @param recv       待解码的上报数据
     * @param params     设备的配置参数
     * @return 数据
     * @throws ProtocolException 解码失败
     */
    public static Map<String, Object> decode(String deviceType, Object recv, Map<String, Object> params) throws ProtocolException {
        // 根据设备类型查找解码器集合
        FoxEdgeMethodTemplate template = FoxEdgeMethodTemplate.inst();
        Map<String, FoxEdgeReportMethod> methodPairs = template.getReportMethod().get(deviceType);
        if (methodPairs == null) {
            throw new ProtocolException("找不到对应设备类型的解码器：" + deviceType);
        }

        for (Map.Entry<String, FoxEdgeReportMethod> entry : methodPairs.entrySet()) {
            FoxEdgeReportMethod methodPair = entry.getValue();

            try {
                // 将解码结果，根据模式，用各自的字段带回
                if (FoxEdgeOperate.record.equals(methodPair.getMode())) {
                    // 记录格式
                    List<Map<String, Object>> values = (List<Map<String, Object>>) methodPair.getDecoderMethod().invoke(null, recv, params);

                    Map<String, Object> recordValue = new HashMap<>();
                    recordValue.put(FoxEdgeOperate.record, values);

                    Map<String, Object> result = new HashMap<>();
                    result.put(FoxEdgeConstant.OPERATE_NAME_TAG, methodPair.getName());
                    result.put(FoxEdgeConstant.DATA_TAG, recordValue);

                    return result;
                } else {
                    // 状态格式
                    Map<String, Object> values = (Map<String, Object>) methodPair.getDecoderMethod().invoke(null, recv, params);

                    Map<String, Object> statusValue = new HashMap<>();
                    statusValue.put(FoxEdgeOperate.status, values);

                    Map<String, Object> result = new HashMap<>();
                    result.put(FoxEdgeConstant.OPERATE_NAME_TAG, methodPair.getName());
                    result.put(FoxEdgeConstant.DATA_TAG, statusValue);
                    return result;
                }
            } catch (Exception e) {
                continue;
            }
        }

        throw new ProtocolException("找不到对应设备类型的解码器：" + deviceType);
    }
}
