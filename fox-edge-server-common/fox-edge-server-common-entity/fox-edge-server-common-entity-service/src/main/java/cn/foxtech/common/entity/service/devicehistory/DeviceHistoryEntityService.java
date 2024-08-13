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

package cn.foxtech.common.entity.service.devicehistory;


import cn.foxtech.common.entity.constant.BaseVOFieldConstant;
import cn.foxtech.common.entity.constant.Constants;
import cn.foxtech.common.entity.constant.DeviceHistoryVOFieldConstant;
import cn.foxtech.common.entity.service.mybatis.LogEntityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeviceHistoryEntityService extends LogEntityService {
    @Autowired(required = false)
    private DeviceHistoryPoMapper mapper;


    /**
     * 子类将自己的mapper绑定到父类上
     */
    public void bindMapper() {
        super.mapper = this.mapper;
    }

    public String getEntityType() {
        return Constants.HistoryEntity;
    }

    private QueryWrapper makeQueryWrapper(Map<String, Object> param) {
        QueryWrapper queryWrapper = new QueryWrapper<>();

        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            BaseVOFieldConstant.makeQueryWrapper(queryWrapper, key, value);


            if (key.equals(DeviceHistoryVOFieldConstant.field_device_id)) {
                queryWrapper.last("device_id " + value);
            }
            if (key.equals(DeviceHistoryVOFieldConstant.field_object_name)) {
                queryWrapper.last("object_name " + value);
            }
        }

        return queryWrapper;
    }

    public Long selectCount(Map<String, Object> param) {
        QueryWrapper queryWrapper = this.makeQueryWrapper(param);
        return mapper.selectCount(queryWrapper);
    }

    /**
     * 删除旧数据，只保留少数的最新的部分数据
     *
     * @param retainCount 需要保留的数据数量
     */
    public void delete(int retainCount) {
        Integer sumCount = mapper.executeSelectCount("SELECT COUNT(1) FROM  tb_device_history");
        if (sumCount <= retainCount) {
            return;
        }

        // 删除旧记录
        String sql = String.format("DELETE FROM  tb_device_history order BY id LIMIT  %d", sumCount - retainCount);
        mapper.executeDelete(sql);
    }
}
