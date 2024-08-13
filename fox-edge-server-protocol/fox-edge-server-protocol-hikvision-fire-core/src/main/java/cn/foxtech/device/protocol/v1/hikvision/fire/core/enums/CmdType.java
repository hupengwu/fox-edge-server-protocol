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

package cn.foxtech.device.protocol.v1.hikvision.fire.core.enums;


import lombok.Getter;


/**
 * 命令字和类型定义表
 * 注意：
 * 1、一个命令字下，有多种类型标志
 * 2、类型标志，可以为null
 * 3、Sender.any是设备和平台，都是可以使用的
 */
public enum CmdType {
    control(1, "控制"),//
    send(2, "发送"),//
    confirm(3, "确认"),//
    request(4, "请求"),//
    respond(5, "应答"),//
    deny(6, "否认"),//
    ;
    @Getter
    private final int cmd;

    @Getter
    private final String description;

    CmdType(int cmd, String description) {
        this.cmd = cmd;
        this.description = description;
    }

    public static CmdType getEnum(Integer cmd) {
        for (CmdType cmdType : CmdType.values()) {
            if (cmd.equals(cmdType.cmd)) {
                return cmdType;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
