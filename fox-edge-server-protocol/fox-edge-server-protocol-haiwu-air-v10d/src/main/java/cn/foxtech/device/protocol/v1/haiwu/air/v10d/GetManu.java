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

package cn.foxtech.device.protocol.v1.haiwu.air.v10d;

import cn.foxtech.device.protocol.v1.core.annotation.FoxEdgeDeviceType;
import cn.foxtech.device.protocol.v1.core.annotation.FoxEdgeOperate;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import cn.foxtech.device.protocol.v1.telecom.core.entity.PduEntity;
import cn.foxtech.device.protocol.v1.utils.HexUtils;
import cn.foxtech.device.protocol.v1.utils.MethodUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送范例：7e 31 30 30 31 36 30 35 31 30 30 30 30 46 44 42 32 0d
 * 返回范例：7e 31 30 30 31 36 30 30 30 36 30 34 36 53 2d 41 43 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 31 33 31 56 35 2d 41 2e 31 20 20 20 20 20 20 20 20 20 20 20 47 44 20 48 57 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 46 32 45 44 0d
 */
@FoxEdgeDeviceType(value = "基站空调(V1.0D)", manufacturer = "广东海悟科技有限公司")
public class GetManu {
    @FoxEdgeOperate(name = "获取厂家信息", polling = true, type = FoxEdgeOperate.encoder, timeout = 2000)
    public static String encodePdu(Map<String, Object> param) {
        // 取出设备地址
        Integer devAddr = (Integer) param.get("devAddr");

        // 检查输入参数
        if (MethodUtils.hasEmpty(devAddr)) {
            throw new ProtocolException("输入参数不能为空:devAddr");
        }

        PduEntity pduEntity = new PduEntity();
        pduEntity.setAddr(devAddr);
        pduEntity.setVer(0x10);
        pduEntity.setCid1(0x60);
        pduEntity.setCid2(0x51);

        byte[] pdu = PduEntity.encodePdu(pduEntity);


        return HexUtils.byteArrayToHexString(pdu);
    }

    @FoxEdgeOperate(name = "获取厂家信息", polling = true, type = FoxEdgeOperate.decoder, timeout = 2000)
    public static Map<String, Object> decodePdu(String hexString, Map<String, Object> param) {
        byte[] pdu = HexUtils.hexStringToByteArray(hexString);

        PduEntity entity = PduEntity.decodePdu(pdu, true, (byte) 0x00);

        if (entity.getCid1() != 0x60) {
            throw new ProtocolException("返回的CID1不正确!");
        }

        byte[] data = entity.getData();
        if (data.length != 70) {
            throw new ProtocolException("返回的数据长度不正确!");
        }

        Map<String, Object> result = new HashMap<>();
        try {
            // S-AC                          131V5-A.1           GD HW
            String text = "";
            text = new String(data, 0, 30, "GB2312");
            result.put("设备名称", text.trim());

            text = new String(data, 30, 20, "GB2312");
            result.put("软件版本", text.trim());

            text = new String(data, 50, 20, "GB2312");
            result.put("厂家名称", text.trim());
        } catch (Exception e) {
            throw new ProtocolException("文本编码格式不正确!");
        }

        return result;
    }
}