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

package cn.foxtech.channel.common.service;

import cn.foxtech.common.entity.entity.ChannelEntity;
import cn.foxtech.common.entity.entity.ChannelStatusEntity;
import cn.foxtech.common.entity.entity.ConfigEntity;
import cn.foxtech.common.entity.manager.EntityServiceManager;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 数据实体业务
 */
@Component
public class EntityManageService extends EntityServiceManager {
    public void instance(Set<String> others) {
        this.addConsumer(ConfigEntity.class.getSimpleName());
        this.addConsumer(ChannelEntity.class.getSimpleName());
        this.addConsumer(others);

        this.addProducer(ChannelStatusEntity.class.getSimpleName());

        this.getSourceRedis().add(ChannelStatusEntity.class.getSimpleName());
    }
}
