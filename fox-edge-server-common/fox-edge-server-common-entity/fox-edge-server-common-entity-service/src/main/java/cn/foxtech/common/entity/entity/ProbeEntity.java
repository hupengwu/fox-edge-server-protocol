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
public class ProbeEntity extends BaseEntity {
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备厂商
     */
    private String manufacturer;

    /**
     * 操作命令
     */
    private String operateName;

    /**
     * 配置集合
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 操作周期
     */
    private Map<String, Object> period = new HashMap<>();


    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = new ArrayList<>();
        list.add(this.getId());

        return list;
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", this.getId());

        return queryWrapper;
    }


    /**
     * 获取业务值
     *
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        list.add(this.period);
        return list;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.deviceName = (String) map.get("deviceName");
        this.deviceType = (String) map.get("deviceType");
        this.manufacturer = (String) map.get("manufacturer");
        this.operateName = (String) map.get("operateName");
        this.params = (Map<String, Object>) map.getOrDefault("params", new HashMap<>());
        this.period = (Map<String, Object>) map.getOrDefault("period", new HashMap<>());
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            ProbeEntity entity = new ProbeEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }

}
