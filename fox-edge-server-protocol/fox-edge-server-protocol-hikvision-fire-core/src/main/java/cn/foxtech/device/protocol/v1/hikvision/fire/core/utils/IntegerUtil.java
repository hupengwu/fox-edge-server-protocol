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

public class IntegerUtil {
    public static int decodeInteger2byte(byte[] data, int offset) {
        int l = data[offset + 0] & 0xff;
        int h = data[offset + 1] & 0xff;
        return h * 0x100 + l;
    }

    public static void encodeInteger2byte(int value, byte[] data, int offset) {
        data[offset + 0] = (byte) (value % 0x100);
        data[offset + 1] = (byte) (value / 0x100);
    }

    public static long decodeLong4byte(byte[] data, int offset) {
        long value = 0;
        value = (value << 0) + data[offset + 3] & 0xff;
        value = (value << 8) + data[offset + 2] & 0xff;
        value = (value << 16) + data[offset + 1] & 0xff;
        value = (value << 24) + data[offset + 0] & 0xff;
        return value;
    }

    public static void encodeLong4byte(long value, byte[] data, int offset) {
        data[offset + 0] = (byte) ((value >> 0) & 0xff);
        data[offset + 1] = (byte) ((value >> 8) & 0xff);
        data[offset + 2] = (byte) ((value >> 16) & 0xff);
        data[offset + 3] = (byte) ((value >> 24) & 0xff);
    }

    public static long decodeLong8byte(byte[] data, int offset) {
        long value = 0;
        value = (value << 0) + data[offset + 7] & 0xff;
        value = (value << 8) + data[offset + 6] & 0xff;
        value = (value << 16) + data[offset + 5] & 0xff;
        value = (value << 24) + data[offset + 4] & 0xff;
        value = (value << 32) + data[offset + 3] & 0xff;
        value = (value << 40) + data[offset + 2] & 0xff;
        value = (value << 48) + data[offset + 1] & 0xff;
        value = (value << 56) + data[offset + 0] & 0xff;
        return value;
    }

    public static void encodeLong8byte(long value, byte[] data, int offset) {
        data[offset + 0] = (byte) ((value >> 0) & 0xff);
        data[offset + 1] = (byte) ((value >> 8) & 0xff);
        data[offset + 2] = (byte) ((value >> 16) & 0xff);
        data[offset + 3] = (byte) ((value >> 24) & 0xff);
        data[offset + 4] = (byte) ((value >> 32) & 0xff);
        data[offset + 5] = (byte) ((value >> 40) & 0xff);
        data[offset + 6] = (byte) ((value >> 48) & 0xff);
        data[offset + 7] = (byte) ((value >> 56) & 0xff);
    }
}
