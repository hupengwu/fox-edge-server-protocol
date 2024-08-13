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

import cn.foxtech.device.protocol.v1.iec104.core.enums.CotReasonEnum;
import lombok.Data;

@Data
public class CotEntity {
    /**
     * 传送原因
     */
    private int reason = CotReasonEnum.active.getValue();

    /**
     * T = 0 未试验 ； T = 1 试验 （一般 T= 0）
     */
    private boolean test = false;
    /**
     * P/N = 0 肯定 ； P/N = 1 否定 （正常为P/N = 0；P/N = 1说明该报文无效）
     */
    private boolean pn = true;

    /**
     * 源发地址：用来记录来时哪个主站的响应数据，一般写 0；
     */
    private int addr = 0;
}
