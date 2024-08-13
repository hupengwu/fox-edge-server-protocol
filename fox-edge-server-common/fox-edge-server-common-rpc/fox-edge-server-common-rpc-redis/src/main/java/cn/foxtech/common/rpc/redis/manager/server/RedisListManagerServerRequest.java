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

package cn.foxtech.common.rpc.redis.manager.server;

import cn.foxtech.common.domain.vo.RestFulRequestVO;
import cn.foxtech.common.utils.redis.logger.RedisLoggerService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 记录类型的队列：这是可靠性记录，它会在redis之中缓存
 */
@Component
public class RedisListManagerServerRequest extends RedisLoggerService {
    @Getter
    private final String key = "fox.edge.list:manager:restful:message:request";

    public RestFulRequestVO popRequest(long timeout, TimeUnit unit) {
        try {
            Object map = super.pop(timeout, unit);
            if (map == null) {
                return null;
            }

            return RestFulRequestVO.buildVO((Map<String, Object>) map);
        } catch (Exception e) {
            return null;
        }
    }
}