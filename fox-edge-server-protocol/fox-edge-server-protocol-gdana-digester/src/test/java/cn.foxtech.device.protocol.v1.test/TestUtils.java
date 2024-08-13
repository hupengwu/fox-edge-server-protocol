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

package cn.foxtech.device.protocol.v1.test;


import cn.foxtech.device.protocol.v1.gdana.digester.*;
import cn.foxtech.device.protocol.v1.utils.HexUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static void main(String[] args) {
        byte[] arrCmd = HexUtils.hexStringToByteArray("22 72 04 03 00 00 80 09 22 72 04 03 00 00 22 04 12 b9 7c ");
        List<DigesterEntity> entityList = DigesterProtocolFrame.decodeStickPack(arrCmd);

        DigesterEntity digesterEntity = DigesterProtocolFrame.decodePack(arrCmd);

        Map<String, Object> param = new HashMap<>();
        Map<String, Object> value = new HashMap<>();
        value = DigesterProtocolCheckDeviceStatus.decodePack("22 72 04 03 00 00 81 0f 47 46 44 2d 32 32 37 32 30 34 30 00 00 24 24 66 cd", param);
        value = DigesterProtocolResetMotor.decodePack("22 72 04 03 00 00 92 02 01 01 a8 c9", param);
        value = DigesterProtocolAddSample.decodePack("22 72 04 03 00 00 93 05 01 01 01 00 64 5d 28", param);
        value = DigesterProtocolSupport.decodePack("22 72 04 03 00 00 94 01 01 8f 20", param);
        value = DigesterProtocolBroadcast.decodePack("22 72 04 03 00 00 80 09 22 72 04 03 00 00 22 04 12 b9 7c", param);


        digesterEntity = DigesterProtocolFrame.decodePack(arrCmd);
        arrCmd = DigesterProtocolFrame.encodePack(digesterEntity);

        String hexString = HexUtils.byteArrayToHexString(arrCmd);

        arrCmd = DigesterProtocolFrame.encodeBroadcast();
        hexString = HexUtils.byteArrayToHexString(arrCmd);

        arrCmd = DigesterProtocolFrame.encodeBroadcast();
        hexString = HexUtils.byteArrayToHexString(arrCmd);


    }
}
