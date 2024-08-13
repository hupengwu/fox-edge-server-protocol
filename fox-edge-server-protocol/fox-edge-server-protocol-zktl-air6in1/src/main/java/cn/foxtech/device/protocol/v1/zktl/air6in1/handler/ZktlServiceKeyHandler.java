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

package cn.foxtech.device.protocol.v1.zktl.air6in1.handler;

import cn.foxtech.device.protocol.v1.utils.netty.ServiceKeyHandler;
import cn.foxtech.device.protocol.v1.zktl.air6in1.encoder.Encoder;
import cn.foxtech.device.protocol.v1.zktl.air6in1.entity.ZktlDataEntity;

public class ZktlServiceKeyHandler extends ServiceKeyHandler {
    @Override
    public String getServiceKey(byte[] pdu) {
        ZktlDataEntity entity = Encoder.decodeDataEntity(pdu);
        return entity.getServiceKey();
    }
}
