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

public class AddressUtil {
    public static final String PLATFORM_DEFAULT = "FFFFFFFFFFFF";
    public static final String DEVICE_DEFAULT = "000000000000";

    public static String decodeAddress6byte(byte[] data, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            byte dat = data[offset + i];

            sb.append(getChar((byte) ((dat & 0xf0) >> 4)));
            sb.append(getChar((byte) (dat & 0x0F)));
        }

        return sb.toString();
    }

    public static void encodeAddress6byte(String address, byte[] data, int offset) {
        int i = 0;
        int length = 6;

        // 不足6字节长度
        if (address.length() / 2 < length) {
            for (; i < length - address.length() / 2; i++) {
                data[i + offset] = 0;
            }
        }

        for (; i < length; i++) {
            int h = getInt(address.charAt(2 * i + 0));
            int l = getInt(address.charAt(2 * i + 1));
            data[i + offset] = (byte) (h * 0x10 + l);
        }
    }

    private static char getChar(byte c) {
        if (0 <= c && c <= 9) {
            return (char)(c + '0');
        }
        if (0x0a <= c && c <= 0x0f) {
            return (char)(c - 10 + 'A');
        }

        return '0';
    }

    private static int getInt(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        if ('a' <= c && c <= 'f') {
            return c - 'a' + 10;
        }
        if ('A' <= c && c <= 'F') {
            return c - 'A' + 10;
        }

        return 0;
    }
}
