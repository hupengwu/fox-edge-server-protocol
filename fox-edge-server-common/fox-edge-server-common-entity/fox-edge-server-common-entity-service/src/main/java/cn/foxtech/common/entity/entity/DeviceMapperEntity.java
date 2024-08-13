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


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class DeviceMapperEntity extends DeviceMapperBase {
    /**
     * 扩展参数（非工作参数）：主要是一些备注信息，它并不参与fox-edge本身的工作
     */
    private Map<String, Object> extendParam = new HashMap<>();

    /**
     * 获得init方法
     *
     * @return 初始化方法
     * @throws NoSuchMethodException 方法异常
     */
    public static Method getInitMethod() throws NoSuchMethodException {
        return DeviceMapperEntity.class.getMethod("init", DeviceObjInfEntity.class);
    }

    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        return super.makeServiceKeyList();
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        return super.makeWrapperKey();
    }

    /**
     * 获取业务值
     *
     * @return 数值成员列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = super.makeServiceValueList();
        list.add(this.extendParam);
        return list;
    }

    public void bind(DeviceMapperEntity other) {
        super.bind(other);

        this.extendParam = other.extendParam;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.extendParam = ((Map<String, Object>) map.getOrDefault("extendParam", new HashMap<>()));
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            DeviceMapperEntity entity = new DeviceMapperEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
