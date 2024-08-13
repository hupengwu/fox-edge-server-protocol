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

package cn.foxtech.device.protocol.v1.s7plc;

import cn.foxtech.device.protocol.v1.core.annotation.FoxEdgeDeviceType;
import cn.foxtech.device.protocol.v1.core.annotation.FoxEdgeOperate;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import cn.foxtech.device.protocol.v1.core.template.TemplateFactory;
import cn.foxtech.device.protocol.v1.s7plc.template.JDefaultTemplate;
import cn.foxtech.device.protocol.v1.utils.MethodUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读数据
 */
@FoxEdgeDeviceType(value = "S7 PLC", manufacturer = "Siemens")
public class S7PLCWriteData {

    @FoxEdgeOperate(name = "writeData", polling = true, type = FoxEdgeOperate.encoder, timeout = 2000)
    public static Map<String, Object> packGetData(Map<String, Object> param) {
        // 提取业务参数：设备地址/对象名称/CSV模板文件
        Map<String, Object> objectValues = (Map<String, Object>) param.get("objectValues");
        String templateName = (String) param.get("templateName");

        // 简单校验参数
        if (MethodUtils.hasNull(objectValues, templateName)) {
            throw new ProtocolException("参数不能为空: objectNames, templateName");
        }

        JDefaultTemplate template = TemplateFactory.getTemplate("fox-edge-server-protocol-s7plc").getTemplate("jsn", templateName, JDefaultTemplate.class);


        List<Map<String, Object>> params = template.encodeWriteObjects(objectValues);

        Map<String, Object> result = new HashMap<>();
        result.put("method", "writeData");
        result.put("params", params);

        return result;
    }

    @FoxEdgeOperate(name = "writeData", polling = true, type = FoxEdgeOperate.decoder, timeout = 2000)
    public static Map<String, Object> unpackReadData(Map<String, Object> respond, Map<String, Object> param) {
        String templateName = (String) param.get("templateName");

        // 简单校验参数
        if (MethodUtils.hasNull(templateName)) {
            throw new ProtocolException("参数不能为空:templateName");
        }

        return new HashMap<>();
    }
}
