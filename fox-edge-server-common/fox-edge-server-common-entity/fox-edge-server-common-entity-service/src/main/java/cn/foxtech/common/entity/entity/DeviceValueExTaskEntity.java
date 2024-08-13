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

import cn.foxtech.common.utils.MapUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@TableName("tb_device_value_task")
public class DeviceValueExTaskEntity extends DeviceValueExTaskBase {
    /**
     * JSON参数
     */
    private Map<String, Object> taskParam = new HashMap<>();


    /**
     * 业务Key：这个可能不是唯一的，不要用它查找唯一性数据，可以用它来筛选数据
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
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = super.makeServiceValueList();
        list.add(this.taskParam);

        return list;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.taskParam = ((Map<String, Object>) map.getOrDefault("taskParam", new HashMap<>()));
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            DeviceValueExTaskEntity entity = new DeviceValueExTaskEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    public void updateTaskParam(Map<String, Object> input) {
        Map<String, Object> param1 = this.taskParam;
        Map<String, Object> input1 = input;

        // 1级参数
        MapUtils.copyValue(param1, "cacheSize", input1, "cacheSize", 1);
        MapUtils.copyValue(param1, "methodScript", input1, "methodScript", "");
        MapUtils.copyValue(param1, "description", input1, "description", "");
        MapUtils.copyValue(param1, "dataSource", input1, "dataSource", new HashMap<>());

        // 2级参数
        Map<String, Object> param2 = (Map<String, Object>) param1.get("dataSource");
        Map<String, Object> input2 = (Map<String, Object>) input1.get("dataSource");
        MapUtils.copyValue(param2, "deviceType", input2, "deviceType", "");
        MapUtils.copyValue(param2, "manufacturer", input2, "manufacturer", "");
        MapUtils.copyValue(param2, "deviceType", input2, "deviceType", "");
        MapUtils.copyValue(param2, "deviceName", input2, "deviceName", "");
        MapUtils.copyValue(param2, "dataSource", input2, "dataSource", new HashMap<>());

        // 3级参数
        Map<String, Object> param3 = (Map<String, Object>) param2.get("dataObject");
        Map<String, Object> input3 = (Map<String, Object>) input2.get("dataObject");
        MapUtils.copyValue(param2, "dataObject", input2, "dataObject", "");
        Collection objectSet = (Collection) param3.computeIfAbsent("objectName", k -> new HashSet<>());
        Collection objectList = (Collection) input3.computeIfAbsent("objectName", k -> new HashSet<>());
        objectSet.addAll(objectList);
    }

    public void updateScript(String script) {
        this.taskParam.put("methodScript", script);
    }

}
