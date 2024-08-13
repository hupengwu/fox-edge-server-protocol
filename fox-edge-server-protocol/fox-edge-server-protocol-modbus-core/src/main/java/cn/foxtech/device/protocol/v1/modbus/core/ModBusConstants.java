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

package cn.foxtech.device.protocol.v1.modbus.core;

/**
 * ModBus常量定义
 */
public class ModBusConstants {
    public static final String MODE = "modbusMode";
    public static final String MODE_ASCII = "ASCII";
    public static final String MODE_RTU = "RTU";
    public static final String MODE_TCP = "TCP";

    /**
     * ModBus的报文结构
     */
    public static final String SN = "sn";
    public static final String ADDR = "devAddr";
    public static final String FUNC = "func";
    public static final String DATA = "data";

    /**
     * 寄存器地址和数量
     */
    public static final String REG_ADDR = "regAddr";
    public static final String REG_CNT = "regCnt";

    public static final String REG_HOLD_STATUS = "regHoldStatus";
}
