package com.foxteam.device.protocol.demo;

import com.foxteam.device.protocol.core.protocol.modbus.*;
import com.foxteam.device.protocol.core.reference.ByteRef;
import com.foxteam.device.protocol.core.reference.BytesRef;
import com.foxteam.device.protocol.core.reference.ShortRef;
import com.foxteam.device.protocol.core.utils.HexUtils;

import java.util.HashMap;
import java.util.Map;

public class DemoModBusTcp {
    public static void main(String[] args) {

        ModBusTcpProtocol protocol = new ModBusTcpProtocol();

        byte[] arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 0d 01 03 0a 00 05 00 06 00 07 00 08 00 09");
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 03 01 83 02");
        ModBusReadRegistersRespond respond = protocol.unPackCmdReadHoldingRegisters2Respond(arrCmd);

        ModBusReadStatusRespond respond1 = new ModBusReadStatusRespond();
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 06 01 01 03 3d 10 0a ");
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 03 01 81 02");
        respond1 = protocol.unPackCmdReadCoilStatus2Respond(arrCmd);

        ModBusReadStatusRespond respond2 = new ModBusReadStatusRespond();
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 06 01 02 03 0b 00 08 ");
        //     arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 03 01 81 02");
        respond2 = protocol.unPackCmdReadInputStatus2Respond(arrCmd);

        ModBusWriteStatusRespond respond3 = new ModBusWriteStatusRespond();
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 06 01 05 00 03 FF 00 ");
        respond3 = protocol.unPackCmdWriteStatus2Respond(arrCmd);

        ModBusWriteRegistersRespond respond4 = new ModBusWriteRegistersRespond();
        arrCmd = HexUtils.hexStringToByteArray("19 b2 00 00 00 06 01 06 00 01 01 0a");
        respond4 = protocol.unPackCmdWriteHoldingRegisters2Respond(arrCmd);


        ShortRef wSn = new ShortRef();
        ByteRef byVer = new ByteRef();
        ByteRef byAddr = new ByteRef();
        ByteRef byFun = new ByteRef();
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


        //   ModBusTcpProtocol.unPackCmd(arrCmd,wSn,byAddr,byFun,arrData);
        //   ModBusTcpProtocol.packCmd(wSn.getValue(),byAddr.getValue(),byFun.getValue(),arrData.getValue(),arrCmdRef);
        //     ModBusTcpProtocol.packCmd((byte) 0x01, (byte) 0x03, arrData.getValue(), arrCmdRef, ModBusProtocol.MODE_RTU);
        //    ByteRef byCmdRef = new ByteRef();
        //   boolean s = ModBusProtocol.unPackCmdReadHoldingRegisters(arrCmd, value);

        String str1 = HexUtils.byteArrayToHexString(arrCmdRef.getValue());

        value.clear();


    }
}
