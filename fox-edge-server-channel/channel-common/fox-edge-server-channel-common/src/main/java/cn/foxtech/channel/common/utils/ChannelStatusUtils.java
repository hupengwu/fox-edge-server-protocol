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

package cn.foxtech.channel.common.utils;

import cn.foxtech.channel.domain.ChannelVOConstant;

import java.util.HashMap;
import java.util.Map;

public class ChannelStatusUtils {
    public static Map<String, Object> buildStatus(boolean isOpen, long activeTime) {
        Map<String, Object> result = new HashMap<>();
        result.put(ChannelVOConstant.value_channel_status_is_open, isOpen);
        result.put(ChannelVOConstant.value_channel_status_is_active, activeTime);
        return result;
    }
}
