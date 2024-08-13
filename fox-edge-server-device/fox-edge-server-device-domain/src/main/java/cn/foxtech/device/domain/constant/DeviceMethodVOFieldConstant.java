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

package cn.foxtech.device.domain.constant;

public class DeviceMethodVOFieldConstant {
    public static final String field_client_name = "clientName";
    public static final String field_uuid = "uuid";
    public static final String field_timeout = "timeout";

    // 接收的topic名称
    public static final String field_route = "route";

    public static final String field_method = "method";
    public static final String field_file = "file";

    /**
     * 字段值定义：field_operate
     */
    public static final String value_operate_exchange = "exchange";// 主从半双工：跟设备交换数据
    public static final String value_operate_publish = "publish";//对设备单向操作
    public static final String value_operate_report = "report";// 设备对服务器单向上报

    public static final String value_data_value = "value";//data->value
}
