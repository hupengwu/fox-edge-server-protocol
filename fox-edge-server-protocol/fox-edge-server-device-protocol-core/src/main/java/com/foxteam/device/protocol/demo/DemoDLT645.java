package com.foxteam.device.protocol.demo;

import com.foxteam.device.protocol.core.protocol.dlt645.DLT645Define;
import com.foxteam.device.protocol.core.protocol.dlt645.DLT645Protocol;
import com.foxteam.device.protocol.core.protocol.dlt645.entity.DLT645FunEntity;
import com.foxteam.device.protocol.core.reference.ByteRef;
import com.foxteam.device.protocol.core.reference.BytesRef;
import com.foxteam.device.protocol.core.utils.HexUtils;

import java.util.HashMap;
import java.util.Map;

public class DemoDLT645 {
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
        ByteRef byCmdRef = new ByteRef();
        //    boolean s = DLT645Protocol.unPackCmd2Map(arrCmd, byCmdRef, arrData);

        arrCmd = HexUtils.hexStringToByteArray("68 01 00 00 00 00 00 68 11 04 33 33 34 33 B3 16");
        Map<String, Object> result = DLT645Protocol.unPackCmd2Map(arrCmd);
        Object func = result.get(DLT645Protocol.FUN);
        DLT645FunEntity entity = DLT645FunEntity.decodeEntity((byte) func);
        String msg = entity.getMessage("v07");
        byte[] data = (byte[]) result.get(DLT645Protocol.DAT);
        for (byte by : data) {
            int v = ((by & 0xff) - 0x33);
            v = 0;
        }

        arrCmd = HexUtils.hexStringToByteArray("FE FE FE FE 68 01 00 00 00 00 00 68 91 09 33 33 34 33 A6 5C 33 33 34 D4 16  ");
        result = DLT645Protocol.unPackCmd2Map(arrCmd);
        func = result.get(DLT645Protocol.FUN);
        entity = DLT645FunEntity.decodeEntity((byte) func);
        msg = entity.getMessage("v07");
        data = (byte[]) result.get(DLT645Protocol.DAT);
//        for (int i = 0; i < data.length; i++) {
//            int v = ((int)(data[i] & 0xff)) - 0x33;
//            data[i] = (byte) v;
//        }

//        DLT645DataID1997 DLT645DataID1997 = new DLT645DataID1997();
//        DLT645DataEntity dataEntity = new DLT645DataEntity();
//        dataEntity.unPackData(data, DLT645DataUtil.format_4byte_double);


        byte test = -90;
        int v = test & 0xff;
        v = (test & 0xff) - 0x33;

        arrCmd = HexUtils.hexStringToByteArray("FE FE FE FE 68 01 00 00 00 00 00 68 91 09 33 33 34 33 45 33 33 33 34 4A 16");
        result = DLT645Protocol.unPackCmd2Map(arrCmd);
        func = result.get(DLT645Protocol.FUN);
        entity = DLT645FunEntity.decodeEntity((byte) func);
        msg = entity.getMessage(DLT645Define.PRO_VER_2007);
        data = (byte[]) result.get(DLT645Protocol.DAT);

        arrCmd = HexUtils.hexStringToByteArray("FE FE FE FE 68 18 20 12 22 20 65 68 11 04 33 32 34 35 A4 16");
        result = DLT645Protocol.unPackCmd2Map(arrCmd);
        func = result.get(DLT645Protocol.FUN);
        entity = DLT645FunEntity.decodeEntity((byte) func);
        msg = entity.getMessage("v07");
        data = (byte[]) result.get(DLT645Protocol.DAT);

    }

    public static byte[] unPackData(byte[] data) {
        byte[] result = new byte[data.length];
        // 每个字节先减去0x33
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) ((data[i] & 0xff) - 0x33);
        }


        return result;
    }
}
