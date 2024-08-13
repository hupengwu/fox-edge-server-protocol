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

package cn.foxtech.common.entity.service.redis;

import cn.foxtech.common.entity.entity.BaseEntity;

import java.util.Map;
import java.util.Set;

/**
 * 类型级别的通知
 */
public interface BaseConsumerTypeNotify {
    /**
     * 通知变更
     *
     * @param addMap
     * @param delSet
     * @param mdyMap
     */
    void notify(String entityType, long updateTime, Map<String, BaseEntity> addMap, Set<String> delSet, Map<String, BaseEntity> mdyMap);
}
