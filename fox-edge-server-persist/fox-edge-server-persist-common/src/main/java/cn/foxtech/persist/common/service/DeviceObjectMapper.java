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

package cn.foxtech.persist.common.service;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.DeviceMapperEntity;
import cn.foxtech.common.utils.MapUtils;
import cn.foxtech.common.utils.pair.Pair;
import cn.foxtech.device.protocol.v1.utils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DeviceObjectMapper {
    @Autowired
    private EntityManageService entityManageService;

    /**
     * 映射表
     */
    private Map<String, Object> mapperEntityMap;

    public Pair<String, Integer> getValue(String manufacturer, String deviceType, String objectName) {
        return (Pair<String, Integer>) MapUtils.getValue(this.mapperEntityMap, manufacturer, deviceType, objectName);
    }

    public Map<String, Object> getValue(String manufacturer, String deviceType) {
        return (Map<String, Object>) MapUtils.getValue(this.mapperEntityMap, manufacturer, deviceType);
    }

    /**
     * 从Redis读取来自管理服务的DeviceMapperEntity的配置信息，缓存到本地内存，
     * 方便后续数据加工的时候使用
     */
    public void syncEntity() {
        // 检查：是否有重新状态的配置到达
        Long updateTime = this.entityManageService.removeReloadedFlag(DeviceMapperEntity.class.getSimpleName());
        if (updateTime == null && this.mapperEntityMap != null) {
            return;
        }

        // 取出重新状态的配置
        List<BaseEntity> entityList = this.entityManageService.getEntityList(DeviceMapperEntity.class);
        Map<String, Object> map = new HashMap<>();
        for (BaseEntity entity : entityList) {
            DeviceMapperEntity mapperEntity = (DeviceMapperEntity) entity;

            // 检查数据
            if (MethodUtils.hasEmpty(mapperEntity.getManufacturer(), mapperEntity.getDeviceType(), mapperEntity.getObjectName(), mapperEntity.getMapperName(), mapperEntity.getMapperMode())) {
                continue;
            }

            // 构造数据
            Pair<String, Integer> pair = new Pair(mapperEntity.getMapperName(), mapperEntity.getMapperMode());
            MapUtils.setValue(map, mapperEntity.getManufacturer(), mapperEntity.getDeviceType(), mapperEntity.getObjectName(), pair);
        }

        this.mapperEntityMap = map;
    }
}
