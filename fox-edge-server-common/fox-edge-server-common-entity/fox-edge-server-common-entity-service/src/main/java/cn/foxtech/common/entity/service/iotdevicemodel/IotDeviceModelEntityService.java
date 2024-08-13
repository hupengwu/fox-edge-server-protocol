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

package cn.foxtech.common.entity.service.iotdevicemodel;


import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.IotDeviceModelEntity;
import cn.foxtech.common.entity.entity.IotDeviceModelPo;
import cn.foxtech.common.entity.service.mybatis.BaseEntityService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Getter(value = AccessLevel.PUBLIC)
public class IotDeviceModelEntityService extends BaseEntityService {
    @Autowired(required = false)
    private IotDeviceModelEntityMapper mapper;

    /**
     * 子类将自己的mapper绑定到父类上
     */
    public void bindMapper() {
        super.mapper = this.mapper;
    }

    @Override
    public BaseEntity selectEntity(Long id) {
        IotDeviceModelPo po = (IotDeviceModelPo) super.selectEntity(id);
        if (po == null) {
            return null;
        }

        return IotDeviceModelEntityMaker.makePo2Entity(po);
    }

    @Override
    public List<BaseEntity> selectListBatchIds(List idList) {
        List<BaseEntity> poList = super.selectListBatchIds(idList);

        List<BaseEntity> entityList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            IotDeviceModelPo po = (IotDeviceModelPo) entity;

            IotDeviceModelEntity Device = IotDeviceModelEntityMaker.makePo2Entity(po);
            entityList.add(Device);
        }

        return entityList;
    }


    /**
     * 根据Key特征，查询实体
     */
    @Override
    public List<BaseEntity> selectEntityList() {
        List<BaseEntity> poList = super.selectEntityList();

        List<BaseEntity> entityList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            IotDeviceModelPo po = (IotDeviceModelPo) entity;

            IotDeviceModelEntity Device = IotDeviceModelEntityMaker.makePo2Entity(po);
            entityList.add(Device);
        }

        return entityList;
    }

    /**
     * 插入实体
     *
     * @param entity 实体
     */
    @Override
    public void insertEntity(BaseEntity entity) {
        IotDeviceModelPo IotDeviceModelPo = IotDeviceModelEntityMaker.makeEntity2Po((IotDeviceModelEntity) entity);
        super.insertEntity(IotDeviceModelPo);

        entity.setId(IotDeviceModelPo.getId());
        entity.setCreateTime(IotDeviceModelPo.getCreateTime());
        entity.setUpdateTime(IotDeviceModelPo.getUpdateTime());
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        IotDeviceModelPo IotDeviceModelPo = IotDeviceModelEntityMaker.makeEntity2Po((IotDeviceModelEntity) entity);
        super.updateEntity(IotDeviceModelPo);

        entity.setId(IotDeviceModelPo.getId());
        entity.setCreateTime(IotDeviceModelPo.getCreateTime());
        entity.setUpdateTime(IotDeviceModelPo.getUpdateTime());
    }

    @Override
    public int deleteEntity(BaseEntity entity) {
        IotDeviceModelPo IotDeviceModelPo = IotDeviceModelEntityMaker.makeEntity2Po((IotDeviceModelEntity) entity);
        return super.deleteEntity(IotDeviceModelPo);
    }
}
