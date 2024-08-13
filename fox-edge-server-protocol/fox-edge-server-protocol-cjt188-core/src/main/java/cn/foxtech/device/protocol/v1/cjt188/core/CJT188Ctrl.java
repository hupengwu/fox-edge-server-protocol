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

package cn.foxtech.device.protocol.v1.cjt188.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class CJT188Ctrl {
    private int value = 0;

    public int getCtrl() {
        return value & 0x7f;
    }

    public boolean getCS() {
        return (value & 0x80) == 0;
    }

    public String getName() {
        if (this.getCtrl() == 0x01) {
            return "读表计数据";
        }
        if (this.getCtrl() == 0x03) {
            return "读表计地址";
        }
        if (this.getCtrl() == 0x15) {
            return "设置表计地址";
        }
        if (this.getCtrl() == 0x2A) {
            return "控制阀门";
        }
        if (this.getCtrl() == 0x04) {
            return "控制阀门";
        }


        return "未定义的值:" + this.getCtrl();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.getCS()) {
            sb.append("主叫方");
        } else {
            sb.append("应答方");
        }
        sb.append(":");
        sb.append(this.getName());

        return sb.toString();
    }
}
