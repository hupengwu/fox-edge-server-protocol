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

package cn.foxtech.device.protocol.v1.utils;

public class ByteUtils {
    public static void encodeInt32(long value, byte[] data, int offset, boolean msbFirst) {
        if (msbFirst) {
            data[offset + 3] = (byte) ((value >> 0) & 0xff);
            data[offset + 2] = (byte) ((value >> 8) & 0xff);
            data[offset + 1] = (byte) ((value >> 16) & 0xff);
            data[offset + 0] = (byte) ((value >> 24) & 0xff);
        } else {
            data[offset + 0] = (byte) ((value >> 0) & 0xff);
            data[offset + 1] = (byte) ((value >> 8) & 0xff);
            data[offset + 2] = (byte) ((value >> 16) & 0xff);
            data[offset + 3] = (byte) ((value >> 24) & 0xff);
        }
    }

    public static void encodeInt16(int value, byte[] data, int offset, boolean msbFirst) {
        if (msbFirst) {
            data[offset + 0] = (byte) ((value >> 8) & 0xff);
            data[offset + 1] = (byte) (value & 0xff);
        } else {
            data[offset + 1] = (byte) ((value >> 8) & 0xff);
            data[offset + 0] = (byte) (value & 0xff);
        }
    }

    /**
     * 对16位长度byte[]，进行int解码
     *
     * @param data     数组
     * @param offset   偏移量
     * @param msbFirst 高位在前
     * @return 数值
     */
    public static int decodeInt16(byte[] data, int offset, boolean msbFirst) {
        if (msbFirst) {
            int value = 0;
            value += (data[offset + 0] & 0xff) * 0x100;
            value += (data[offset + 1] & 0xff) * 0x1;
            return value;
        } else {
            int value = 0;
            value += (data[offset + 1] & 0xff) * 0x100;
            value += (data[offset + 0] & 0xff) * 0x1;
            return value;
        }
    }

    /**
     * 对16位长度byte[]，进行int解码
     *
     * @param data     数组
     * @param msbFirst 高位在前
     * @return 数值
     */
    public static int decodeInt16(byte[] data, boolean msbFirst) {
        return decodeInt16(data, 0, msbFirst);
    }

    /**
     * 对16位长度byte[]，进行int解码
     *
     * @param data 数组
     * @return 数值
     */
    public static int decodeInt16(byte[] data) {
        return decodeInt16(data, true);
    }

    /**
     * 对32位长度byte[]，进行int解码
     *
     * @param data     数组
     * @param offset   偏移量
     * @param msbFirst 高位在前
     * @return 数值
     */
    public static long decodeInt32(byte[] data, int offset, boolean msbFirst) {
        if (msbFirst) {
            long value = 0;
            value += (data[offset + 0] & 0xff) * 0x1000000L;
            value += (data[offset + 1] & 0xff) * 0x10000L;
            value += (data[offset + 2] & 0xff) * 0x100L;
            value += (data[offset + 3] & 0xff) * 0x1L;
            return value;
        } else {
            long value = 0;
            value += (data[offset + 3] & 0xff) * 0x1000000L;
            value += (data[offset + 2] & 0xff) * 0x10000L;
            value += (data[offset + 1] & 0xff) * 0x100L;
            value += (data[offset + 0] & 0xff) * 0x1L;
            return value;
        }
    }

    /**
     * 对32位长度byte[]，进行int解码
     *
     * @param data     数组
     * @param msbFirst 高位在前
     * @return 数值
     */
    public static long decodeInt32(byte[] data, boolean msbFirst) {
        return decodeInt32(data, 0, msbFirst);
    }

    /**
     * 对32位长度byte[]，进行int解码
     *
     * @param data 数组
     * @return 数值
     */
    public static long decodeInt32(byte[] data) {
        return decodeInt32(data, 0, true);
    }

    /**
     * ASCII字符串的解码
     *
     * @param data     数据块
     * @param offset   起始位置
     * @param length   长度
     * @param msbFirst 是否高位在前
     * @return 字符串
     */
    public static String decodeAscii(byte[] data, int offset, int length, boolean msbFirst) {
        if (msbFirst) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                byte by = data[i + offset];
                if (by == 0) {
                    break;
                }
                sb.append((char) by);
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                byte by = data[i + length + offset - 1];
                if (by == 0) {
                    break;
                }

                sb.append((char) by);
            }
            return sb.toString();
        }
    }

    public static void encodeAscii(String str, byte[] data, int offset, int length, boolean msbFirst) {
        if (msbFirst) {
            for (int i = 0; i < str.length(); i++) {
                data[i + offset] = (byte) str.charAt(i);
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                data[i + length + offset - 1] = (byte) str.charAt(i);
            }
        }
    }
}
