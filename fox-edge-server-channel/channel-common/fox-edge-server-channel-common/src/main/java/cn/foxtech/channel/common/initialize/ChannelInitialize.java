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

package cn.foxtech.channel.common.initialize;

import cn.foxtech.channel.common.properties.ChannelProperties;
import cn.foxtech.channel.common.scheduler.EntityManageScheduler;
import cn.foxtech.channel.common.scheduler.RedisListRespondScheduler;
import cn.foxtech.channel.common.scheduler.RedisListReportToScheduler;
import cn.foxtech.channel.common.service.EntityManageService;
import cn.foxtech.common.rpc.redis.channel.server.RedisListChannelServer;
import cn.foxtech.common.status.ServiceStatusScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 初始化
 */
@Component
public class ChannelInitialize {

    /**
     * redis的通道状态更新
     */
    @Autowired
    private EntityManageScheduler entityManageScheduler;

    @Autowired
    private ServiceStatusScheduler serviceStatusScheduler;

    @Autowired
    private RedisListReportToScheduler redisListReportToScheduler;

    @Autowired
    private RedisListRespondScheduler redisListRespondScheduler;

    /**
     * 实体状态管理线程
     */
    @Autowired
    private EntityManageService entityManageService;

    @Autowired
    private ChannelProperties channelProperties;

    @Autowired
    private RedisListChannelServer redisListChannelServer;


    public void initialize() {
        Set<String> others = new HashSet<>();
        initialize(others);
    }

    public void initialize(Set<String> others) {
        // 启动进程状态通知
        this.serviceStatusScheduler.initialize();
        this.serviceStatusScheduler.schedule();

        // 读取应用程序的配置
        this.channelProperties.initialize();

        // 绑定通道类型
        this.redisListChannelServer.setChannelType(this.channelProperties.getChannelType());

        // 装载实体
        this.entityManageService.instance(others);
        this.entityManageService.initLoadEntity();

        // 启动独立的Topic发布线程：该线程不能阻塞
        this.redisListReportToScheduler.schedule();
        this.redisListRespondScheduler.schedule();

        // 启动实体同步线程：该线程允许阻塞
        this.entityManageScheduler.schedule();
    }
}
