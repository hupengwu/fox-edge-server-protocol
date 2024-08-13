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

package cn.foxtech.common.entity.entity;


import cn.foxtech.common.entity.constant.DeviceValueVOFieldConstant;
import cn.foxtech.common.utils.number.NumberUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class DeviceValueEntity extends BaseEntity {
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型名
     */
    private String deviceType;

    /**
     * 设备参数
     */
    private String manufacturer;

    /**
     * 配置集合
     */
    private Map<String, DeviceObjectValue> params = new HashMap<>();

    public static DeviceValueEntity buildValueEntity(Map<String, Object> deviceValueMap) {
        if (deviceValueMap == null) {
            return null;
        }

        Long id = NumberUtils.makeLong(deviceValueMap.get(DeviceValueVOFieldConstant.field_id));
        Long createTime = NumberUtils.makeLong(deviceValueMap.get(DeviceValueVOFieldConstant.field_create_time));
        Long updateTime = NumberUtils.makeLong(deviceValueMap.get(DeviceValueVOFieldConstant.field_update_time));

        String deviceName = (String) deviceValueMap.get(DeviceValueVOFieldConstant.field_device_name);
        String deviceType = (String) deviceValueMap.get(DeviceValueVOFieldConstant.field_device_type);
        String manufacturer = (String) deviceValueMap.get(DeviceValueVOFieldConstant.field_manufacturer);

        Map<String, DeviceObjectValue> params = new HashMap<>();
        Map<String, Map<String, Object>> values = (Map<String, Map<String, Object>>) deviceValueMap.getOrDefault(DeviceValueVOFieldConstant.field_params, new HashMap<>());
        for (String key : values.keySet()) {
            DeviceObjectValue objectValue = new DeviceObjectValue();
            Map<String, Object> map = values.get(key);
            if (map == null) {
                params.put(key, null);
                continue;
            }

            objectValue.setValue(map.get(DeviceValueVOFieldConstant.field_value_value));
            objectValue.setTime(NumberUtils.makeLong(map.get(DeviceValueVOFieldConstant.field_value_time)));

            params.put(key, objectValue);
        }

        DeviceValueEntity valueEntity = new DeviceValueEntity();
        valueEntity.setId(id);
        valueEntity.setCreateTime(createTime);
        valueEntity.setUpdateTime(updateTime);
        valueEntity.setDeviceName(deviceName);
        valueEntity.setDeviceType(deviceType);
        valueEntity.setManufacturer(manufacturer);
        valueEntity.setParams(params);


        return valueEntity;
    }

    /**
     * 将带时间戳的params结构，转换为没有时间戳的value
     *
     * @param params 带时间戳的数值对象
     * @return 不带时间戳的数值
     */
    public static Map<String, Object> buildValue(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        for (String key : params.keySet()) {
            Map<String, Object> param = (Map<String, Object>) params.get(key);
            Object value = param.get(DeviceValueVOFieldConstant.field_value_value);
            map.put(key, value);
        }

        return map;
    }


    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = new ArrayList<>();
        list.add(this.deviceName);

        return list;
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_name", this.deviceName);

        return queryWrapper;
    }

    /**
     * 获取业务值
     *
     * @return 对象实体
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        list.add(this.params);
        list.add(this.deviceType);
        list.add(this.manufacturer);
        return list;
    }

    public void bind(DeviceValueEntity other) {
        super.bind(other);

        this.deviceName = other.deviceName;
        this.deviceType = other.deviceType;
        this.manufacturer = other.manufacturer;
        this.params = other.params;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.deviceName = ((String) map.get("deviceName"));
        this.deviceType = ((String) map.get("deviceType"));
        this.manufacturer = ((String) map.get("manufacturer"));

        this.getParams().clear();
        Map<String, Object> values = (Map<String, Object>) map.get("params");
        for (String key : values.keySet()) {
            Map<String, Object> value = (Map<String, Object>) values.get(key);
            if (value == null) {
                continue;
            }

            DeviceObjectValue objectValue = new DeviceObjectValue();
            objectValue.bind(value);

            this.getParams().put(key, objectValue);
        }
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            DeviceValueEntity entity = new DeviceValueEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
