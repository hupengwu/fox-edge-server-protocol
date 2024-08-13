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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ExtendParam {
    /**
     * 绑定的对象列表
     */
    private Set<Object> binds = new HashSet<>();
    /**
     * 扩展的字段列表
     */
    private List<ExtendField> fields = new ArrayList<>();

    public void bind(Map<String, Object> map) {
        this.binds.clear();
        this.binds.addAll((Collection) map.getOrDefault("binds", new HashSet<>()));

        this.fields.clear();
        Collection fields = (Collection) map.getOrDefault("fields", new HashSet<>());
        for (Object field : fields) {
            if (field instanceof ExtendField) {
                this.fields.add((ExtendField) field);
                continue;
            }
            if (field instanceof Map) {
                ExtendField value = new ExtendField();
                value.bind((Map) field);
                this.fields.add(value);
                continue;
            }

        }
    }
}
