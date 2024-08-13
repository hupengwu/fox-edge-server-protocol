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

package cn.foxtech.iot.common.remote;

import cn.foxtech.common.entity.manager.LocalConfigService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class RemoteProxyService {
    @Autowired
    private LocalConfigService localConfigService;

    @Autowired
    private RemoteHttpProxyService httpProxyService;

    @Autowired
    private RemoteMqttService mqttService;


    private String mode;

    public void initialize() {
        // 读取配置参数
        Map<String, Object> configs = this.localConfigService.getConfig();

        Map<String, Object> remote = (Map<String, Object>) configs.getOrDefault("remote", new HashMap<>());
        this.mode = (String) remote.getOrDefault("mode", "http");


        if ("http".equals(this.mode)) {
            Map<String, Object> http = (Map<String, Object>) remote.getOrDefault("http", new HashMap<>());
            String host = (String) http.getOrDefault("host", "http://localhost");
            this.httpProxyService.setUri(host);
        }
        if ("mqtt".equals(this.mode)) {
            Map<String, Object> mqtt = (Map<String, Object>) remote.getOrDefault("mqtt", new HashMap<>());
            this.mqttService.setMqttConfig(mqtt);
        }
    }
}
