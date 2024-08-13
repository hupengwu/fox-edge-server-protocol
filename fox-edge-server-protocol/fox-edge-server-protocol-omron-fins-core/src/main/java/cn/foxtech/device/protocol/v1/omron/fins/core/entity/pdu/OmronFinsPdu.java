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

package cn.foxtech.device.protocol.v1.omron.fins.core.entity.pdu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 欧姆龙的一级报文结构
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OmronFinsPdu {
    // 命令字:
    /**
     * 命令字
     * 0x00：connect requst 连接请求数据帧
     * 0x01：connect Response，连接请求确认数据；
     * 0x02：data，数据传输；
     */
    private int command = 2;

    /**
     * 出错码
     */
    private int error = 0;


    /**
     * 数据区
     */
    private byte[] data = new byte[0];
}
