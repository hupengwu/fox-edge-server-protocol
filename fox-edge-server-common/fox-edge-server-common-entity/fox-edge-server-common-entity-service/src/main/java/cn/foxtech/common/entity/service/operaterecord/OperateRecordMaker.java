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

package cn.foxtech.common.entity.service.operaterecord;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.utils.json.JsonUtils;
import cn.foxtech.common.entity.entity.OperateRecordEntity;
import cn.foxtech.common.entity.entity.OperateRecordPo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OperateRecordPo是数据库格式的对象，OperateRecordEntity是内存格式的对象，两者需要进行转换
 */
public class OperateRecordMaker {
    /**
     * PO转Entity
     *
     * @param poList po列表
     * @return 实体列表
     */
    public static List<OperateRecordEntity> makePoList2EntityList(List<OperateRecordPo> poList) {
        List<OperateRecordEntity> operateRecordList = new ArrayList<>();
        for (BaseEntity entity : poList) {
            OperateRecordPo po = (OperateRecordPo) entity;

            OperateRecordEntity config = OperateRecordMaker.makePo2Entity(po);
            operateRecordList.add(config);
        }

        return operateRecordList;
    }

    public static OperateRecordPo makeEntity2Po(OperateRecordEntity entity) {
        OperateRecordPo result = new OperateRecordPo();
        result.bind(entity);

        result.setRecordParam(JsonUtils.buildJsonWithoutException(entity.getRecordParam()));
        result.setRecordData(JsonUtils.buildJsonWithoutException(entity.getRecordData()));
        return result;
    }

    public static OperateRecordEntity makePo2Entity(OperateRecordPo entity) {
        OperateRecordEntity result = new OperateRecordEntity();
        result.bind(entity);

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getRecordData(), Map.class);
            if (params != null) {
                result.setRecordData(params);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getRecordData());
            }
        } catch (Exception e) {
            System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getRecordData());
            e.printStackTrace();
        }

        try {
            Map<String, Object> params = JsonUtils.buildObject(entity.getRecordParam(), Map.class);
            if (params != null) {
                result.setRecordParam(params);
            } else {
                System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getRecordParam());
            }
        } catch (Exception e) {
            System.out.println("设备配置参数转换Json对象失败：" + entity.getDeviceName() + ":" + entity.getRecordParam());
            e.printStackTrace();
        }

        return result;
    }
}
