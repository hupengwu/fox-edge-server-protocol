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

package cn.foxtech.utils.common.utils.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取全部数据
     *
     * @param key 缓存的键值
     * @return ZSet的数据
     */
    public Set<String> rangeCacheZSet(final String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }


    /**
     * 查询分数信息
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Map<String, Double> rangeWithScoresCacheZSet(final String key, final long start, final long end) {
        Map<String, Double> map = new HashMap<>();
        Set<ZSetOperations.TypedTuple<String>> rang = redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        for (ZSetOperations.TypedTuple<String> typedTuple : rang) {
            map.put(typedTuple.getValue(), typedTuple.getScore());
        }

        return map;
    }

    /**
     * 删除ZSet的数据
     *
     * @param key   缓存的键值
     * @param start 下标范围-start
     * @param end   下标范围-end
     * @return 删除的数量
     */
    public Long removeRangeCacheZSet(final String key, final long start, final long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 添加ZSet数据
     *
     * @param key
     * @param map
     * @return
     */
    public Long addIfAbsentCacheZSet(final String key, Map<String, Double> map) {
        Set<ZSetOperations.TypedTuple<String>> typles = new HashSet<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            ZSetOperations.TypedTuple<String> objectTypedTuple = new DefaultTypedTuple<>(entry.getKey(), entry.getValue());
            typles.add(objectTypedTuple);
        }
        return redisTemplate.opsForZSet().addIfAbsent(key, typles);
    }

    /**
     * 添加ZSet数据
     *
     * @param key
     * @param hkeys
     * @return
     */
    public Long addIfAbsentCacheZSet(final String key, Set<String> hkeys) {
        Map<String, Double> map = new HashMap<>();
        for (String hkey : hkeys) {
            map.put(hkey, 0.0);
        }

        return this.addIfAbsentCacheZSet(key, map);
    }

    /**
     * 增加ZSet某个元素的分数,vk不存在，直接新增一个元素
     *
     * @param key   缓存的键值
     * @param vkey  元素特征值
     * @param delta 递增值
     * @return 返回增加后的值
     */
    public Double incrementScoreCacheZSet(final String key, String vkey, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, vkey, delta);
    }

    /**
     * 返回指定数值范围的集合
     *
     * @param key 缓存的键值
     * @param min 数值范围min
     * @param max 数值范围max
     * @return 集合
     */
    public Set<String> rangeByScoreScoreCacheZSet(final String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }


    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public <T> List<T> getCacheMap(final String key, final Collection<String> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    public <T> Long deleteCacheMap(final String key, final String hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> readEntityMap(final String key, final Collection hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
