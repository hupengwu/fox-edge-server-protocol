package com.foxteam.device.protocol;

import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.annotation.FoxEdgeOperate;
import com.foxteam.device.protocol.core.annotation.FoxEdgePublish;
import com.foxteam.device.protocol.core.utils.HexUtils;

import java.util.Map;

@FoxEdgeDeviceType(value = "刘日威解码器")
public class LRWProtocolBrocast extends LRWProtocolFrame {
    /**
     * 查询传感器状态
     *
     * @param param 输入参数
     * @return 操作是否成功
     */
    @FoxEdgePublish
    @FoxEdgeOperate(name = "广播地址", polling = true, type = FoxEdgeOperate.encoder)
    public static String encodePackSensor(Map<String, Object> param) {
        return HexUtils.byteArrayToHexString(encodePack((byte) 0x02));
    }
}
