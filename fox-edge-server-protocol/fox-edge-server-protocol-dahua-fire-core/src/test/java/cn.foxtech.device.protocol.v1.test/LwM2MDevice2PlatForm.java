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

import cn.foxtech.device.protocol.v1.dahua.fire.core.entity.AduEntity;
import cn.foxtech.device.protocol.v1.dahua.fire.core.entity.LwM2MPduEntity;
import cn.foxtech.device.protocol.v1.dahua.fire.core.entity.infobj.InfObjRegisterEntity;
import cn.foxtech.device.protocol.v1.dahua.fire.core.enums.AduType;

public class LwM2MDevice2PlatForm {
    public static void main(String[] args) {
        testRegister();
    }

    public static void testRegister() {
        byte[] data = new byte[0];

        // 设备请求
        LwM2MPduEntity device = new LwM2MPduEntity();
        device.setSn(8);
        device.getCtrlEntity().setCmd(AduType.register.getCmd());
        device.setAduEntity(new AduEntity());
        device.getAduEntity().setType(AduType.register.getType());
        device.getAduEntity().getInfObjEntities().add(new InfObjRegisterEntity());
        data = LwM2MPduEntity.encodeEntity(device);

        // 自我验证
        device = LwM2MPduEntity.decodeEntity(data);

        // 平台应答
        LwM2MPduEntity platform = new LwM2MPduEntity();
        platform.getCtrlEntity().setCmd(AduType.confirm.getCmd());
        data = LwM2MPduEntity.encodeEntity(platform);

        // 自我验证
        platform = LwM2MPduEntity.decodeEntity(data);

    }
}
