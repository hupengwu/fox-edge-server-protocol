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

package cn.foxtech.device.protocol.v1.mitsubishi.plc.fx.entity;

import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class MitsubishiPlcFxForceOnEntity extends MitsubishiPlcFxEntity {
    /**
     * 返回：ACK/NAK
     */
    private String result = "";

    @Override
    public int encodeAddress() {
        String target = this.getTarget();
        int address = this.getAddress();

        if (address >= 0 && address < 1024) {
            if ("S".equals(target)) {
                return address * 1 + 0x0000;
            } else if ("X".equals(target)) {
                return address * 2 + 0x0400;
            } else if ("Y".equals(target)) {
                return address * 1 + 0x0500;
            } else if ("T".equals(target)) {
                return address * 1 + 0x0600;
            } else if ("M".equals(target)) {
                return address * 1 + 0x0800;
            } else if ("C".equals(target)) {
                return address * 1 + 0x0E00;
            } else {
                throw new ProtocolException("Target类型不支持!");
            }
        } else if (address >= 8000 && address < 8512) {
            return (address - 8000) * 1 + 0x6000;
        } else {
            throw new ProtocolException("地址范围不支持!");
        }
    }

}
