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

import com.fasterxml.jackson.core.JsonParseException;
import cn.foxtech.utils.common.utils.redis.service.RedisService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapRedisService extends BaseHashMapRedisService {
    private static final Map<String, HashMapRedisService> map = new ConcurrentHashMap<>();
    private String entityType;
    private RedisService redisService;

    public static synchronized <T> HashMapRedisService newInstance(Class<T> clazz, RedisService redisService) {
        HashMapRedisService instance = new HashMapRedisService();
        instance.entityType = clazz.getSimpleName();
        instance.redisService = redisService;
        return instance;
    }

    public static synchronized <T> HashMapRedisService getInstanceBySimpleName(String clazzSimpleName, RedisService redisService) {
        // 如果已经存在，那么取出该实例
        if (map.containsKey(clazzSimpleName)) {
            return map.get(clazzSimpleName);
        }

        // 分配并保存实例
        HashMapRedisService instance = new HashMapRedisService();
        instance.entityType = clazzSimpleName;
        instance.redisService = redisService;

        map.put(instance.entityType, instance);
        return map.get(clazzSimpleName);
    }

    public static <T> HashMapRedisService getInstance(Class<T> clazz, RedisService redisService) {
        return getInstanceBySimpleName(clazz.getSimpleName(), redisService);
    }

    /**
     * 根据redis的时间戳，判定数据是否已经被生产者更新
     *
     * @return
     */
    @Override
    public boolean isNeedLoad() {
        return super.isNeedLoad();
    }

    /**
     * 从redis重新读取数据到本地内存中
     */
    @Override
    public void loadAllEntities() throws JsonParseException {
        super.loadAllEntities();
    }

    /**
     * 绑定通知
     *
     * @param notify
     */
    @Override
    public void bind(HashMapRedisNotify notify) {
        super.bind(notify);
    }

    /**
     * 从redis重新读取数据到本地内存中
     */
    @Override
    public void loadAgileEntities() throws IOException {
        super.loadAgileEntities();
    }

    /**
     * 获得一个副本
     *
     * @return
     */
    @Override
    public Map<String, Map<String, Object>> getEntitys() {
        return super.getEntitys();
    }

    /**
     * 获取实体
     *
     * @return 实体
     */
    @Override
    public Map<String, Object> getEntity(String entityKey) {
        return super.getEntity(entityKey);
    }

    public RedisService getRedisService() {
        return this.redisService;
    }

    public String getEntityType() {
        return this.entityType;
    }
}
