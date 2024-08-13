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

package cn.foxtech.device.protocol.v1.core.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateFactory {
    private static final Map<String, TemplateContainer> map = new ConcurrentHashMap<>();

    /**
     * 协议模块名称
     *
     * @param protocolModelName 协议模型明名称
     * @return 模板容器
     */
    public static TemplateContainer getTemplate(String protocolModelName) {
        TemplateContainer template = map.get(protocolModelName);
        if (template == null) {
            template = new TemplateContainer();
            map.put(protocolModelName, template);
        }

        return template;
    }
}
