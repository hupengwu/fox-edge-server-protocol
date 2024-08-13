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

package cn.foxtech.device.protocol.v1.zktl.ctrl4g.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZktlPeriodDataEntity extends ZktlDataEntity {
    /**
     * ICCID
     */
    private String iccid = "";
    /**
     * 供电类型
     */
    private int powerSupplyType = 0;
    /**
     * 电池电压
     */
    private int voltage = 0;
    /**
     * ADC0
     */
    private int adc0 = 0;
    /**
     * ADC1
     */
    private int adc1 = 0;
    /**
     * 继电器状态
     */
    private int relayStatus = 0;
    /**
     * 外部输入 IO
     */
    private int outIO = 0;
    /**
     * 信号 CSQ
     */
    private int signalCSQ = 0;
    /**
     * 预留
     */
    private int reserve = 0;
}
