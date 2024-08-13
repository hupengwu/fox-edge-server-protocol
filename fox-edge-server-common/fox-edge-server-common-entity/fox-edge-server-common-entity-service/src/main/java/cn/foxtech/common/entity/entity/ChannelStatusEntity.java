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
public class ChannelStatusEntity extends BaseEntity {
    /**
     * 通道名称
     */
    private String channelName;
    /**
     * 通道类型
     */
    private String channelType;


    /**
     * 是否打开
     */
    private boolean open = false;

    /**
     * 状态参数
     */
    private Map<String, Object> channelParam = new HashMap<>();

    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = new ArrayList<>();
        list.add(this.channelType);
        list.add(this.channelName);


        return list;
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("channel_name", this.channelName);
        queryWrapper.eq("channel_type", this.channelType);

        return queryWrapper;
    }

    /**
     * 获取业务值
     *
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        list.add(this.open);
        list.add(this.channelParam);
        return list;
    }

    public void bind(ChannelEntity channelEntity) {
        this.setId(channelEntity.getId());
        this.setCreateTime(channelEntity.getCreateTime());
        this.setUpdateTime(channelEntity.getUpdateTime());

        this.channelType = channelEntity.getChannelType();
        this.channelName = channelEntity.getChannelName();
    }

    @Override
    public BaseEntity build(Map<String, Object> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            ChannelStatusEntity entity = new ChannelStatusEntity();
            entity.bind(map);

            entity.channelName = ((String) map.get("channelName"));
            entity.channelType = ((String) map.get("channelType"));
            entity.open = ((Boolean) map.getOrDefault("open", false));
            entity.channelParam = ((Map<String, Object>) map.getOrDefault("channelParam", new HashMap<>()));

            return entity;
        } catch (Exception e) {
            return null;
        }
    }
}
