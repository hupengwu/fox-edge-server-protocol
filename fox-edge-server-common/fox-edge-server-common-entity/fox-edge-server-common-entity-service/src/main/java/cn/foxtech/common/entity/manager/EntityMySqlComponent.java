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

package cn.foxtech.common.entity.manager;

import cn.foxtech.common.entity.service.mybatis.BaseEntityService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库部件
 */
@Data
@Component
public class EntityMySqlComponent {
    private final Map<String, BaseEntityService> dBService = new HashMap<>();

    public BaseEntityService getEntityServiceBySimpleName(String simpleName) {
        return dBService.get(simpleName);
    }

    public <T> BaseEntityService getEntityService(Class<T> clazz) {
        return dBService.get(clazz.getSimpleName());
    }
}
