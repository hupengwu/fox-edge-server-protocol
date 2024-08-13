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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@TableName("tb_channel")
public class ChannelEntity extends ChannelBase {
    /**
     * 参数信息
     */
    private Map<String, Object> channelParam = new HashMap<>();
    /**
     * 扩展参数（非工作参数）：主要是一些备注信息，它并不参与fox-edge本身的工作
     */
    private Map<String, Object> extendParam = new HashMap<>();


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
        list.add(this.channelParam);
        list.add(this.extendParam);

        return list;
    }

    public void bind(ChannelEntity other) {
        super.bind(other);

        this.channelParam = other.channelParam;
        this.extendParam = other.extendParam;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.channelParam = ((Map<String, Object>) map.getOrDefault("channelParam", new HashMap<>()));
        this.extendParam = ((Map<String, Object>) map.getOrDefault("extendParam", new HashMap<>()));
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            ChannelEntity entity = new ChannelEntity();
            entity.bind(map);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
