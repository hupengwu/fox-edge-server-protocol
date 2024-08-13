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

package cn.foxtech.rpc.sdk.mqtt.remote;

import cn.foxtech.common.mqtt.MqttClientHandler;
import cn.foxtech.common.mqtt.MqttClientService;
import lombok.Setter;
import net.dreamlu.iot.mqtt.core.client.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RemoteMqttService {
    @Autowired
    private MqttClientService mqttClientService;

    @Setter
    private Map<String, Object> mqttConfig = new HashMap<>();

    public void initialize(MqttClientHandler clientHandler) {
        // 绑定当前的handler
        this.mqttClientService.getMqttClientListener().setClientHandler(clientHandler);
        this.mqttClientService.Initialize(this.mqttConfig);
    }

    public MqttClient getClient() {
        return this.mqttClientService.getMqttClient();
    }

    /**
     * 等待连接建立
     *
     * @param timeout 等待超时
     */
    public void waitConnected(long timeout) {
        long startTime = System.currentTimeMillis();
        while (true) {
            // 检查：是否连接成功
            if (this.mqttClientService.getMqttClient().isConnected()) {
                return;
            }

            if (timeout < System.currentTimeMillis() - startTime) {
                return;
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
