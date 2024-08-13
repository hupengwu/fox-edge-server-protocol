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

package cn.foxtech.device.protocol.v1.hj212_2017.utils;

public class Crc16Util {
    /**
     * 中国环境自定义的“CRC16”算法
     * 备注：它并不是国际规范化的CRC16，所以不要用协议库中的计算公式来重构代码了
     * 它只是一个CRC16风格的算法
     *
     * @param pdu 报文
     * @return 返回值
     */
    public static int getCrc16(byte[] pdu) {
        int usDataLen = pdu.length;

        int i, j, crc_reg, check;
        crc_reg = 0xFFFF;
        for (i = 0; i < usDataLen; i++) {
            crc_reg = (crc_reg >> 8) ^ (pdu[i] & 0xff);
            for (j = 0; j < 8; j++) {
                check = crc_reg & 0x0001;
                crc_reg >>= 1;
                if (check == 0x0001) {
                    crc_reg ^= 0xA001;
                }
            }
        }
        return crc_reg & 0xffff;
    }

}
