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

package cn.foxtech.rpc.sdk.redis;

import cn.foxtech.common.rpc.redis.channel.client.RedisListChannelClient;
import cn.foxtech.common.rpc.redis.device.client.RedisListDeviceClient;
import cn.foxtech.common.rpc.redis.manager.client.RedisListManagerClient;
import cn.foxtech.common.rpc.redis.persist.client.RedisListPersistClient;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RpcSdkRedisClient {
    @Autowired
    @Getter(value = AccessLevel.PUBLIC)
    private RedisListChannelClient channelClient;

    @Autowired
    @Getter(value = AccessLevel.PUBLIC)
    private RedisListDeviceClient deviceClient;

    @Autowired
    @Getter(value = AccessLevel.PUBLIC)
    private RedisListManagerClient managerClient;

    @Autowired
    @Getter(value = AccessLevel.PUBLIC)
    private RedisListPersistClient persistClient;
}
