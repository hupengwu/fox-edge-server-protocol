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

package cn.foxtech.device.protocol.v1.zktl.air5in1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZktlLoRaDataEntity extends ZktlDataEntity {
    /**
     * Addr
     */
    private String addr = "";
    /**
     *  防拆状态
     */
    private int tamper = 0;
    /**
     * 外部触发状态
     */
    private int externalTrigger = 0;
    /**
     * 外部开关 2 状态
     */
    private int externalSwitch2 = 0;
    /**
     * 外部开关 1 状态
     */
    private int externalSwitch1 = 0;
    /**
     * 包类型
     */
    private int packType = 0;
    /**
     * 电池电压
     */
    private double batteryVoltage = 0;
    /**
     * 采集数据
     */
    private double collectData = 0;
    /**
     * 信号强度
     */
    private int signal = 0;
    /**
     * 包序号
     */
    private int packSn = 0;
    /**
     * 预留
     */
    private int reserve = 0;

    public String getServiceKey() {
        return "air5in1=" + super.getCommunTypeName() + ":" + super.getDeviceTypeName() + ":" + this.addr;
    }
}
