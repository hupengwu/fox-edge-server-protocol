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

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通知组件：通知数据发生变化
 */
@Data
@Component
public class EntityChangeComponent {
    /**
     * 再次发生重新装载的实体类型：从而感知消费者数据发生了变化
     */
    private Map<String, Long> reloadMap = new ConcurrentHashMap<>();
    /**
     * 再次发生发布的实体类型：从而感知生产者数据发生了变化
     */
    private Map<String, Long> publishMap = new ConcurrentHashMap<>();
}
