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

package cn.foxtech.common.entity.service.devicevalue.task;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.DeviceValueExTaskEntity;
import cn.foxtech.common.entity.entity.DeviceValueExTaskPo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OperateTemplatePo是数据库格式的对象，OperateTemplateEntity是内存格式的对象，两者需要进行转换
 */
public class DeviceValueExTaskMaker {
    /**
     * PO转Entity
     *
     * @param poList PO列表
     * @return 实体列表
     */
    public static List<BaseEntity> makePoList2EntityList(List<BaseEntity> poList) {
        List<BaseEntity> operateRecordList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            DeviceValueExTaskPo po = (DeviceValueExTaskPo) entity;

            DeviceValueExTaskEntity config = DeviceValueExTaskMaker.makePo2Entity(po);
            operateRecordList.add(config);
        }

        return operateRecordList;
    }

    public static DeviceValueExTaskPo makeEntity2Po(DeviceValueExTaskEntity entity) {
        DeviceValueExTaskPo result = new DeviceValueExTaskPo();
        result.bind(entity);

        result.setTaskParam(JsonUtils.buildJsonWithoutException(entity.getTaskParam()));
        return result;
    }

    public static DeviceValueExTaskEntity makePo2Entity(DeviceValueExTaskPo entity) {
        DeviceValueExTaskEntity result = new DeviceValueExTaskEntity();
        result.bind(entity);

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getTaskParam(), Map.class);
            if (params != null) {
                result.setTaskParam(params);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getTaskName() + ":" + entity.getTaskParam());
            }
        } catch (Exception e) {
            System.out.println("设备配置参数转换Json对象失败：" + entity.getTaskName() + ":" + entity.getTaskParam());
            e.printStackTrace();
        }

        return result;
    }
}
