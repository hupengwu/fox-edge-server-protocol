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

package cn.foxtech.device.protocol.v1.hikvision.fire.core.utils;

public class VersionUtil {
    public static String decodeVersion2byte(byte[] data, int offset) {
        return (data[offset + 0] & 0xff) + "." + String.format("%02d",data[offset + 1] & 0xff);
    }

    public static void encodeVersion2byte(String version, byte[] data, int offset) {
        if (version.isEmpty()) {
            return;
        }
        String[] item = version.split("\\.");
        if (item.length != 2){
            return;
        }

        data[offset + 0] = (byte) Integer.parseInt(item[0]);
        data[offset + 1] = (byte) Integer.parseInt(item[1]);
    }
}
