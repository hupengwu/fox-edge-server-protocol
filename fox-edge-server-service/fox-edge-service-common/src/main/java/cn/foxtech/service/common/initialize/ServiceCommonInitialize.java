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

package cn.foxtech.service.common.initialize;

import cn.foxtech.common.status.ServiceStatusScheduler;
import cn.foxtech.service.common.scheduler.EntityManageScheduler;
import cn.foxtech.service.common.service.ServiceEntityManageService;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 初始化
 */
@Data
@Component
public class ServiceCommonInitialize {
    private static final Logger logger = Logger.getLogger(ServiceCommonInitialize.class);

    /**
     * 实体管理
     */
    @Autowired
    private ServiceEntityManageService entityManageService;

    /**
     * 实体调度
     */
    @Autowired
    private EntityManageScheduler entityManageScheduler;

    /**
     * 进程状态
     */
    @Autowired
    private ServiceStatusScheduler serviceStatusScheduler;


    public void initialize() {
        // 进程状态
        this.serviceStatusScheduler.initialize();
        this.serviceStatusScheduler.schedule();

        // 初始化数据管理器
        this.entityManageService.instance();
        this.entityManageService.initLoadEntity();
        this.entityManageScheduler.schedule();
    }
}
