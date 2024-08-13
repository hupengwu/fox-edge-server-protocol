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

package cn.foxtech.device.protocol.v1.core.enums;


import lombok.Getter;


public enum WorkerLoggerType {
    send(0, "发送"),//
    recv(1, "接收"),//

    ;
    @Getter
    private final int code;

    @Getter
    private final String name;

    WorkerLoggerType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static WorkerLoggerType getEnum(Integer code) {
        for (WorkerLoggerType value : WorkerLoggerType.values()) {
            if (code.equals(value.code)) {
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
