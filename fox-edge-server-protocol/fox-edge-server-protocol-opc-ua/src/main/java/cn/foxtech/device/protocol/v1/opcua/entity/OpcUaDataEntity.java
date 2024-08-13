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

package cn.foxtech.device.protocol.v1.opcua.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OpcUaDataEntity {
    /**
     * 对象名称：用户自定义的名称，在设备内部唯一
     */
    private String objectName;

    /**
     * 节点ID：opc设备侧的节点ID，它在设备内具有唯一性
     */
    private OpcUaNodeId nodeId;

    /**
     * 节点名称：opc设备侧的节点名，实际上它只在同一个父亲节点下名称唯一，但是在设备中可不唯一，它更多的是作为描述信息
     */
    private String nodeName;

    /**
     * 数据类型：opc设备侧的节点数值的数据类型
     */
    private String type;
}
