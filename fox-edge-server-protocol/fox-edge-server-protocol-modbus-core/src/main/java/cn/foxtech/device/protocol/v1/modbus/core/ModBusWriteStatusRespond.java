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

package cn.foxtech.device.protocol.v1.modbus.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 状态查询响应报文
 * 注意：线圈状态返回的数量总是8的倍数，也就是说有些数据是空白的，需要跟发送参数核对。
 * 比如，发送包查询20个数据，那么会返回24个数据，实际上的数据要跟发送报文中的20个核对
 */

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ModBusWriteStatusRespond {
    /**
     * 报文实体
     */
    private ModBusEntity entity = new ModBusEntity();

    /**
     * 地址偏移量
     */
    private int memAddr = 0;

    /**
     * 线圈状态
     */
    private boolean status = false;
}
