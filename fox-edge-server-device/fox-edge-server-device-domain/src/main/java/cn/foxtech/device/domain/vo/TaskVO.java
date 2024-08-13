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

package cn.foxtech.device.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 包操作：批量操作
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class TaskVO {
    /**
     * UUID
     */
    private String uuid;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 通信超时
     */
    private Integer timeout;

    public void bindBaseVO(TaskVO vo) {
        this.uuid = vo.uuid;
        this.clientName = vo.clientName;
        this.timeout = vo.timeout;
    }

    public void bindBaseVO(Map<String, Object> map) {
        this.uuid = (String) map.get("uuid");
        this.clientName = (String) map.get("clientName");
        this.timeout = (Integer) map.get("timeout");
    }
}
