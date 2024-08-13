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

/**
 * 这是管理服务使用的操作方式
 * 说明：OperateMethodEntity 和 OperateEntity的作用 是区分设备服务和管理服务，两种结构类似，但是管理者/使用者不同的实体，
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OperateEntity extends OperateMethodBase {
    /**
     * 脚本引擎
     */
    private Map<String, Object> engineParam = new HashMap<>();
    /**
     * 扩展参数
     */
    private Map<String, Object> extendParam = new HashMap<>();


    /**
     * 获得bind方法
     *
     * @return 对象列表
     * @throws NoSuchMethodException 异常信息
     */
    public static Method getInitMethod() throws NoSuchMethodException {
        return OperateEntity.class.getMethod("init", OperateMethodEntity.class);
    }

    public void init(OperateMethodEntity other) {
        this.bind(other);
    }

    public List<Object> makeServiceValueList() {
        List<Object> list = super.makeServiceValueList();
        list.add(this.engineParam);
        list.add(this.extendParam);
        return list;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.engineParam = (Map<String, Object>) map.get("engineParam");
        this.extendParam = (Map<String, Object>) map.get("extendParam");
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            OperateEntity entity = new OperateEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
