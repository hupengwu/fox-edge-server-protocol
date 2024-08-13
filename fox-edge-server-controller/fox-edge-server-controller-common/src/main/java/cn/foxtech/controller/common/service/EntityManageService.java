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

package cn.foxtech.controller.common.service;

import cn.foxtech.common.entity.entity.*;
import cn.foxtech.common.entity.entity.*;
import cn.foxtech.common.entity.manager.EntityServiceManager;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 实体管理操作：提供设备信息查询和构造服务
 */
@Component
public class EntityManageService extends EntityServiceManager {
    public void instance() {
        Set<String> consumer = this.entityRedisComponent.getConsumer();
        Set<String> reader = this.entityRedisComponent.getReader();

        consumer.add(OperateEntity.class.getSimpleName());
        consumer.add(DeviceEntity.class.getSimpleName());
        consumer.add(ConfigEntity.class.getSimpleName());
        consumer.add(OperateMonitorTaskEntity.class.getSimpleName());

        reader.add(DeviceValueEntity.class.getSimpleName());
    }
}
