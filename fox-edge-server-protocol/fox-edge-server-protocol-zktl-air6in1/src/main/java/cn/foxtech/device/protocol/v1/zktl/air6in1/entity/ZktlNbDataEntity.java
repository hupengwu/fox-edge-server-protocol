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

package cn.foxtech.device.protocol.v1.zktl.air6in1.entity;

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
     * PM1.0
     */
    private int pm1p0 = 0;
    /**
     * PM2.5
     */
    private int pm2p5 = 0;
    /**
     * PM10
     */
    private int pm10 = 0;
    /**
     * VOC
     */
    private double voc = 0;
    /**
     * 温度
     */
    private double temp = 0;
    /**
     * 湿度
     */
    private double humidity = 0;
    /**
     * 包类型
     */
    private String packType = "";
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
        return "air6in1=" + super.getCommunTypeName() + ":" + super.getDeviceTypeName() + ":" + this.imei + ":" + this.iccid;
    }
}
