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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RedisTagService {
    /**
     * 本地缓存
     */
    private final Map<String, Object> consumerData = new ConcurrentHashMap<>();
    private final Map<String, Object> producerData = new ConcurrentHashMap<>();
    /**
     * 总的同步标记
     */
    private final Object consumerSyncTag = 0L;
    /**
     * 总的同步标记
     */
    private boolean producerSyncTag = false;

    /**
     * 读取redis
     */
    @Autowired
    private RedisTagReader reader;

    @Autowired
    private RedisTagWriter writer;

    public synchronized boolean load() {
        // 读取总标记
        Object redisSync = this.reader.readTagsSync();
        if (redisSync == null) {
            return false;
        }

        // 检查：总标记是否发生了变化
        if (this.consumerSyncTag.equals(redisSync)) {
            return true;
        }

        // 读取具体数据
        Map<String, Object> tags = this.reader.readTags();
        if (tags == null) {
            return false;
        }

        // 填写新数据
        for (String key : tags.keySet()) {
            Object value = tags.get(key);
            if (value == null) {
                continue;
            }

            this.consumerData.put(key, value);
        }

        // 删除老数据
        Set<String> keys = new HashSet<>();
        keys.addAll(this.consumerData.keySet());
        for (String key : keys) {
            Object value = tags.get(key);
            if (value == null) {
                this.consumerData.remove(key);
            }
        }

        return true;
    }

    public synchronized void save() {
        if (!this.producerSyncTag) {
            return;
        }

        this.writer.writeTags(this.producerData);
        this.producerSyncTag = false;
    }

    public synchronized void setValue(String key, Object value) {
        this.producerData.put(key, value);
        this.producerSyncTag = true;
    }

    public synchronized Object getValue(String key) {
        return this.getValue(key);
    }
}
