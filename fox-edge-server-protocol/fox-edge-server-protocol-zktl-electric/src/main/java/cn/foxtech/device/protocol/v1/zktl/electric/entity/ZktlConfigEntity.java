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

package cn.foxtech.device.protocol.v1.zktl.electric.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备的配置命令为：SETxxx=yyyAA的格式，xxx是大写的命令字，yyy是命令字规定的数值格式
 * 例如：
 * SETCH=255AA
 * SETUL=200VAA
 * SETINH=500mAAA
 */
@Getter
@Setter
public class ZktlConfigEntity {
    /**
     * 命令
     */
    private String cmd = "";
    /**
     * 数值
     */
    private String value = "";
}
