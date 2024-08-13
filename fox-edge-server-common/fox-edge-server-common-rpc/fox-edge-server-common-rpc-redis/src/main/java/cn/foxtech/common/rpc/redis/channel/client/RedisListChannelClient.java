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

package cn.foxtech.common.rpc.redis.channel.client;

import cn.foxtech.channel.domain.ChannelRequestVO;
import cn.foxtech.channel.domain.ChannelRespondVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisListChannelClient {
    @Autowired
    private RedisListChannelClientRespond channelRespond;

    @Autowired
    private RedisListChannelClientRequest channelRequest;

    @Autowired
    private RedisListChannelClientReport channelReport;

    public ChannelRespondVO getChannelRespond(String channelType, String uuid, long timeout) {
        return this.channelRespond.get(channelType, uuid, timeout);
    }


    public void pushChannelRequest(String channelType, ChannelRequestVO value) {
        this.channelRequest.push(channelType, value);
    }

    public ChannelRespondVO popChannelReport(long timeout, TimeUnit unit) {
        return this.channelReport.pop(timeout, unit);
    }


}
