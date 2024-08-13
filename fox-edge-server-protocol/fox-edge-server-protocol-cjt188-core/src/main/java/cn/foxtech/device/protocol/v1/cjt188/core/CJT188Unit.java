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

public class CJT188Unit {
    public static String getUnit(int value) {
        if (value == 0x2) return "Wh";
        if (value == 0x5) return "KWh";
        if (value == 0x8) return "MWh";
        if (value == 0xA) return "MWh * 100";
        if (value == 0x1) return "J";
        if (value == 0xB) return "KJ";
        if (value == 0xE) return "MJ";
        if (value == 0x11) return "GJ";
        if (value == 0x13) return "GJ * 100";
        if (value == 0x14) return "W";
        if (value == 0x17) return "KW";
        if (value == 0x1A) return "MW";
        if (value == 0x29) return "L";
        if (value == 0x2C) return "m3";
        if (value == 0x32) return "L/h";
        if (value == 0x35) return "m3/h";

        return "未定义的单位";
    }
}
