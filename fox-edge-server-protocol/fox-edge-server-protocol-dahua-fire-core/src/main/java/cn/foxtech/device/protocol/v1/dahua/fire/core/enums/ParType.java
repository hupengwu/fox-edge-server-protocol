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

package cn.foxtech.device.protocol.v1.dahua.fire.core.enums;

import lombok.Getter;


/**
 * 不定长参数类型及取值范围定义表
 */
public enum ParType {
    host("域名/IP", 0, ParFmt.ascii, ParRW.rw),//
    port("PORT", 1, ParFmt.ascii, ParRW.rw),//
    localIp("本地IP", 2, ParFmt.ascii, ParRW.rw),//
    mask("掩码", 3, ParFmt.ascii, ParRW.rw),//
    gateway("网关", 4, ParFmt.ascii, ParRW.rw),//
    dns("DNS", 5, ParFmt.ascii, ParRW.rw),//
    alarmTel("报警电话", 6, ParFmt.ascii, ParRW.rw),//
    password("密码", 7, ParFmt.ascii, ParRW.rw),//
    timezone("时间区间", 8, ParFmt.ascii, ParRW.rw),//
    confInfo("主机配置及部件信息", 9, ParFmt.hex, ParRW.rw),//
    imei("IMEI", 10, ParFmt.bcd, ParRW.r),//
    imsi("IMSI", 11, ParFmt.bcd, ParRW.r),//
    ccid("CCID", 12, ParFmt.bcd, ParRW.r),//
    cellId("Cell ID", 13, ParFmt.ascii, ParRW.r),//
    apn("APN", 14, ParFmt.ascii, ParRW.r),//
    deviceType("产品型号", 15, ParFmt.ascii, ParRW.r),//
    time("时间", 16, ParFmt.ascii, ParRW.rw),//
    mac("MAC地址", 17, ParFmt.hex, ParRW.rw),//
    sn("SN", 18, ParFmt.ascii, ParRW.r),//
    softwareVer("软件版本", 19, ParFmt.ascii, ParRW.r),//
    particleIdentify("粒子鉴别开关", 20, ParFmt.hex, ParRW.rw),//
    auxiliarySensor("辅助传感器开关", 21, ParFmt.hex, ParRW.rw),//
    filterLifespan("过滤器寿命监控开关", 22, ParFmt.hex, ParRW.rw),//
    ;

    @Getter
    private final String name;

    @Getter
    private final int type;

    @Getter
    private final ParFmt fmt;

    @Getter
    private final ParRW rw;


    ParType(String name, int type, ParFmt fmt, ParRW rw) {
        this.name = name;
        this.type = type;
        this.fmt = fmt;
        this.rw = rw;
    }

    public static ParType getEnum(int type) {
        for (ParType par : ParType.values()) {
            if (par.type == type) {
                return par;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
