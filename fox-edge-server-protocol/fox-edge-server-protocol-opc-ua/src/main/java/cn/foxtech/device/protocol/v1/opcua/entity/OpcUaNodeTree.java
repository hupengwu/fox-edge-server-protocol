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

import java.util.ArrayList;
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OpcUaNodeTree {
    /**
     * 节点名称：设备的命名，只是方便理解，并不具有ID的特性
     */
    private String name;
    /**
     * 节点ID：这个才是唯一性ID
     */
    private OpcUaNodeId nodeId;
    /**
     * 数值
     */
    private Object value;
    /**
     * 只节点
     */
    private List<OpcUaNodeTree> children = new ArrayList<>();

    public OpcUaNodeTree(OpcUaNodeId nodeId) {
        this.nodeId = nodeId;
    }
}