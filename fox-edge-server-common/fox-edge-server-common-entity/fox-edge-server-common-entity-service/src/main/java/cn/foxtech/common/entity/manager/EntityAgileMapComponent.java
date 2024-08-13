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

package cn.foxtech.common.entity.manager;


import cn.foxtech.common.entity.service.redis.AgileMapRedisService;
import cn.foxtech.common.entity.service.redis.BaseAgileMapRedisService;
import cn.foxtech.utils.common.utils.redis.service.RedisService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 敏捷版的HashMap版的Redis的部件：它只将Agile时间戳装载到本地内存
 * 这样就可以达到减少内存占用，又可以快速感知redis的时间戳发生了变化
 */
@Data
@Component
public class EntityAgileMapComponent {
    /**
     * 消费者：Entity结构的数据
     */
    private final Set<String> consumer = new HashSet<>();

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    protected <T> AgileMapRedisService getBaseRedisService(Class<T> clazz) {
        if (this.consumer.contains(clazz.getSimpleName())) {
            AgileMapRedisService consumerRedisService = AgileMapRedisService.getInstanceBySimpleName(clazz.getSimpleName(), this.redisService);
            return consumerRedisService;
        }

        return null;
    }

    protected <T> AgileMapRedisService getBaseRedisService(String simpleName) {
        if (this.consumer.contains(simpleName)) {
            AgileMapRedisService consumerRedisService = AgileMapRedisService.getInstanceBySimpleName(simpleName, this.redisService);
            return consumerRedisService;
        }

        return null;
    }
}
