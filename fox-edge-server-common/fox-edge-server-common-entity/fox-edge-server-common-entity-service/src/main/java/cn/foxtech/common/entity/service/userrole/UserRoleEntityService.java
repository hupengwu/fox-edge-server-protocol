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

package cn.foxtech.common.entity.service.userrole;


import cn.foxtech.common.entity.constant.BaseVOFieldConstant;
import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.UserRoleEntity;
import cn.foxtech.common.entity.entity.UserRolePo;
import cn.foxtech.common.entity.service.mybatis.BaseEntityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.foxtech.common.entity.constant.UserRoleVOFieldConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserRoleEntityService extends BaseEntityService {
    @Autowired(required = false)
    private UserRoleMapper mapper;


    /**
     * 子类将自己的mapper绑定到父类上
     */
    public void bindMapper() {
        super.mapper = this.mapper;
    }

    @Override
    public List<BaseEntity> selectEntityList() {
        List<BaseEntity> poList = super.selectEntityList();
        return UserRoleMaker.makePoList2EntityList(poList);
    }

    /**
     * 插入实体
     *
     * @param entity 实体
     */
    @Override
    public void insertEntity(BaseEntity entity) {
        UserRolePo userPo = UserRoleMaker.makeEntity2Po((UserRoleEntity) entity);
        super.insertEntity(userPo);

        entity.setId(userPo.getId());
        entity.setCreateTime(userPo.getCreateTime());
        entity.setUpdateTime(userPo.getUpdateTime());
    }

    @Override
    public void updateEntity(BaseEntity entity) {
        UserRolePo userPo = UserRoleMaker.makeEntity2Po((UserRoleEntity) entity);
        super.updateEntity(userPo);

        entity.setId(userPo.getId());
        entity.setCreateTime(userPo.getCreateTime());
        entity.setUpdateTime(userPo.getUpdateTime());
    }

    @Override
    public int deleteEntity(BaseEntity entity) {
        UserRolePo userPo = UserRoleMaker.makeEntity2Po((UserRoleEntity) entity);
        return super.deleteEntity(userPo);
    }

    public List<UserRolePo> selectEntityListByPage(Map<String, Object> param) {
        QueryWrapper queryWrapper = new QueryWrapper<>();

        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            BaseVOFieldConstant.makeQueryWrapper(queryWrapper, key, value);

            // 各字段条件
            if (key.equals(UserRoleVOFieldConstant.field_name)) {
                queryWrapper.eq("name", value);
            }
        }

        List<UserRolePo> entitys = mapper.selectList(queryWrapper);

        return entitys;
    }
}
