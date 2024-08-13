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

package cn.foxtech.common.entity.utils;

import cn.foxtech.common.utils.bean.BeanMapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 将Entity生成VO的数据结构：要剔除掉一些Entity内部数据
 */
public class EntityVOBuilder {
    public static <T> List<String> getFilterKeys() {
        List<String> filterKeys = new ArrayList<>();

        filterKeys.add("serviceKey");
        filterKeys.add("serviceKeyList");
        filterKeys.add("serviceValue");
        filterKeys.add("wrapperKey");
        return filterKeys;
    }

    public static <T> List<Map<String, Object>> buildVOList(Collection<T> entityList) {
        return BeanMapUtils.objectToMap(entityList, getFilterKeys());
    }

    public static <T> Map<String, Object> buildVO(T entity) {
        return BeanMapUtils.objectToMap(entity, getFilterKeys());
    }
}
