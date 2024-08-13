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

package cn.foxtech.common.entity.service.config;


import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.ConfigEntity;
import cn.foxtech.common.entity.entity.ConfigPo;
import cn.foxtech.common.entity.service.mybatis.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigEntityService extends BaseEntityService {
    @Autowired(required = false)
    private ConfigMapper mapper;

    /**
     * 子类将自己的mapper绑定到父类上
     */
    public void bindMapper() {
        super.mapper = this.mapper;
    }

    /**
     * 根据Key特征，查询实体
     */
    @Override
    public List<BaseEntity> selectEntityList() {
        List<BaseEntity> recordList = super.selectEntityList();

        List<BaseEntity> configList = new ArrayList<>();
        for (BaseEntity entity : recordList) {
            ConfigPo po = (ConfigPo) entity;

            ConfigEntity config = ConfigEntityMaker.makePo2Entity(po);
            configList.add(config);
        }

        return configList;
    }

    /**
     * 插入实体
     *
     * @param entity 实体
     */
    @Override
    public void insertEntity(BaseEntity entity) {
        ConfigPo configPo = ConfigEntityMaker.makeEntity2Po((ConfigEntity) entity);
        super.insertEntity(configPo);

        entity.setId(configPo.getId());
        entity.setCreateTime(configPo.getCreateTime());
        entity.setUpdateTime(configPo.getUpdateTime());
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        ConfigPo configPo = ConfigEntityMaker.makeEntity2Po((ConfigEntity) entity);
        super.updateEntity(configPo);

        entity.setId(configPo.getId());
        entity.setCreateTime(configPo.getCreateTime());
        entity.setUpdateTime(configPo.getUpdateTime());
    }

    @Override
    public int deleteEntity(BaseEntity entity) {
        ConfigPo ConfigPo = ConfigEntityMaker.makeEntity2Po((ConfigEntity) entity);
        return super.deleteEntity(ConfigPo);
    }
}
