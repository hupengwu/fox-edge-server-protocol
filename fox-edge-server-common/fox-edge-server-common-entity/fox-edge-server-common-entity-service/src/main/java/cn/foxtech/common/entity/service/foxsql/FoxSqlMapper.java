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

package cn.foxtech.common.entity.service.foxsql;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 注意：在启动类中加上这个注解，此时会进行实例化的FoxSqlMapper
 */
public interface FoxSqlMapper {
    @Select("${sql}")
    List<Map<String, Object>> selectMapList(@Param("sql") String sql);

    @Select({"${sql}"})
    Integer selectCount(@Param("sql") String sqlStr);

    @Delete({"${sql}"})
    void delete(@Param("sql") String sql);

    @Insert({"${sql}"})
    void insert(@Param("sql") String sql);
}
