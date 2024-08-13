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
import cn.foxtech.device.protocol.v1.core.annotation.FoxEdgeOperate;
import cn.foxtech.device.protocol.v1.core.channel.FoxEdgeChannelService;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.CommunicationException;
import javax.script.ScriptEngine;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExchangeService {
    @Autowired
    private ScriptEngineService engineService;

    @Autowired
    private ScriptEngineOperator engineOperator;

    @Autowired
    private ScriptEngineModel engineModel;

    public Map<String, Object> exchange(String deviceName, String manufacturer, String deviceType, OperateEntity operateEntity, Map<String, Object> params, int timeout, FoxEdgeChannelService channelService) throws ProtocolException {
        try {
            // 取出ScriptEngine
            ScriptEngine engine = this.engineService.getScriptEngine(manufacturer, deviceType);

            // 取出：设备模型信息
            String modelName = (String) operateEntity.getEngineParam().getOrDefault("modelName", "");

            // 取出：编码/解码信息
            Map<String, Object> encode = (Map<String, Object>) operateEntity.getEngineParam().getOrDefault("encode", new HashMap<>());
            Map<String, Object> decode = (Map<String, Object>) operateEntity.getEngineParam().getOrDefault("decode", new HashMap<>());
            if (MethodUtils.hasEmpty(encode, decode)) {
                throw new ProtocolException("找不到对应操作名称的编码/解码函数：" + operateEntity.getOperateName());
            }

            // 编码脚本
            String encodeMain = (String) encode.getOrDefault("main", "");
            String encodeScript = (String) encode.getOrDefault("code", "");
            if (MethodUtils.hasEmpty(encodeMain, encodeScript)) {
                throw new ProtocolException("找不到对应操作名称的编码函数：" + operateEntity.getOperateName());
            }

            // 解码脚本
            String decodeMain = (String) decode.getOrDefault("main", "");
            String decodeScript = (String) decode.getOrDefault("code", "");
            if (MethodUtils.hasEmpty(decodeMain, decodeScript)) {
                throw new ProtocolException("找不到对应操作名称的解码函数：" + operateEntity.getOperateName());
            }


            Object send;
            Object recv;


            try {
                // 为ScriptEngine填入设备模型
                this.engineModel.setEnvDeviceModel(manufacturer, deviceType, modelName, params);

                // 为ScriptEngine填入全局变量
                this.engineOperator.setSendEnvValue(engine, params);

                // 数据编码
                send = this.engineOperator.encode(engine, encodeMain, encodeScript);
            } catch (Exception e) {
                throw new ProtocolException("编码错误：" + e.getMessage());
            }

            try {
                recv = channelService.exchange(deviceName, deviceType, send, timeout);
            } catch (Exception e) {
                throw new CommunicationException(e.getMessage());
            }


            try {
                // 为ScriptEngine填入全局变量
                this.engineOperator.setRecvEnvValue(engine, recv, params);

                // 将解码结果，根据模式，用各自的字段带回
                if (FoxEdgeOperate.record.equals(operateEntity.getDataType())) {
                    return this.engineOperator.decodeRecord(engine, operateEntity.getOperateName(), decodeMain, decodeScript);
                } else if (FoxEdgeOperate.result.equals(operateEntity.getDataType())) {
                    return this.engineOperator.decodeResult(engine, operateEntity.getOperateName(), decodeMain, decodeScript);
                } else {
                    return this.engineOperator.decodeStatus(engine, operateEntity.getOperateName(), decodeMain, decodeScript);
                }
            } catch (Exception e) {
                throw new ProtocolException("解码错误：" + e.getMessage());
            }
        } catch (Exception e) {
            throw new ProtocolException(e.getMessage());
        }
    }
}
