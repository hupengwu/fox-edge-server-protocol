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

package cn.foxtech.common.entity.service.userpermission;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.UserPermissionEntity;
import cn.foxtech.common.entity.entity.UserPermissionPo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class UserPermissionMaker {
    /**
     * PO转Entity
     *
     * @param poList PO列表
     * @return 实体列表
     */
    public static List<BaseEntity> makePoList2EntityList(List<BaseEntity> poList) {
        List<BaseEntity> deviceConfigList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            UserPermissionPo po = (UserPermissionPo) entity;


            UserPermissionEntity config = UserPermissionMaker.makePo2Entity(po);
            deviceConfigList.add(config);
        }

        return deviceConfigList;
    }

    public static UserPermissionPo makeEntity2Po(UserPermissionEntity entity) {
        UserPermissionPo result = new UserPermissionPo();
        result.bind(entity);


        result.setPermission(JsonUtils.buildJsonWithoutException(entity.getParams()));
        return result;
    }

    public static UserPermissionEntity makePo2Entity(UserPermissionPo entity) {
        UserPermissionEntity result = new UserPermissionEntity();
        result.bind(entity);


        try {
            List<String> roleList = JsonUtils.buildObject(entity.getPermission(), List.class);
            if (roleList != null) {
                result.getParams().clear();
                result.getParams().addAll(roleList);
            } else {
                System.out.println("触发器配置参数转换Json对象失败：" + entity.getPermission());
            }
        } catch (Exception e) {
            System.out.println("触发器配置参数转换Json对象失败：" + entity.getPermission());
            e.printStackTrace();
        }

        return result;
    }
}
