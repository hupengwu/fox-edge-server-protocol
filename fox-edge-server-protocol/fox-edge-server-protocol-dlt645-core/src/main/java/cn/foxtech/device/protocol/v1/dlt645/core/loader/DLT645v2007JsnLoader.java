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

package cn.foxtech.device.protocol.v1.dlt645.core.loader;

import cn.foxtech.device.protocol.v1.dlt645.core.entity.DLT645DataEntity;
import cn.foxtech.device.protocol.v1.dlt645.core.entity.DLT645DataFormatEntity;
import cn.foxtech.device.protocol.v1.dlt645.core.entity.DLT645v2007DataEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HoldingRegisters数据实体的数据模板
 */
@Data
public class DLT645v2007JsnLoader {
    /**
     * 从CSV文件中装载映射表
     */
    public List<DLT645DataEntity> loadJsnModel(List<Map<String, Object>> rows) {
        // 将文件记录组织到map中
        List<DLT645DataEntity> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            try {
                DLT645v2007DataEntity entity = new DLT645v2007DataEntity();
                entity.setName((String) row.get("name"));
                entity.setDi0((byte) Integer.parseInt(row.get("di0").toString(), 16));
                entity.setDi1((byte) Integer.parseInt(row.get("di1").toString(), 16));
                entity.setDi2((byte) Integer.parseInt(row.get("di2").toString(), 16));
                entity.setDi3((byte) Integer.parseInt(row.get("di3").toString(), 16));
                entity.setLength(Integer.parseInt(row.get("length").toString(), 16));
                entity.setUnit(row.get("unit").toString());
                entity.setRead(Boolean.parseBoolean((row.get("read").toString())));
                entity.setWrite(Boolean.parseBoolean((row.get("write").toString())));

                DLT645DataFormatEntity format = new DLT645DataFormatEntity();
                if (format.decodeFormat((String) row.get("format"), entity.getLength())) {
                    entity.setFormat(format);
                } else {
                    System.out.println("DLT645的Format错误:" + row.get("name") + ":" + row.get("format"));
                    continue;
                }

                list.add(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
