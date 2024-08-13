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

package cn.foxtech.device.protocol.v1.iec104.core.enums;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * U帧的控制字枚举类型，U帧用于链路需要的建立/拆除/心跳
 */
public enum UControlTypeEnum {
    /**
     * 测试命令
     */
    TESTFR(0x43000000),
    /**
     * 测试确认指令
     */
    TESTFR_YES(0x83000000),
    /**
     * 停止指令
     */
    STOPDT(0x13000000),
    /**
     * 停止确认
     */
    STOPDT_YES(0x23000000),
    /**
     * 启动命令
     */
    STARTDT(0x07000000),
    /**
     * 启动确认命令
     */
    STARTDT_YES(0x0B000000);

    @Getter
    private final int value;

    UControlTypeEnum(int value) {
        this.value = value;
    }


    public static Set<Integer> getTypes() {
        Set<Integer> cmds = new HashSet<>();
        for (UControlTypeEnum type : UControlTypeEnum.values()) {
            cmds.add((type.getValue() >> 24) & 0xff);
        }

        return cmds;
    }
}
