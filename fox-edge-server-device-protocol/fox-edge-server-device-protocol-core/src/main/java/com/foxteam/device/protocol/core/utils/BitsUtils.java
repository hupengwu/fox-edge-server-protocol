package com.foxteam.device.protocol.core.utils;

/**
 * 浮点数内存编码转换
 */
public class BitsUtils {
    /**
     * 4字节整数内存编码，转换为int
     *
     * @param bts
     * @return
     */
    public static int bitsToInteger(byte[] bts) {
        int bits = bts[0] + (bts[1] << 8) + (bts[2] << 16) + (bts[3] << 24);
        return bits;
    }

    /**
     * 4字节整数内存编码，转换为int
     *
     * @param bts0
     * @param bts1
     * @param bts2
     * @param bts3
     * @return
     */
    public static float bitsToInteger(byte bts0, byte bts1, byte bts2, byte bts3) {
        int bits = bts0 + (bts1 << 8) + (bts2 << 16) + (bts3 << 24);
        return bits;
    }

    /**
     * 4字节浮点数内存编码，转换为float
     *
     * @param bts
     * @return
     */
    public static float bitsToFloat(byte[] bts) {
        if (bts.length == 4) {
            int bits = bts[0] + (bts[1] << 8) + (bts[2] << 16) + (bts[3] << 24);
            return Float.intBitsToFloat(bits);
        } else {
            return 0;
        }
    }

    /**
     * 4字节浮点数内存编码，转换为float
     *
     * @param bts0
     * @param bts1
     * @param bts2
     * @param bts3
     * @return
     */
    public static float bitsToFloat(byte bts0, byte bts1, byte bts2, byte bts3) {
        int bits = bts0 + (bts1 << 8) + (bts2 << 16) + (bts3 << 24);
        return Float.intBitsToFloat(bits);
    }

    public static float bitsToFloat(int bits) {
        return Float.intBitsToFloat(bits);
    }

    /**
     * 8字节浮点数内存编码，转换为double
     *
     * @param bts
     * @return
     */
    public static double bitsToDouble(byte[] bts) {
        if (bts.length == 8) {
            long bits = bts[0] + (bts[1] << 8) + (bts[2] << 16) + (bts[3] << 24) + (bts[4] << 32) + (bts[5] << 40) + (bts[6] << 48) + (bts[7] << 56);
            return Double.longBitsToDouble(bits);
        } else {
            return 0;
        }
    }

    /**
     * 8字节浮点数内存编码，转换为double
     */
    public static double bitsToDouble(byte bts0, byte bts1, byte bts2, byte bts3, byte bts4, byte bts5, byte bts6, byte bts7) {
        long bits = bts0 + (bts1 << 8) + (bts2 << 16) + (bts3 << 24) + (bts4 << 32) + (bts5 << 40) + (bts6 << 48) + (bts7 << 56);
        return Double.longBitsToDouble(bits);
    }
}
