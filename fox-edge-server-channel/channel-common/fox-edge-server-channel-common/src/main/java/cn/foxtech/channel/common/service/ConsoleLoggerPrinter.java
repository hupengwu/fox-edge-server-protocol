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

package cn.foxtech.channel.common.service;

import cn.foxtech.common.entity.manager.InitialConfigService;
import cn.foxtech.common.entity.manager.RedisConsoleService;
import cn.foxtech.common.utils.json.JsonUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ConsoleLoggerPrinter {
    @Autowired
    private InitialConfigService configService;

    private String configName = "serverConfig";
    private String configValueLogger = "logger";
    private String configValueChannel = "channelName";

    public void printLogger(String channelName, String type, Object content) {
        this.printLogger("DEBUG", channelName, type, content);
    }

    public void printLogger(String level, String channelName, String type, Object content) {
        try {
            // 检测：是否需要记录日志
            Object isLogger = this.configService.getConfigValue(this.configName, this.configValueLogger);
            if (!Boolean.TRUE.equals(isLogger)) {
                return;
            }

            // 检测：设备名称是否相同
            Object name = this.configService.getConfigValue(this.configName, this.configValueChannel);
            if (!channelName.equals(name)) {
                return;
            }

            RedisConsoleService logger = this.configService.getLogger();

            String message = "";
            if (content == null) {
                message = "通道名称：" + channelName + "\n" + type + "：" + null;
            }
            if (content instanceof String) {
                message = "通道名称：" + channelName + "\n" + type + "：" + content;
            }
            if ((content instanceof Map) || (content instanceof List)) {
                message = "通道名称：" + channelName + "\n" + type + "：" + JsonUtils.buildJsonWithoutException(content);
            }

            if (message.isEmpty()) {
                return;
            }

            if ("DEBUG".equals(level)) {
                logger.debug(message);
                return;
            }
            if ("INFO".equals(level)) {
                logger.info(message);
                return;
            }
            if ("WARN".equals(level)) {
                logger.warn(message);
            }
            if ("ERROR".equals(level)) {
                logger.error(message);
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
