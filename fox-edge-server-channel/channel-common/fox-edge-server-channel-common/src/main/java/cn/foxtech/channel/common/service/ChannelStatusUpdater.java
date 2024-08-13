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

import cn.foxtech.channel.common.properties.ChannelProperties;
import cn.foxtech.common.entity.entity.ChannelEntity;
import cn.foxtech.common.entity.entity.ChannelStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelStatusUpdater {
    @Autowired
    private EntityManageService entityManageService;

    @Autowired
    private ChannelProperties channelProperties;

    public void updateOpenStatus(String channelName, boolean isOpen) {
        ChannelEntity finder = new ChannelEntity();
        finder.setChannelType(this.channelProperties.getChannelType());
        finder.setChannelName(channelName);

        // 查找channel实体
        ChannelEntity channelEntity = this.entityManageService.getEntity(finder.makeServiceKey(), ChannelEntity.class);
        if (channelEntity == null) {
            return;
        }


        ChannelStatusEntity entity = new ChannelStatusEntity();
        entity.bind(channelEntity);

        // 查找状态实体
        ChannelStatusEntity exist = this.entityManageService.getEntity(entity.makeServiceKey(), ChannelStatusEntity.class);
        if (exist != null) {
            exist.setOpen(isOpen);
            this.entityManageService.updateRDEntity(exist);
            return;
        }

        entity.setId(channelEntity.getId());
        entity.setUpdateTime(channelEntity.getUpdateTime());
        entity.setOpen(isOpen);

        this.entityManageService.insertRDEntity(entity);
    }

    public void updateParamStatus(String channelName, String key, Object value) {
        ChannelStatusEntity channelStatusEntity = new ChannelStatusEntity();
        channelStatusEntity.setChannelType(this.channelProperties.getChannelType());
        channelStatusEntity.setChannelName(channelName);

        // 检查：是否存在状态实体
        ChannelStatusEntity exist = this.entityManageService.getEntity(channelStatusEntity.makeServiceKey(), ChannelStatusEntity.class);
        if (exist == null) {
            return;
        }

        // 更新信息到redis
        exist.getChannelParam().put(key, value);
        this.entityManageService.updateRDEntity(exist);
    }
}
