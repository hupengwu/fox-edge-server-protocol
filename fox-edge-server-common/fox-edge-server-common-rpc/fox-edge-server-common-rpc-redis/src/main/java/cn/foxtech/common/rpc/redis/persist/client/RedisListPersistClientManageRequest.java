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

package cn.foxtech.common.rpc.redis.persist.client;

import cn.foxtech.common.utils.redis.logger.RedisLoggerService;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 发送列表
 * 接收者： manage
 * 发送者： persist
 */
@Component
public class RedisListPersistClientManageRequest extends RedisLoggerService {
    @Getter
    private final String key = "fox.edge.list:persist:manage:request";

    @Override
    public void push(Object value) {
        super.push(value);
    }
}