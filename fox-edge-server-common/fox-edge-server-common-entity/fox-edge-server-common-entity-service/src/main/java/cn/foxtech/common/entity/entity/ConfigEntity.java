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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置实体：各服务需要读取预置的全局配置参数
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ConfigEntity extends ConfigBase {
    /**
     * 数值信息
     */
    private Map<String, Object> configValue = new HashMap<>();
    /**
     * 参数信息
     */
    private Map<String, Object> configParam = new HashMap<>();

    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = super.makeServiceKeyList();

        return list;
    }


    /**
     * 获取业务值
     *
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = super.makeServiceValueList();
        list.add(this.configValue);
        list.add(this.configParam);

        return list;
    }

    public void bind(ConfigEntity other) {
        super.bind(other);

        this.configValue = other.configValue;
        this.configParam = other.configParam;
    }

    public ConfigEntity clone() {
        ConfigEntity clone = new ConfigEntity();

        clone.bind(this);

        clone.configValue = new HashMap<>();
        clone.configParam = new HashMap<>();

        clone.configValue.putAll(this.configValue);
        clone.configParam.putAll(this.configParam);

        return clone;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.configValue = ((Map<String, Object>) map.getOrDefault("configValue", new HashMap<>()));
        this.configParam = ((Map<String, Object>) map.getOrDefault("configParam", new HashMap<>()));
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            ConfigEntity entity = new ConfigEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
