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

package cn.foxtech.device.protocol.v1.haier.ycj.a002.enums;

import lombok.Getter;


public enum Speed {
    value0(0, "超高速"),//
    value1(1, "高速"),//
    value2(2, "中速"),//
    value3(3, "低速"),//
    value4(4, "自动"),//
    value5(5, "微风"),//
    value6(6, "中风弱"),//

    ;
    @Getter
    private final int code;

    @Getter
    private final String name;

    Speed(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Speed getEnum(Integer code) {
        for (Speed value : Speed.values()) {
            if (code.equals(value.code)) {
                return value;
            }
        }

        return null;
    }

    public static Speed getEnum(String name) {
        for (Speed value : Speed.values()) {
            if (name.equals(value.name)) {
                return value;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
