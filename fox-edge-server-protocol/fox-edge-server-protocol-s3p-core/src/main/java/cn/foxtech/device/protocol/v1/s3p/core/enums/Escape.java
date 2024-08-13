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

package cn.foxtech.device.protocol.v1.s3p.core.enums;

import lombok.Getter;


public enum Escape {
    No(0, "No"),//
    Min(1, "Min"),//
    Max(2, "Max"),//

    ;
    @Getter
    private final int code;

    @Getter
    private final String name;

    Escape(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Escape getEnum(Integer code) {
        for (Escape value : Escape.values()) {
            if (code.equals(value.code)) {
                return value;
            }
        }

        return null;
    }

    public static Escape getEnum(String name) {
        for (Escape value : Escape.values()) {
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
