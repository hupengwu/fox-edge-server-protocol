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

package cn.foxtech.common.rpc.redis.channel.server;

import cn.foxtech.channel.domain.ChannelRequestVO;
import cn.foxtech.channel.domain.ChannelRespondVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisListChannelServer {
    @Autowired
    private RedisListChannelServerRequest channelRequest;

    @Autowired
    private RedisListChannelServerRespond channelRespond;

    @Autowired
    private RedisListChannelServerReport channelReport;

    public void setChannelType(String channelType){
        this.channelRequest.setChannelType(channelType);
        this.channelRespond.setChannelType(channelType);
    }

    public ChannelRequestVO popChannelRequest(long timeout, TimeUnit unit) {
        return this.channelRequest.pop(timeout, unit);
    }

    public void pushChannelRespond(String uuid, ChannelRespondVO respondVO) {
        this.channelRespond.set(uuid, respondVO);
    }

    public void pushChannelReport(ChannelRespondVO respondVO) {
        this.channelReport.push(respondVO);
    }

}
