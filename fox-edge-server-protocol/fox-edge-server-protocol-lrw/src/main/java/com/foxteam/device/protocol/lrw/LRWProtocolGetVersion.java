package com.foxteam.device.protocol.lrw;

import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.annotation.FoxEdgeOperate;
import com.foxteam.device.protocol.core.exception.ProtocolException;
import com.foxteam.device.protocol.core.utils.HexUtils;

import java.util.HashMap;
import java.util.Map;

@FoxEdgeDeviceType(value = "LRW解码器")
public class LRWProtocolGetVersion extends LRWProtocolFrame {
    @FoxEdgeOperate(name = "检查版本号", polling = true, type = FoxEdgeOperate.encoder)
    public static String encodePackVersion(Map<String, Object> param) {
        return HexUtils.byteArrayToHexString(encodePack((byte) 0x01));
    }

    @FoxEdgeOperate(name = "检查版本号", polling = true, type = FoxEdgeOperate.decoder)
    public static Map<String, Object> decodePackVersion(String hexString, Map<String, Object> param) {
        byte[] pack = HexUtils.hexStringToByteArray(hexString);

        // 解码报文
        LRWEntity entity = decodePack(pack);
        if (entity == null) {
            throw new ProtocolException("报文格式不正确！");
        }

        if (entity.getCmd() != (byte) 0x81) {
            throw new ProtocolException("设备拒绝！");
        }

        // 解码数据
        Map<String, Object> value = new HashMap<>();
        byte[] dat = entity.getDat();
        value.put("verSoft", dat[0]);
        value.put("verHard", dat[1]);
        value.put("verProt", dat[2]);

        return value;
    }
}