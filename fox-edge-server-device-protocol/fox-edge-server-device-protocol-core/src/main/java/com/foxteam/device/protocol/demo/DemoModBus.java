package com.foxteam.device.protocol.demo;

import com.foxteam.device.protocol.core.reference.ByteRef;
import com.foxteam.device.protocol.core.reference.BytesRef;
import com.foxteam.device.protocol.core.utils.HexUtils;

import java.util.HashMap;
import java.util.Map;

public class DemoModBus {
    public static void main(String[] args) {

        byte[] arrCmd = HexUtils.hexStringToByteArray("FEFEFE6812140006072068810653C3383639339A16");

        ByteRef byVer = new ByteRef();
        ByteRef byAddr = new ByteRef();
        ByteRef byCID1 = new ByteRef();
        ByteRef byCID2 = new ByteRef();
        BytesRef arrData = new BytesRef();
        BytesRef arrCmdRef = new BytesRef();
        Map<String, Object> value = new HashMap<String, Object>();
        arrData.setValue(new byte[4]);
        arrData.getValue()[0] = 0x00;
        arrData.getValue()[1] = 0x0A;
        arrData.getValue()[2] = 0x00;
        arrData.getValue()[3] = 0x50;
        //   ModBusRtuProtocol.packCmd((byte) 0x01, (byte) 0x03, arrData.getValue(), arrCmdRef);
        ByteRef byCmdRef = new ByteRef();
        // boolean s = ModBusOldProtocol.unPackCmdReadHoldingRegisters(arrCmd, value);

        String str1 = HexUtils.byteArrayToHexString(arrCmd);

        value.clear();


    }
}
