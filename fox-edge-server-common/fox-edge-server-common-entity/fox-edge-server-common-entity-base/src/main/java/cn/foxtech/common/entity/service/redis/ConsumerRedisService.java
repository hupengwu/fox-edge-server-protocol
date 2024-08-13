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

import cn.foxtech.utils.common.utils.redis.service.RedisService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用消费者
 */
public class ConsumerRedisService extends BaseConsumerRedisService {
    private static final Map<String, ConsumerRedisService> map = new ConcurrentHashMap<>();

    private String entityType;

    private RedisService redisService;

    public static synchronized <T> ConsumerRedisService newInstance(Class<T> clazz, RedisService redisService) {
        ConsumerRedisService instance = new ConsumerRedisService();
        instance.entityType = clazz.getSimpleName();
        instance.redisService = redisService;
        return instance;
    }

    public static synchronized <T> ConsumerRedisService getInstanceBySimpleName(String clazzSimpleName, RedisService redisService) {
        // 如果已经存在，那么取出该实例
        if (map.containsKey(clazzSimpleName)) {
            return map.get(clazzSimpleName);
        }

        // 分配并保存实例
        ConsumerRedisService instance = new ConsumerRedisService();
        instance.entityType = clazzSimpleName;
        instance.redisService = redisService;

        map.put(instance.entityType, instance);
        return map.get(clazzSimpleName);
    }

    public static <T> ConsumerRedisService getInstance(Class<T> clazz, RedisService redisService) {
        return getInstanceBySimpleName(clazz.getSimpleName(), redisService);
    }


    public RedisService getRedisService() {
        return this.redisService;
    }

    public String getEntityType() {
        return this.entityType;
    }
}
