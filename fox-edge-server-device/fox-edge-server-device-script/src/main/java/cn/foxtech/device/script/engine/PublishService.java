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

package cn.foxtech.device.script.engine;

import cn.foxtech.common.entity.entity.OperateEntity;
import cn.foxtech.common.utils.method.MethodUtils;
import cn.foxtech.device.protocol.v1.core.channel.FoxEdgeChannelService;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.CommunicationException;
import javax.script.ScriptEngine;
import java.util.HashMap;
import java.util.Map;

@Component
public class PublishService {
    @Autowired
    private ScriptEngineService engineService;

    @Autowired
    private ScriptEngineOperator engineOperator;

    public void publish(String deviceName, String manufacturer, String deviceType, OperateEntity operateEntity, Map<String, Object> params, int timeout, FoxEdgeChannelService channelService) throws ProtocolException {
        try {
            // 取出ScriptEngine
            ScriptEngine engine = this.engineService.getScriptEngine(manufacturer, deviceType);

            // 取出编码/解码信息
            Map<String, Object> encode = (Map<String, Object>) operateEntity.getEngineParam().getOrDefault("encode", new HashMap<>());
            if (MethodUtils.hasEmpty(encode)) {
                throw new ProtocolException("找不到对应操作名称的编码函数：" + operateEntity.getOperateName());
            }

            // 编码脚本
            String encodeMain = (String) encode.getOrDefault("main", "");
            String encodeScript = (String) encode.getOrDefault("code", "");
            if (MethodUtils.hasEmpty(encodeMain, encodeScript)) {
                throw new ProtocolException("找不到对应操作名称的编码函数：" + operateEntity.getOperateName());
            }


            Object send;


            try {
                // 为ScriptEngine填入全局变量
                this.engineOperator.setSendEnvValue(engine, params);

                // 数据编码
                send = this.engineOperator.encode(engine, encodeMain, encodeScript);
            } catch (Exception e) {
                throw new ProtocolException("编码错误：" + e.getMessage());
            }


            try {
                channelService.publish(deviceName, deviceType, send, timeout);
            } catch (Exception e) {
                throw new CommunicationException(e.getMessage());
            }

        } catch (Exception e) {
            throw new ProtocolException(e.getMessage());
        }
    }
}
