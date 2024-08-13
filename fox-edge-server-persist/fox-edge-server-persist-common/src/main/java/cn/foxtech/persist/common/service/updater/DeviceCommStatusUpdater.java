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

package cn.foxtech.persist.common.service.updater;

import cn.foxtech.common.entity.constant.DeviceStatusVOFieldConstant;
import cn.foxtech.common.entity.entity.DeviceStatusEntity;
import cn.foxtech.common.utils.number.NumberUtils;
import cn.foxtech.device.domain.vo.OperateRespondVO;
import cn.foxtech.persist.common.service.EntityManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeviceCommStatusUpdater {
    private static final Logger logger = Logger.getLogger(DeviceCommStatusUpdater.class);

    /**
     * 实体管理
     */
    @Autowired
    private EntityManageService entityManageService;

    /**
     * 根据通信状态数据到redis
     */
    public void updateStatusEntity(Long deviceId, Map<String, Object> status) {
        try {
            if (status == null) {
                return;
            }

            DeviceStatusEntity statusEntity = new DeviceStatusEntity();
            statusEntity.setId(deviceId);

            long commFailedTime = NumberUtils.makeLong(status.get(OperateRespondVO.data_comm_status_failed_time));
            long commSuccessTime = NumberUtils.makeLong(status.get(OperateRespondVO.data_comm_status_success_time));

            long time = System.currentTimeMillis();

            Map<String, Object> existEntity = this.entityManageService.readHashMap(statusEntity.makeServiceKey(), DeviceStatusEntity.class);
            if (existEntity == null) {
                int commFailedCount = 0;
                if (commFailedTime > 0) {
                    commFailedCount++;
                }

                statusEntity.setCreateTime(time);
                statusEntity.setUpdateTime(time);

                // 插入对象
                statusEntity.setCommFailedCount(commFailedCount);
                statusEntity.setCommFailedTime(commFailedTime);
                statusEntity.setCommSuccessTime(commSuccessTime);
                this.entityManageService.writeEntity(statusEntity);

            } else {
                int commFailedCount = NumberUtils.makeInteger(existEntity.getOrDefault(DeviceStatusVOFieldConstant.field_failed_count, 0));
                if (commFailedTime > 0) {
                    commFailedCount++;
                } else {
                    commFailedCount = 0;
                }

                statusEntity.setUpdateTime(time);

                // 更新对象
                statusEntity.setCommFailedCount(commFailedCount);
                statusEntity.setCommFailedTime(commFailedTime);
                statusEntity.setCommSuccessTime(commSuccessTime);
                this.entityManageService.writeEntity(statusEntity);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
