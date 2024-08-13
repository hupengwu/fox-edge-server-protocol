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


import cn.foxtech.device.protocol.v1.telecom.core.entity.PduEntity;
import cn.foxtech.device.protocol.v1.utils.HexUtils;


public class TestTelecomProtocol {
    public static void main(String[] args) {
        byte[] pdu = HexUtils.hexStringToByteArray("7e 31 30 30 31 36 30 34 46 30 30 30 30 46 44 39 45 0d ");

        PduEntity entity = PduEntity.decodePdu(pdu);


        pdu = HexUtils.hexStringToByteArray("7E31303031383430303330314330303334463242463442323030373033313331323538333830303030463744330D ");

        entity = PduEntity.decodePdu(pdu);

        pdu = PduEntity.encodePdu(entity);

        String txt = HexUtils.byteArrayToHexString(pdu).toUpperCase();

       // PduEntity.getUnPackCmdVfyCode()

    }

}
