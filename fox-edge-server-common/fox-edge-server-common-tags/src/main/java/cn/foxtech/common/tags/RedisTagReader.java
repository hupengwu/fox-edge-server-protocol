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

package cn.foxtech.common.tags;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 读取全局标志
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Component
public class RedisTagReader {


    /**
     * redis
     */
    @Autowired
    private RedisTemplate redisTemplate;


    private String getHead() {
        return "fox.edge.tags.";
    }


    /**
     * 读取同步标记
     *
     * @return
     */
    public synchronized Object readTagsSync() {
        return this.redisTemplate.opsForValue().get(this.getHead() + "sync");
    }

    public synchronized Map<String, Object> readTags() {
        Map<String, Object> dataJsn = this.redisTemplate.opsForHash().entries(this.getHead() + "data");
        return dataJsn;
    }

    public synchronized Object readTag(String key) {
        return this.redisTemplate.opsForHash().get(this.getHead() + "data", key);
    }
}
