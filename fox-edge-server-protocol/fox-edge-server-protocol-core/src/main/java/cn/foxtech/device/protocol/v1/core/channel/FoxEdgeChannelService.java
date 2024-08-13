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

package cn.foxtech.device.protocol.v1.core.channel;

import cn.foxtech.device.protocol.v1.core.enums.WorkerLoggerType;

public interface FoxEdgeChannelService {
    /**
     * 数据交换操作：一问一答方式的主从半双工通信方式
     *
     * @param deviceName 设备名称
     * @param deviceType 设备类型
     * @param send       发送的数据
     * @param timeout    通信超时时间间隔
     * @return 返回的报文内容
     * @throws Exception 通信异常信息
     */
    Object exchange(String deviceName, String deviceType, Object send, int timeout) throws Exception;

    /**
     * 数据发布操作：单向发布到设备的通信方式
     *
     * @param deviceName 设备名称
     * @param deviceType 设备类型
     * @param send       发送的数据
     * @param timeout    发送超时
     * @throws Exception 通信异常信息
     */
    void publish(String deviceName, String deviceType, Object send, int timeout) throws Exception;

    /**
     * 打印日志
     *
     * @param deviceName   设备名称
     * @param manufacturer 设备厂商
     * @param deviceType   设备类型
     * @param type         日志类型
     * @param content      日志内容
     */

    void printLogger(String deviceName, String manufacturer, String deviceType, WorkerLoggerType type, Object content);
}
