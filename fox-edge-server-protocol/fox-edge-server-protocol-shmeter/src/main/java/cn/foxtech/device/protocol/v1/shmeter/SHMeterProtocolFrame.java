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

package cn.foxtech.device.protocol.v1.shmeter;


import cn.foxtech.device.protocol.v1.dlt645.core.DLT645Protocol;

import java.util.Map;

/**
 * 上海电表采用的是DLT645的协议框架
 */
public class SHMeterProtocolFrame extends DLT645Protocol {
    /**
     * @param param
     * @return
     */
    public static byte[] packCmd(Map<String, Object> param) {
        return DLT645Protocol.packCmd(param);
    }

    /**
     * ZX的电源自己会在结尾处额外再加一个0X0D，所以要重载处理
     *
     * @param arrCmd
     * @return
     */
    public static Map<String, Object> unPackCmd2Map(byte[] arrCmd, Map<String, Object> param) {
        return DLT645Protocol.unPackCmd2Map(arrCmd);
    }

    /**
     * 如果没有夹带版本和地址参数，那么填写默认参数
     *
     * @param param
     */
    public static void pretreatParam(Map<String, Object> param) {
        if (!param.containsKey(ADR)) {
            // 默认设备地址0x01
            byte[] arrAddr = new byte[6];
            arrAddr[0] = 0x01;
            param.put(ADR, 0x01);
        }
        if (!param.containsKey(FUN)) {
            // 读数据功能码 0x01
            param.put(FUN, 0x01);
        }
    }
}
