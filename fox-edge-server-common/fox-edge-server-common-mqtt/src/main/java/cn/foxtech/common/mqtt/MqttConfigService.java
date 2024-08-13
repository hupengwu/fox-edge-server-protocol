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

package cn.foxtech.common.mqtt;

import cn.foxtech.common.utils.MapUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class MqttConfigService {
    /**
     * MQTT订阅位置
     */
    private final String subscribe = "/fox/proxy/c2e";


    private String host = "127.0.0.1";

    private Integer port = 1883;

    private String userName = "mica";

    private String password = "123456";

    private String name = "Mica-Mqtt-Client";

    private String version = "mqtt_3_1_1";

    private String clientId = "FOX_CLIENT_CHANNEL_MQTT_CLIENT";

    private Integer keepAliveSecs = 60;

    private Integer reInterval = 5000;

    public void initialize(Map<String, Object> configs) {
        // 从redis中装载配置：如果redis没有，则默认采用application.yml的配置数据
        this.host = MapUtils.getOrDefault(configs, String.class, "host", this.host);
        this.port = MapUtils.getOrDefault(configs, Integer.class, "port", this.port);
        this.clientId = MapUtils.getOrDefault(configs, String.class, "clientId", this.clientId);
        this.userName = MapUtils.getOrDefault(configs, String.class, "user-name", this.userName);
        this.password = MapUtils.getOrDefault(configs, String.class, "password", this.password);
        this.name = MapUtils.getOrDefault(configs, String.class, "name", this.name);
        this.version = MapUtils.getOrDefault(configs, String.class, "version", this.version);
        this.keepAliveSecs = MapUtils.getOrDefault(configs, Integer.class, "keep-alive-secs", this.keepAliveSecs);
        this.reInterval = MapUtils.getOrDefault(configs, Integer.class, "re-interval", this.reInterval);

        if (this.clientId == null || this.clientId.isEmpty()) {
            this.clientId = "clientId:" + UUID.randomUUID();
        }
    }
}
