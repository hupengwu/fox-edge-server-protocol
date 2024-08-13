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

package cn.foxtech.device.protocol.v1.iec104.core.entity;

import lombok.Data;

/**
 * APDU(应用协议单元)
 * I帧：编号的信息传输帧，包含APCI和ASDU。控制域1的bit1=0，表示I帧。
 * S帧：编号监视帧。控制域1的bit1=1 ，bit2=0表示S帧，只包含APCI。
 * U帧：未编号的控制帧。控制域1的bit1=1， bit2=1表示U帧，只包含APCI。
 */
@Data
public class ApduEntity {
    /**
     * 控制信息:1+1+4字节
     */
    private ControlEntity control;

    /**
     * 数据单元：6+N字节
     * 只有I帧才包含这段数据，S帧和U帧不包含这段数据
     */
    private AsduEntity asdu;
}
