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

package cn.foxtech.common.entity.service.operatetask.channel;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.OperateChannelTaskEntity;
import cn.foxtech.common.entity.entity.OperateChannelTaskPo;
import cn.foxtech.common.entity.service.mybatis.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperateChannelTaskEntityService extends BaseEntityService {
    @Autowired(required = false)
    private OperateChannelTaskEntityMapper mapper;


    /**
     * 子类将自己的mapper绑定到父类上
     */
    public void bindMapper() {
        super.mapper = this.mapper;
    }


    @Override
    public List<BaseEntity> selectEntityList() {
        List<BaseEntity> poList = super.selectEntityList();
        return OperateChannelTaskMaker.makePoList2EntityList(poList);
    }

    /**
     * 插入实体
     *
     * @param entity 实体
     */
    @Override
    public void insertEntity(BaseEntity entity) {
        OperateChannelTaskPo deviceConfigPo = OperateChannelTaskMaker.makeEntity2Po((OperateChannelTaskEntity) entity);
        super.insertEntity(deviceConfigPo);

        entity.setId(deviceConfigPo.getId());
        entity.setCreateTime(deviceConfigPo.getCreateTime());
        entity.setUpdateTime(deviceConfigPo.getUpdateTime());
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        OperateChannelTaskPo userPo = OperateChannelTaskMaker.makeEntity2Po((OperateChannelTaskEntity) entity);
        super.updateEntity(userPo);

        entity.setId(userPo.getId());
        entity.setCreateTime(userPo.getCreateTime());
        entity.setUpdateTime(userPo.getUpdateTime());
    }

    @Override
    public int deleteEntity(BaseEntity entity) {
        OperateChannelTaskPo deviceConfigPo = OperateChannelTaskMaker.makeEntity2Po((OperateChannelTaskEntity) entity);
        return super.deleteEntity(deviceConfigPo);
    }
}
