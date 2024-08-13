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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * ModBus的三要素：地址，功能码，数据
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ModBusEntity {
    /**
     * 流水号
     */
    private int sn = 0;

    /**
     * 地址
     */
    private byte devAddr = 0x01;

    /**
     * 功能码
     */
    private byte func = 0x01;

    /**
     * 数据域
     */
    private byte[] data = new byte[0];

    /**
     * 出错信息
     */
    private int errCode = 0;

    /**
     * 出错信息
     */
    private String errMsg = "";
}
