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

package cn.foxtech.device.protocol.v1.cjt188.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 仪表类型
 * 广播类型: AA
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class CJT188Type {
    private int value = 0;

    public String getName() {
        if (value == (0xAA & 0xff)) {
            return "广播类型:AA";
        }

        if (value == (0x10 & 0xff)) {
            return "冷水水表";
        }
        if (value == (0x11 & 0xff)) {
            return "热水水表";
        }
        if (value == (0x12 & 0xff)) {
            return "直饮水水表";
        }
        if (value == (0x20 & 0xff)) {
            return "热量表 (记热量)";
        }
        if (value == (0x21 & 0xff)) {
            return "热量表 (记冷量)";
        }
        if (value == (0x10 & 0xff)) {
            return "冷水水表";
        }
        if (value == (0x30 & 0xff)) {
            return "燃气表";
        }
        if (value == (0x40 & 0xff)) {
            return "电度表";
        }
        return "未知类型:" + value;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
