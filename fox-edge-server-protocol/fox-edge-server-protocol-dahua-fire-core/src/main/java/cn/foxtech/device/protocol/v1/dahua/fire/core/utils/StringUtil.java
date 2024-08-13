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

package cn.foxtech.device.protocol.v1.dahua.fire.core.utils;

public class StringUtil {
    public static String truncateString(String str, int length) {
        if (str.length() == length) {
            return str;
        }

        // IMEI(8 字节):BCD格式
        StringBuilder sb = new StringBuilder();
        if (str.length() < length) {
            for (int i = 0; i < length - str.length(); i++) {
                sb.append("0");
            }
            sb.append(str);
        }
        if (str.length() >= length) {
            for (int i = 0; i < str.length(); i++) {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }
}
