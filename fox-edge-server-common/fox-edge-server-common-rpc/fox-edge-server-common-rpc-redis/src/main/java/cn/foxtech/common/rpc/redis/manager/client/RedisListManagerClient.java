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

package cn.foxtech.common.rpc.redis.manager.client;

import cn.foxtech.common.domain.vo.RestFulRequestVO;
import cn.foxtech.common.domain.vo.RestFulRespondVO;
import cn.foxtech.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisListManagerClient {
    @Autowired
    private RedisListManagerClientRequest managerRequest;

    @Autowired
    private RedisListManagerClientRespond managerRespond;

    /**
     * 向管理服务，推送restful风格的消息
     *
     * @param uuid   uuid，用于标识唯一性的报文
     * @param uri    uri ，例如："/kernel/manager/device/entity"
     * @param method 方法，例如："post"
     * @param data   数据，对应body参数和url上的查询参数
     */
    public void pushRequest(String uuid, String uri, String method, Object data) {
        this.managerRequest.pushRequest(uuid, uri, method, data);
    }

    public void pushRequest(String uri, String method, Object data) {
        this.managerRequest.pushRequest(uri, method, data);
    }

    public void pushRequest(RestFulRequestVO requestVO) {
        this.managerRequest.pushRequest(requestVO);
    }

    /**
     * 查询响应
     *
     * @param uuid
     * @param timeout
     */
    public RestFulRespondVO queryRespond(String uuid, long timeout) {
        if (uuid == null || uuid.isEmpty()) {
            throw new ServiceException("参数缺失：uuid 不能为空");
        }

        return this.managerRespond.queryRespond(uuid, timeout);
    }
}
