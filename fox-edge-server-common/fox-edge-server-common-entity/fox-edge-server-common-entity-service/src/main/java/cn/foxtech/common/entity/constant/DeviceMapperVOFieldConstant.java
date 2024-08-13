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

package cn.foxtech.common.entity.constant;

public class DeviceMapperVOFieldConstant extends BaseVOFieldConstant {
    public static final String field_device_type = "deviceType";
    public static final String field_manufacturer = "manufacturer";

    public static final String field_object_name = "objectName";
    public static final String field_mapper_name = "mapperName";
    public static final String field_mapper_mode = "mapperMode";
    public static final String field_value_Type = "valueType";

    public static final String field_extend_param = "extendParam";


    /**
     * 映射模式：
     * <p>
     * original   0  原始值：例如将"对象1"依然保留为“对象1"
     * replace    1  替换，例如将"对象1"重命名成“object 1”，只保留
     * duplicate  2  副本，例如为“对象1”新增一个"object 1"的副本，同时保留"对象1"
     * filter     3  过滤，例如将“对象1”剔除掉
     * expend     4  展开，例如将“JSON格式的 obj1 JSON对象，展开为两个对象
     *                  {"k":
     *                      {"sk1":"sv1"},
     *                      {"sk2":"sv2"}
     *                  }
     *                  展开为obj1_k_sk1和obj1_k_sk2两个对象，格式是以下划线分开
     */
    public static final int mapper_mode_original = 0;
    public static final int mapper_mode_replace = 1;
    public static final int mapper_mode_duplicate = 2;
    public static final int mapper_mode_filter = 3;
    public static final int mapper_mode_expend = 4;
}
