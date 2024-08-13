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

package cn.foxtech.common.rpc.redis.device.client;

import cn.foxtech.device.domain.vo.TaskRequestVO;
import cn.foxtech.device.domain.vo.TaskRespondVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisListDeviceClient {
    @Autowired
    private RedisListDeviceClientRespond deviceRespond;

    @Autowired
    private RedisListDeviceClientRequest deviceRequest;

    @Autowired
    private RedisListDeviceClientReport deviceReport;

    public TaskRespondVO getDeviceRespond(String uuid, long timeout) {
        return this.deviceRespond.get(uuid, timeout);
    }


    public void pushDeviceRequest(TaskRequestVO requestVO) {
        this.deviceRequest.push(requestVO);
    }

    public TaskRespondVO popDeviceReport(long timeout, TimeUnit unit) {
        return this.deviceReport.pop(timeout, unit);
    }


}
