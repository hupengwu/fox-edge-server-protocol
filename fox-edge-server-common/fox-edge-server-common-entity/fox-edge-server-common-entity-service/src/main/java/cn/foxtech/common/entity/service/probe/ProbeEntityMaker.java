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

package cn.foxtech.common.entity.service.probe;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.ProbeEntity;
import cn.foxtech.common.entity.entity.ProbePo;
import cn.foxtech.common.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DeviceConfigPo是数据库格式的对象，DeviceConfigEntity是内存格式的对象，两者需要进行转换
 */
public class ProbeEntityMaker {
    /**
     * PO转Entity
     *
     * @param poList PO列表
     * @return 实体列表
     */
    public static List<BaseEntity> makePoList2EntityList(List<BaseEntity> poList) {
        List<BaseEntity> entityList = new ArrayList<>();
        for (BaseEntity po : poList) {
            ProbePo probePo = (ProbePo) po;

            ProbeEntity entity = ProbeEntityMaker.makePo2Entity(probePo);
            entityList.add(entity);
        }

        return entityList;
    }

    public static ProbePo makeEntity2Po(ProbeEntity entity) {
        ProbePo result = new ProbePo();
        result.setDeviceName(entity.getDeviceName());
        result.setDeviceType(entity.getDeviceType());
        result.setManufacturer(entity.getManufacturer());
        result.setOperateName(entity.getOperateName());

        result.setId(entity.getId());
        result.setCreateTime(entity.getCreateTime());
        result.setUpdateTime(entity.getUpdateTime());

        result.setOperateParam(JsonUtils.buildJsonWithoutException(entity.getParams()));
        result.setOperatePeriod(JsonUtils.buildJsonWithoutException(entity.getPeriod()));
        return result;
    }

    public static ProbeEntity makePo2Entity(ProbePo entity) {
        ProbeEntity result = new ProbeEntity();
        result.setDeviceName(entity.getDeviceName());
        result.setDeviceType(entity.getDeviceType());
        result.setManufacturer(entity.getManufacturer());
        result.setOperateName(entity.getOperateName());

        result.setId(entity.getId());
        result.setCreateTime(entity.getCreateTime());
        result.setUpdateTime(entity.getUpdateTime());

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getOperateParam(), Map.class);
            if (params != null) {
                result.setParams(params);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getOperateParam());
            }

            Map<String, Object> period = JsonUtils.buildObject(entity.getOperatePeriod(), Map.class);
            if (period != null) {
                result.setPeriod(period);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getOperatePeriod());
            }
        } catch (Exception e) {
            System.out.println("设备周期参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getOperateParam());
            e.printStackTrace();
        }

        return result;
    }
}
