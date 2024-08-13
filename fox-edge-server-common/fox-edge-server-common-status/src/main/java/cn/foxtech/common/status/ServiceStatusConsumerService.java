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

package cn.foxtech.common.status;

import cn.foxtech.common.utils.redis.status.RedisStatusConsumerService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServiceStatusConsumerService extends RedisStatusConsumerService {
    /**
     * 本地缓存
     */
    @Getter(value = AccessLevel.PUBLIC)
    private final Map<String, Object> consumerData = new ConcurrentHashMap<>();

    public String getKeySync() {
        return "fox.edge.service.status.sync";
    }

    public String getKeyData() {
        return "fox.edge.service.status.data";
    }
}
