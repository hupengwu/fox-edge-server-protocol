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

package cn.foxtech.device.protocol.v1.iec104.core.encoder;

import cn.foxtech.device.protocol.v1.iec104.core.entity.CotEntity;

public class CotEncoder {
    public static CotEntity decodeCot(int value) {
        CotEntity entity = new CotEntity();
        entity.setTest((value & 0x0080) != 0x00);
        entity.setPn((value & 0x0040) == 0x00);
        entity.setReason(value & 0x003f);
        entity.setAddr(value >> 8);
        return entity;
    }

    public static int encodeCot(CotEntity entity) {
        int result = entity.getReason();
        if (entity.isTest()) {
            result |= 0x0080;
        }
        if (!entity.isPn()) {
            result |= 0x0040;
        }
        result |= entity.getAddr() << 8;

        return result;
    }
}
