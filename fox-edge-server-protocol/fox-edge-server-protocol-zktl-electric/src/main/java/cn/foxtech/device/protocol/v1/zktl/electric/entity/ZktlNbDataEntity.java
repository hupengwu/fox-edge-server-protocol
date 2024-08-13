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

package cn.foxtech.device.protocol.v1.zktl.electric.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZktlNbDataEntity extends ZktlDataEntity {
    /**
     * IMEI
     */
    private String imei = "";
    /**
     * ICCID
     */
    private String iccid = "";
    /**
     * 包类型
     */
    private int packType = 0;
    /**
     * 消音状态
     */
    private int silencerStatus = 0;
    /**
     * 电压状态
     */
    private int voltageStatus = 0;
    /**
     * 电流状态
     */
    private int currentStatus = 0;
    /**
     * 温度状态
     */
    private int tempStatus = 0;
    /**
     * A 相电压
     */
    private int voltageA = 0;
    /**
     * B 相电压
     */
    private int voltageB = 0;
    /**
     * C 相电压
     */
    private int voltageC = 0;
    /**
     * A 线电流
     */
    private double currentA = 0;
    /**
     * B 线电流
     */
    private double currentB = 0;
    /**
     * C 线电流
     */
    private double currentC = 0;
    /**
     * 漏电流
     */
    private int currentLeakage = 0;
    /**
     * A 线温
     */
    private int lineTempeA = 0;
    /**
     * B 线温
     */
    private int lineTempeB = 0;
    /**
     * C 线温
     */
    private int lineTempeC = 0;
    /**
     * 箱温
     */
    private int boxTemp = 0;
    /**
     * A 相有功功率
     */
    private double activePowerA = 0;
    /**
     * B 相有功功率
     */
    private double activePowerB = 0;
    /**
     * C 相有功功率
     */
    private double activePowerC = 0;
    /**
     * 总有功功率
     */
    private double activePowerTotal = 0;
    /**
     * A 相无功功率
     */
    private double reactivePowerA = 0;
    /**
     * B 相无功功率
     */
    private double reactivePowerB = 0;
    /**
     * C 相无功功率
     */
    private double reactivePowerC = 0;
    /**
     * 总无功功率
     */
    private double reactivePowerTotal = 0;
    /**
     * A 相视在功率
     */
    private double apparentPowerA = 0;
    /**
     * A 相视在功率
     */
    private double apparentPowerB = 0;
    /**
     * A 相视在功率
     */
    private double apparentPowerC = 0;
    /**
     * 总视在功率
     */
    private double apparentPowertotal = 0;
    /**
     * A 相功率因数
     */
    private double powerFactorA = 0;
    /**
     * B 相功率因数
     */
    private double powerFactorB = 0;
    /**
     * C 相功率因数
     */
    private double powerFactorC = 0;
    /**
     * 总功率因数
     */
    private double powerFactorTotal = 0;
    /**
     * 电网频率
     */
    private int gridFrequency = 0;
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
        return "electric fire=" + super.getCommunTypeName() + ":" + super.getDeviceTypeName() + ":" + this.imei + ":" + this.iccid;
    }
}
