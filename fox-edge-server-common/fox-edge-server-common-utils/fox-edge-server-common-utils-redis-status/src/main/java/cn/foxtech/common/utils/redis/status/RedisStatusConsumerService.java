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

package cn.foxtech.common.utils.redis.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class RedisStatusConsumerService {
    /**
     * 本地缓存
     */
    private final Map<String, Object> localMap = new ConcurrentHashMap<>();
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 系统key
     *
     * @return 系统key
     */
    public abstract String getKeySync();

    public abstract String getKeyData();


    /**
     * 4、消费端（异步线程）：保存数据
     *
     * @return 是否成功
     */
    public synchronized boolean load() {
        // 读取redis数据
        Map<String, Object> redisSync = redisTemplate.opsForHash().entries(this.getKeySync());

        for (String key : redisSync.keySet()) {
            Object updateTime = redisSync.getOrDefault(key, -1L);

            // 取出本地缓存的数据
            Map<String, Object> localData = (Map<String, Object>) this.localMap.get(key);
            if (localData == null) {
                localData = new HashMap<>();
                localData.put(RedisStatusConst.field_active_time, -1L);
                this.localMap.put(key, localData);
            }

            Object lastTime = localData.getOrDefault(RedisStatusConst.field_active_time, -1L);


            // 比较时间戳
            if (updateTime.equals(lastTime)) {
                continue;
            }


            Map<String, Object> redisData = (Map<String, Object>) redisTemplate.opsForHash().get(this.getKeyData(), key);
            if (redisData == null) {
                continue;
            }

            if (!redisData.containsKey(RedisStatusConst.field_active_time)) {
                redisData.put(RedisStatusConst.field_active_time, -1L);
            }

            this.localMap.put(key, redisData);
        }

        return true;
    }

    /**
     * 5、消费端（异步线程）：保存数据
     *
     * @return 状态
     */
    public synchronized Map<String, Object> getStatus() {
        return this.localMap;
    }
}
