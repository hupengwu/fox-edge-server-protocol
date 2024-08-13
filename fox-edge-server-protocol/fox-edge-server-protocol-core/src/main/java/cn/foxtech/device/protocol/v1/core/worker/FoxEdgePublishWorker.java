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

package cn.foxtech.device.protocol.v1.core.worker;

import cn.foxtech.device.protocol.v1.core.channel.FoxEdgeChannelService;
import cn.foxtech.device.protocol.v1.core.enums.WorkerLoggerType;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import cn.foxtech.device.protocol.v1.core.method.FoxEdgeMethodTemplate;
import cn.foxtech.device.protocol.v1.core.method.FoxEdgePublishMethod;

import javax.naming.CommunicationException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FoxEdgePublishWorker {
    /**
     * 对设备进行操作
     *
     * @param deviceName     设备名称
     * @param manufacturer   设备厂商
     * @param deviceType     设备类型
     * @param operateName    操作名称
     * @param params         参数表
     * @param timeout        通信超时
     * @param channelService 通道服务
     * @throws ProtocolException      业务异常，比如找不到解码器
     * @throws CommunicationException 通信异常
     */
    public static void publish(String deviceName, String manufacturer, String deviceType, String operateName, Map<String, Object> params, int timeout, FoxEdgeChannelService channelService) throws ProtocolException, CommunicationException {
        try {
            // 根据设备类型查找编码器集合
            Map<String, Object> methodPairs = FoxEdgeMethodTemplate.inst().getPublishMethod(manufacturer, deviceType);
            if (methodPairs == null) {
                throw new ProtocolException("找不到对应设备类型的编码器：" + manufacturer + ":" + deviceType);
            }

            Map<String, FoxEdgePublishMethod> methodMap = (Map<String, FoxEdgePublishMethod>) methodPairs.get(operateName);
            if (methodMap == null) {
                throw new ProtocolException("找不到对应操作名称的编码/解码函数：" + operateName);
            }

            // 根据操作名称，获得对应的编码/解码函数
            FoxEdgePublishMethod methodPair = methodMap.get("method");
            if (methodPair == null) {
                throw new ProtocolException("数据结构异常!");
            }

            if (methodPair == null) {
                throw new ProtocolException("找不到对应操作名称的编码函数：" + operateName);
            }

            // 主从半双工，必须同时有编码和解码函数
            if (methodPair.getEncoderMethod() == null) {
                throw new ProtocolException("找不到对应操作名称的编码函数：" + operateName);
            }

            if (timeout <= 0) {
                timeout = methodPair.getTimeout();
            }

            // 编码
            Object send = methodPair.getEncoderMethod().invoke(null, params);

            // 打印日志
            channelService.printLogger(deviceName, manufacturer, deviceType, WorkerLoggerType.send, send);

            try {
                // 向设备发布数据
                channelService.publish(deviceName, deviceType, send, timeout);
            } catch (Exception e) {
                throw new CommunicationException(e.getMessage());
            }
        } catch (InvocationTargetException ie) {
            throw new ProtocolException(ie.getTargetException().getMessage());
        } catch (Exception e) {
            throw new ProtocolException(e.getMessage());
        }
    }
}
