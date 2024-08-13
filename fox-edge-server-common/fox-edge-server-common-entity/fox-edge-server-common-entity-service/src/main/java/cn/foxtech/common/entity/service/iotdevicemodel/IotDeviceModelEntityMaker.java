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
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DeviceModelPo是数据库格式的对象，DeviceModelEntity是内存格式的对象，两者需要进行转换
 */
public class IotDeviceModelEntityMaker {
    /**
     * PO转Entity
     *
     * @param poList po列表
     * @return 对象列表
     */
    public static List<BaseEntity> makePoList2EntityList(List poList) {
        List<BaseEntity> resultList = new ArrayList<>();
        for (Object entity : poList) {
            IotDeviceModelPo po = (IotDeviceModelPo) entity;


            IotDeviceModelEntity result = IotDeviceModelEntityMaker.makePo2Entity(po);
            resultList.add(result);
        }

        return resultList;
    }

    public static IotDeviceModelPo makeEntity2Po(IotDeviceModelEntity entity) {
        IotDeviceModelPo result = new IotDeviceModelPo();
        result.bind(entity);

        result.setServiceParam(JsonUtils.buildJsonWithoutException(entity.getServiceParam()));
        result.setModelSchema(JsonUtils.buildJsonWithoutException(entity.getModelSchema()));
        return result;
    }

    public static IotDeviceModelEntity makePo2Entity(IotDeviceModelPo entity) {
        IotDeviceModelEntity result = new IotDeviceModelEntity();
        result.bind(entity);

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getServiceParam(), Map.class);
            if (params != null) {
                result.setServiceParam(params);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getServiceParam() + ":" + entity.getServiceParam());
            }
        } catch (Exception e) {
            System.out.println("设备配置参数转换Json对象失败：" + entity.getModelName() + ":" + entity.getServiceParam());
            e.printStackTrace();
        }

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getModelSchema(), Map.class);
            if (params != null) {
                result.setModelSchema(params);
            } else {
                System.out.println("设备扩展参数转换Json对象失败：" + entity.getModelName() + ":" + entity.getModelSchema());
            }
        } catch (Exception e) {
            System.out.println("设备扩展参数转换Json对象失败：" + entity.getModelName() + ":" + entity.getModelSchema());
            e.printStackTrace();
        }

        return result;
    }
}
