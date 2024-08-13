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

package cn.foxtech.common.entity.entity;

import cn.foxtech.common.utils.number.NumberUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class DeviceValueExObjectValue {
    /**
     * 缓存队列的深度
     */
    private int cacheSize = 1;

    /**
     * 数值
     */
    private List<DeviceObjectValue> values = new ArrayList<>();

    public void bind(Map<String, Object> map) {
        this.cacheSize = NumberUtils.makeInteger(map.get("cacheSize"));

        List values = (List) map.getOrDefault("values", new ArrayList<>());
        this.values.clear();
        for (Object data : values) {
            if (data instanceof DeviceObjectValue) {
                this.values.add((DeviceObjectValue) data);
                continue;
            }
            if (data instanceof Map) {
                DeviceObjectValue deviceObjectValue = new DeviceObjectValue();
                deviceObjectValue.bind((Map) data);
                this.values.add(deviceObjectValue);
                continue;
            }
        }

    }
}
