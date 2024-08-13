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


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@TableName("tb_device_object")
public class DeviceObjectEntity extends BaseEntity {
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备类型
     */
    private String manufacturer;
    /**
     * 对象名称
     */
    private String objectName;


    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = new ArrayList<>();
        list.add(this.deviceName);
        list.add(this.objectName);


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
        queryWrapper.eq("object_name", this.objectName);

        return queryWrapper;
    }

    public Object makeDeviceWrapperKey() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_name", this.deviceName);

        return queryWrapper;
    }

    /**
     * 获取业务值
     *
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        list.add(this.manufacturer);
        list.add(this.deviceType);
        return list;
    }

    public void bind(DeviceObjectEntity other) {
        this.manufacturer = other.manufacturer;
        this.deviceType = other.deviceType;
        this.deviceName = other.deviceName;
        this.objectName = other.objectName;

        super.bind(other);
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.manufacturer = (String) map.get("manufacturer");
        this.deviceType = (String) map.get("deviceType");
        this.deviceName = (String) map.get("deviceName");
        this.objectName = (String) map.get("objectName");
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            DeviceObjectEntity entity = new DeviceObjectEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
