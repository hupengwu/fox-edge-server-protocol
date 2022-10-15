package com.foxteam.device.protocol.mitsubishi.plc.fx;

import com.foxteam.device.protocol.core.utils.HexUtils;
import com.foxteam.device.protocol.mitsubishi.plc.fx.entity.MitsubishiPlcFxDeviceReadEntity;
import com.foxteam.device.protocol.mitsubishi.plc.fx.entity.MitsubishiPlcFxDeviceWriteEntity;
import com.foxteam.device.protocol.mitsubishi.plc.fx.entity.MitsubishiPlcFxEntity;
import com.foxteam.device.protocol.mitsubishi.plc.fx.entity.MitsubishiPlcFxForceOnEntity;
import com.foxteam.device.protocol.mitsubishi.plc.fx.frame.MitsubishiPlcFxProtocolFrame;

public class Demo {
    public static void main(String[] args) {
        try {

            // 读取数据
            MitsubishiPlcFxDeviceReadEntity readEntity = new MitsubishiPlcFxDeviceReadEntity();
            readEntity.setTarget(MitsubishiPlcFxEntity.TAR_D);
            readEntity.setAddress(123);
            readEntity.setCount(4);
            String hexString = HexUtils.byteArrayToHexString(MitsubishiPlcFxProtocolFrame.encodePack(readEntity));
            MitsubishiPlcFxProtocolFrame.decodePack(HexUtils.hexStringToByteArray("02 33 34 31 32 43 44 41 42 03 44 37 "), readEntity);

            MitsubishiPlcFxDeviceWriteEntity writeEntity = new MitsubishiPlcFxDeviceWriteEntity();
            writeEntity.setTarget(MitsubishiPlcFxEntity.TAR_D);
            writeEntity.setAddress(123);
            writeEntity.setData("1234ABCD");
            hexString = HexUtils.byteArrayToHexString(MitsubishiPlcFxProtocolFrame.encodePack(writeEntity));
            MitsubishiPlcFxProtocolFrame.decodePack(HexUtils.hexStringToByteArray("15"), writeEntity);


            MitsubishiPlcFxForceOnEntity forceOnEntity = new MitsubishiPlcFxForceOnEntity();
            forceOnEntity.setTarget(MitsubishiPlcFxEntity.TAR_C);
            forceOnEntity.setAddress(123);
            hexString = HexUtils.byteArrayToHexString(MitsubishiPlcFxProtocolFrame.encodePack(forceOnEntity));
            MitsubishiPlcFxProtocolFrame.decodePack(HexUtils.hexStringToByteArray("15 "), forceOnEntity);


            hexString = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
