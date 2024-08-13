/* ----------------------------------------------------------------------------
 * Copyright (c) Guangzhou Fox-Tech Co., Ltd. 2020-2024. All rights reserved.
 *
 *     This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * --------------------------------------------------------------------------- */

package cn.foxtech.device.protocol.v1.test;


import cn.foxtech.device.protocol.v1.modbus.core.*;
import cn.foxtech.device.protocol.v1.utils.Crc16Utils;
import cn.foxtech.device.protocol.v1.utils.HexUtils;
import cn.foxtech.device.protocol.v1.utils.enums.CrcType;

public class TestRTU {
    public static void main(String[] args) {
        byte[] pdu = HexUtils.hexStringToByteArray("01 04 0c 01 1a 01 ba 41 e1 fa 9c 42 30 ed cb ef ca");

        int crc = Crc16Utils.getCRC16(pdu, 0, pdu.length - 2, CrcType.CRC16MODBUS);


        byte[] crcs = new byte[2];
        crcs[0] = (byte) (crc >> 8 & 0xff);
        crcs[1] = (byte) (crc >> 0 & 0xff);

        pdu = HexUtils.hexStringToByteArray("01 03 10 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 E4 59");

        pdu = HexUtils.hexStringToByteArray("01 03 00 06 45 21 45 23 45 34 36 21");

        ModBusProtocol modBusProtocol = ModBusProtocolFactory.createProtocol(ModBusConstants.MODE_RTU);
        ModBusEntity modBusEntity = modBusProtocol.unPackCmd2Entity(pdu);
        pdu = modBusProtocol.packCmd4Entity(modBusEntity);

        ModBusReadRegistersRequest request = new ModBusReadRegistersRequest();
        pdu = modBusProtocol.packCmdReadRegisters4Request(request);

        System.out.println(HexUtils.byteArrayToHexString(pdu));

        modBusEntity = modBusProtocol.unPackCmd2Entity(HexUtils.hexStringToByteArray("01030443556680D5A7"));

        ModBusReadRegistersRespond respond = modBusProtocol.unPackCmdReadRegisters2Respond(HexUtils.hexStringToByteArray("01030443556680D5A7"));


    }


}
