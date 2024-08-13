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

import cn.foxtech.device.protocol.v1.iec104.core.entity.VsqEntity;

public class VsqEncoder {
    public static VsqEntity decodeVsq(int value) {
        VsqEntity entity = new VsqEntity();
        entity.setSq((value & 0x80) != 0x00);
        entity.setNum(value & 0x7f);
        return entity;
    }

    public static int encodeVsq(VsqEntity entity) {
        int result = entity.getNum();
        if (entity.isSq() == true) {
            result |= 0x80;
        }

        return result;
    }
}
