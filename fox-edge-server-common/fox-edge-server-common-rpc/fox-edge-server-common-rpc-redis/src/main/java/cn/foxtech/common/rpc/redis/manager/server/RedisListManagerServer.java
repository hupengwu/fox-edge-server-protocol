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
import cn.foxtech.common.domain.vo.RestFulRespondVO;
import cn.foxtech.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisListManagerServer {
    @Autowired
    private RedisListManagerServerRequest managerRequest;

    @Autowired
    private RedisListManagerServerRespond managerRespond;

    public RestFulRequestVO popRequest(long timeout, TimeUnit unit) {
        return this.managerRequest.popRequest(timeout, unit);
    }

    public void pushRespond(String uuid, RestFulRespondVO value) {
        if (uuid == null || uuid.isEmpty()) {
            throw new ServiceException("参数缺失：uuid 不能为空");
        }

        this.managerRespond.pushRespond(uuid, value);
    }
}
