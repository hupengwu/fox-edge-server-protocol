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

import java.util.Map;
import java.util.Objects;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OpcUaNodeId {
    /**
     * 名空间
     */
    private Integer namespace;
    /**
     * 名空间下的一个节点ID
     */
    private String identifier;

    public static OpcUaNodeId buildEntity(Integer namespace, String identifier) {
        if (namespace == null || identifier == null) {
            return null;
        }

        OpcUaNodeId result = new OpcUaNodeId();
        result.setNamespace(namespace);
        result.setIdentifier(identifier);
        return result;
    }

    public static OpcUaNodeId buildEntity(Map<String, Object> nodeIdMap) {
        if (nodeIdMap == null) {
            return null;
        }

        Integer namespace = (Integer) nodeIdMap.get("namespace");
        String identifier = (String) nodeIdMap.get("identifier");

        return buildEntity(namespace, identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpcUaNodeId nodeId = (OpcUaNodeId) o;
        return Objects.equals(namespace, nodeId.namespace) && Objects.equals(identifier, nodeId.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, identifier);
    }

    @Override
    public String toString() {
        return "NodeId:[" + namespace + ":" + identifier + "]";
    }
}
