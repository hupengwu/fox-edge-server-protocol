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

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.UserRoleEntity;
import cn.foxtech.common.entity.entity.UserRolePo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class UserRoleMaker {
    /**
     * PO转Entity
     *
     * @param poList PO列表
     * @return 实体列表
     */
    public static List<BaseEntity> makePoList2EntityList(List<BaseEntity> poList) {
        List<BaseEntity> resultList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            UserRolePo po = (UserRolePo) entity;


            UserRoleEntity result = UserRoleMaker.makePo2Entity(po);
            resultList.add(result);
        }

        return resultList;
    }

    public static UserRolePo makeEntity2Po(UserRoleEntity entity) {
        UserRolePo result = new UserRolePo();
        result.bind(entity);


        result.setRole(JsonUtils.buildJsonWithoutException(entity.getParams()));
        return result;
    }

    public static UserRoleEntity makePo2Entity(UserRolePo entity) {
        UserRoleEntity result = new UserRoleEntity();
        result.bind(entity);


        try {
            List<String> objList = JsonUtils.buildObject(entity.getRole(), List.class);
            if (objList != null) {
                result.getParams().clear();
                result.getParams().addAll(objList);
            } else {
                System.out.println("触发器配置参数转换Json对象失败：" + entity.getRole());
            }
        } catch (Exception e) {
            System.out.println("触发器配置参数转换Json对象失败：" + entity.getRole());
            e.printStackTrace();
        }

        return result;
    }
}
