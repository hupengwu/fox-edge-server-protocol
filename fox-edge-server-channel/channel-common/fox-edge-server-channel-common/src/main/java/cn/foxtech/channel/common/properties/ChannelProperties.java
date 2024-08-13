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

package cn.foxtech.channel.common.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Component;

@Getter(value = AccessLevel.PUBLIC)
@Component
public class ChannelProperties {
    @Autowired
    private AbstractEnvironment environment;

    /**
     * 必填参数
     */
    @Value("${spring.fox-service.model.name}")
    private String channelType;

    /**
     * 是否打印收/发日志
     * 该参数只是临时变量，它的填写由各个channel服务在Initialize阶段，自己填写，自己在后续阶段使用。
     */
    @Setter
    private boolean logger = false;

    /**
     * 可选参数
     */
    private String initMode;


    public void initialize() {
        this.initMode = this.environment.getProperty("spring.channel.init-mode", String.class, "redis");
    }
}
