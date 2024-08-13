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

package cn.foxtech.device.protocol.v1.hikvision.fire.core.entity;

import cn.foxtech.device.protocol.v1.hikvision.fire.core.entity.infobj.*;
import cn.foxtech.device.protocol.v1.hikvision.fire.core.enums.AduType;

import java.util.HashMap;
import java.util.Map;

public class AduInfObjMap {
    private static final Map<AduType, Class> aduTypeInfObjMap = new HashMap<>();

    /**
     * 获得Class
     *
     * @param aduType 命令字类型
     * @return 具体对应的InfObjEntity的Class
     */
    public static Class getInfObjClass(AduType aduType) {
        return inst().get(aduType);
    }

    private static Map<AduType, Class> inst() {
        if (!aduTypeInfObjMap.isEmpty()) {
            return aduTypeInfObjMap;
        }

        aduTypeInfObjMap.put(AduType.systemStatus, InfObjSysStatusEntity.class);
        aduTypeInfObjMap.put(AduType.compStatus, InfObjCompStatusEntity.class);
        aduTypeInfObjMap.put(AduType.deviceStatus, InfObjDeviceStatusEntity.class);
        aduTypeInfObjMap.put(AduType.deviceOperate, InfObjDeviceOperateEntity.class);
        aduTypeInfObjMap.put(AduType.deviceSoftVer, InfObjDeviceSoftVerEntity.class);
        aduTypeInfObjMap.put(AduType.syncClock, InfObjSyncClockEntity.class);
        aduTypeInfObjMap.put(AduType.setInspection, InfObjSetInspectionEntity.class);


        return aduTypeInfObjMap;
    }
}
