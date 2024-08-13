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


public enum ParRW {
    r("r"),//
    w("w"),//
    rw("r/w"),//
    ;


    @Getter
    private final String name;

    ParRW(String name) {
        this.name = name;
    }

    public static ParRW getEnum(String name) {
        for (ParRW type : ParRW.values()) {
            if (type.getName().equals(name)) {
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
