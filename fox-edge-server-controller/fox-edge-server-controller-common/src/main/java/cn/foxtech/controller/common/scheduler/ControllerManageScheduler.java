/* ----------------------------------------------------------------------------
 * Copyright (c) Guangzhou Fox-Tech Co., Ltd. 2020-2024. All rights reserved.
 * --------------------------------------------------------------------------- */

package cn.foxtech.controller.common.scheduler;


import cn.foxtech.controller.common.service.ControllerManageService;
import cn.foxtech.common.utils.scheduler.singletask.PeriodTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 实体管理器的定时同步数据
 */
@Component
public class ControllerManageScheduler extends PeriodTaskService {
    @Autowired
    private ControllerManageService entityManageService;

    @Override
    public void execute(long threadId) throws Exception {
        Thread.sleep(1000);

        this.entityManageService.syncEntity();
    }
}