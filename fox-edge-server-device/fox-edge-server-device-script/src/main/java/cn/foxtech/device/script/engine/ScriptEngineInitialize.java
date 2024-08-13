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

package cn.foxtech.device.script.engine;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.OperateEntity;
import cn.foxtech.common.entity.manager.EntityServiceManager;
import cn.foxtech.common.entity.service.redis.ConsumerRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScriptEngineInitialize {
    @Autowired
    private EntityServiceManager entityServiceManager;

    @Autowired
    private OperateNotify operateNotify;

    public void initialize() {
        // 为操纵绑定JSP引擎
        List<BaseEntity> operateEntityList = this.entityServiceManager.getEntityList(OperateEntity.class);
        for (BaseEntity entity : operateEntityList) {
            this.operateNotify.rebindScriptEngine(entity);
        }

        // 绑定一个类型级别的数据变更通知
        ConsumerRedisService consumerRedisService = (ConsumerRedisService) this.entityServiceManager.getBaseRedisService(OperateEntity.class);
        consumerRedisService.bind(this.operateNotify);
    }
}
