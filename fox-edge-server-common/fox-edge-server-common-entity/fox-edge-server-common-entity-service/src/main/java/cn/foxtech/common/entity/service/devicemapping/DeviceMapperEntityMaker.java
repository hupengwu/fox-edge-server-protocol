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

package cn.foxtech.common.entity.service.devicemapping;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.DeviceMapperEntity;
import cn.foxtech.common.entity.entity.DeviceMapperPo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DevicePo是数据库格式的对象，DeviceEntity是内存格式的对象，两者需要进行转换
 */
public class DeviceMapperEntityMaker {
    /**
     * PO转Entity
     *
     * @param poList po列表
     * @return 对象列表
     */
    public static List<BaseEntity> makePoList2EntityList(List poList) {
        List<BaseEntity> resultList = new ArrayList<>();
        for (Object entity : poList) {
            DeviceMapperPo po = (DeviceMapperPo) entity;


            DeviceMapperEntity result = DeviceMapperEntityMaker.makePo2Entity(po);
            resultList.add(result);
        }

        return resultList;
    }

    public static DeviceMapperPo makeEntity2Po(DeviceMapperEntity entity) {
        DeviceMapperPo result = new DeviceMapperPo();
        result.bind(entity);

        result.setExtendParam(JsonUtils.buildJsonWithoutException(entity.getExtendParam()));
        return result;
    }

    public static DeviceMapperEntity makePo2Entity(DeviceMapperPo entity) {
        DeviceMapperEntity result = new DeviceMapperEntity();
        result.bind(entity);


        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getExtendParam(), Map.class);
            if (params != null) {
                result.setExtendParam(params);
            } else {
                System.out.println("扩展参数转换Json对象失败：" + entity.getMapperName() + ":" + entity.getExtendParam());
            }
        } catch (Exception e) {
            System.out.println("扩展参数转换Json对象失败：" + entity.getMapperName() + ":" + entity.getExtendParam());
            e.printStackTrace();
        }

        return result;
    }
}
