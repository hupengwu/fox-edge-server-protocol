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

public enum Upgrade {
    upgradeOk(0, "升级成功"),//
    illegalNode(1, "非法节点"),//
    commTimeout(2, "通信超时"),//
    underVoltage(3, "节点欠压"),//
    disconnect(4, "节点失联"),//
    powerFailure(5, "电源故障"),//
    verifyError(6, "校验错误"),//
    ;
    @Getter
    private final int value;


    @Getter
    private final String name;

    Upgrade(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Upgrade getEnum(int value) {
        for (Upgrade type : Upgrade.values()) {
            if (type.value == value) {
                return type;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
