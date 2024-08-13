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

package cn.foxtech.device.protocol.v1.cetups;

import cn.foxtech.device.protocol.v1.core.reference.BytesRef;
import cn.foxtech.device.protocol.v1.modbus.core.ModBusConstants;
import cn.foxtech.device.protocol.v1.modbus.core.ModBusProtocol;
import cn.foxtech.device.protocol.v1.modbus.core.ModBusProtocolFactory;

import java.util.Map;

public class CETUPSProtocolFrame {
    protected static final ModBusProtocol protocol = ModBusProtocolFactory.createProtocol(ModBusConstants.MODE_RTU);

    /**
     * @param param
     * @param arrCmd
     * @return
     */
    public static boolean packCmd(Map<String, Object> param, BytesRef arrCmd) {
        byte[] array = protocol.packCmd4Map(param);
        if (array == null) {
            return false;
        }

        arrCmd.setValue(array);
        return true;
    }

    /**
     * ZX的电源自己会在结尾处额外再加一个0X0D，所以要重载处理
     *
     * @param arrCmd 数据报文
     * @return
     */
    public static Map<String, Object> unPackCmd2Map(byte[] arrCmd) {
        return protocol.unPackCmd2Map(arrCmd);
    }

    /**
     * 如果没有夹带版本和地址参数，那么填写默认参数
     *
     * @param param
     */
    public static void pretreatParam(Map<String, Object> param) {
        Object devAddr = param.get("设备地址");
        if (devAddr != null) {
            Integer addr = Integer.parseInt(devAddr.toString());
            param.put(ModBusConstants.ADDR, addr);
        }
        if (!param.containsKey(ModBusConstants.ADDR)) {
            param.put(ModBusConstants.ADDR, 0x01);
        }
    }
}
