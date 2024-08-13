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

import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;

public class TimeUtil {
    public static String decodeTime6byte(byte[] data, int offset) {
        StringBuilder sb = new StringBuilder();
        sb.append("20");
        sb.append(String.format("%02d", data[offset + 5]));
        sb.append("-");
        sb.append(String.format("%02d", data[offset + 4]));
        sb.append("-");
        sb.append(String.format("%02d", data[offset + 3]));
        sb.append(" ");
        sb.append(String.format("%02d", data[offset + 2]));
        sb.append(":");
        sb.append(String.format("%02d", data[offset + 1]));
        sb.append(":");
        sb.append(String.format("%02d", data[offset + 0]));

        return sb.toString();
    }

    public static void encodeTime6byte(String dateTime, byte[] data, int offset) {
        if (dateTime.isEmpty()) {
            throw new ProtocolException("时间格式不合法，正确的范例:2023-12-28 18:14:57");
        }

        String[] item = dateTime.split(" ");
        if (item.length != 2) {
            return;
        }

        String date = item[0];
        String time = item[1];

        item = time.split(":");
        if (item.length != 3) {
            return;
        }

        data[offset + 0] = (byte) Integer.parseInt(item[2]);
        data[offset + 1] = (byte) Integer.parseInt(item[1]);
        data[offset + 2] = (byte) Integer.parseInt(item[0]);

        item = date.split("-");
        if (item.length != 3) {
            return;
        }

        data[offset + 3] = (byte) Integer.parseInt(item[2]);
        data[offset + 4] = (byte) Integer.parseInt(item[1]);
        data[offset + 5] = (byte) (Integer.parseInt(item[0]) % 100);
    }
}
