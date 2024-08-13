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

import cn.foxtech.device.protocol.v1.haier.ycj.a002.entity.PduEntity;
import cn.foxtech.device.protocol.v1.utils.HexUtils;

public class Test {
    public static void main(String[] args) {
        sysStatus();

    }

    public static void sysStatus() {
        PduEntity entity = new PduEntity();
        entity.setHostAddr(1);
        entity.setDevAddr(2);
        byte[] pdu = PduEntity.encodePdu(entity);

        String text = HexUtils.byteArrayToHexString(pdu, true);

        entity = PduEntity.decodePdu(pdu);
        text = "";

    }


}
