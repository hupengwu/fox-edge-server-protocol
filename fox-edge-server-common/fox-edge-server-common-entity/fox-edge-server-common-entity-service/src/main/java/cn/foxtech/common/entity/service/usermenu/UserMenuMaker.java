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

package cn.foxtech.common.entity.service.usermenu;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.UserMenuEntity;
import cn.foxtech.common.entity.entity.UserMenuPo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class UserMenuMaker {
    /**
     * PO转Entity
     *
     * @param poList PO列表
     * @return 实体列表
     */
    public static List<BaseEntity> makePoList2EntityList(List<BaseEntity> poList) {
        List<BaseEntity> resultList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            UserMenuPo po = (UserMenuPo) entity;


            UserMenuEntity result = UserMenuMaker.makePo2Entity(po);
            resultList.add(result);
        }

        return resultList;
    }

    public static UserMenuPo makeEntity2Po(UserMenuEntity entity) {
        UserMenuPo result = new UserMenuPo();
        result.bind(entity);


        result.setMenu(JsonUtils.buildJsonWithoutException(entity.getParams()));
        return result;
    }

    public static UserMenuEntity makePo2Entity(UserMenuPo entity) {
        UserMenuEntity result = new UserMenuEntity();
        result.bind(entity);


        try {
            List<String> objList = JsonUtils.buildObject(entity.getMenu(), List.class);
            if (objList != null) {
                result.getParams().clear();
                result.getParams().addAll(objList);
            } else {
                System.out.println("触发器配置参数转换Json对象失败：" + entity.getMenu());
            }
        } catch (Exception e) {
            System.out.println("触发器配置参数转换Json对象失败：" + entity.getMenu());
            e.printStackTrace();
        }

        return result;
    }
}
