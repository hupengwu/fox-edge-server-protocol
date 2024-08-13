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

package cn.foxtech.device.protocol.v1.iec104.core.encoder;

import cn.foxtech.device.protocol.v1.iec104.core.enums.AsduTypeIdEnum;
import cn.foxtech.device.protocol.v1.iec104.core.enums.CotReasonEnum;

public class MessageUtils {
    public static String getTypeIdMessage(int typeId) {
        AsduTypeIdEnum typeIdEnum = AsduTypeIdEnum.getEnum(typeId);
        if (typeIdEnum == null) {
            return "未知的Asdu Type Id：" + typeId;
        }

        return typeIdEnum.getMsg();
    }

    public static String getReasonMessage(int reasonId) {
        CotReasonEnum reasonEnum = CotReasonEnum.getEnum(reasonId);
        if (reasonEnum == null) {
            return "未知的Asdu Cot reason Id：" + reasonId;
        }

        return reasonEnum.getMsg();
    }


}
