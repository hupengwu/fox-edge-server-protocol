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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class RedisWriter {
    /**
     * redis
     */
    private RedisTemplate redisTemplate;

    /**
     * 边缘服务器：数据库表的名称
     */
    private String entityType;

    private String getHead() {
        return "fox.edge.entity." + this.getEntityType() + ".";
    }


    public synchronized void writeEntityMap(Map<String, BaseEntity> dataMap) {
        if (dataMap == null || dataMap.isEmpty()) {
            return;
        }

        Long time = System.currentTimeMillis();

        // 构造敏捷数据
        Map<String, Long> agileMap = new HashMap<>();
        for (String key : dataMap.keySet()) {
            agileMap.put(key, time);
        }

        this.redisTemplate.opsForHash().putAll(this.getHead() + "agile", agileMap);
        this.redisTemplate.opsForHash().putAll(this.getHead() + "data", dataMap);
        this.redisTemplate.opsForValue().set(this.getHead() + "sync", time);
    }

    public synchronized void deleteEntity(Set<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return;
        }

        Long time = System.currentTimeMillis();


        for (String key : keys) {
            this.redisTemplate.opsForHash().delete(this.getHead() + "agile", key);
            this.redisTemplate.opsForHash().delete(this.getHead() + "data", key);
            this.redisTemplate.opsForValue().set(this.getHead() + "sync", time);
        }
    }

    public void deleteEntity(String key) {
        Set<String> keys = new HashSet<>();
        keys.add(key);

        this.deleteEntity(keys);
    }

    public void writeEntityList(List<BaseEntity> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        Map<String, BaseEntity> dataMap = new HashMap<>();
        for (BaseEntity entity : dataList) {
            dataMap.put(entity.makeServiceKey(), entity);
        }

        this.writeEntityMap(dataMap);
    }

    public void writeEntity(BaseEntity entity) {
        if (entity == null) {
            return;
        }

        Map<String, BaseEntity> dataMap = new HashMap<>();
        dataMap.put(entity.makeServiceKey(), entity);

        this.writeEntityMap(dataMap);
    }
}
