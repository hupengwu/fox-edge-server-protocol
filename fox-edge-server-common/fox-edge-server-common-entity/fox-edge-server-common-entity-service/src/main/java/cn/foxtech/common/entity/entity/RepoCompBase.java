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


import cn.foxtech.common.utils.number.NumberUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class RepoCompBase extends BaseEntity {
    /**
     * 组件仓库：cloud或local
     */
    private String compRepo;
    /**
     * 组件业务类型：jar-decoder/jsp-decoder
     */
    private String compType;
    /**
     * 组件名称：这个是自动化生成
     */
    private String compName;

    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        List<Object> list = new ArrayList<>();
        list.add(this.compRepo);
        list.add(this.compType);
        list.add(this.compName);

        return list;
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_repo", this.compRepo);
        queryWrapper.eq("comp_type", this.compType);
        queryWrapper.eq("comp_name", this.compName);

        return queryWrapper;
    }

    /**
     * 获取业务值
     *
     * @return 对象列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        return list;
    }

    public void bind(RepoCompBase other) {
        super.bind(other);

        this.compRepo = other.compRepo;
        this.compType = other.compType;
        this.compName = other.compName;
    }

    @Override
    public void bind(Map<String, Object> map) {
        super.bind(map);

        this.compRepo = (String) map.get("compRepo");
        this.compType = (String) map.get("compType");
        this.compName = (String) map.get("compName");
    }
}
