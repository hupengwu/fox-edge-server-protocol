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

package cn.foxtech.device.service.initialize;


import cn.foxtech.common.entity.manager.InitialConfigService;
import cn.foxtech.common.entity.manager.RedisConsoleService;
import cn.foxtech.common.status.ServiceStatusScheduler;
import cn.foxtech.device.script.engine.ScriptEngineInitialize;
import cn.foxtech.device.service.context.DeviceModelContext;
import cn.foxtech.device.service.controller.DeviceExecuteController;
import cn.foxtech.device.service.controller.DeviceReportController;
import cn.foxtech.device.service.scheduler.EntityManageScheduler;
import cn.foxtech.device.service.service.EntityManageService;
import cn.foxtech.device.service.service.MethodEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化
 */
@Component
public class Initialize implements CommandLineRunner {
    /**
     * 日志
     */
    @Autowired
    private RedisConsoleService logger;

    @Autowired
    private DeviceModelContext deviceModelContext;

    @Autowired
    private EntityManageService entityManageService;

    @Autowired
    private EntityManageScheduler entityManageScheduler;


    @Autowired
    private DeviceExecuteController deviceExecuteController;

    @Autowired
    private DeviceReportController deviceReportController;

    @Autowired
    private ServiceStatusScheduler serviceStatusScheduler;

    @Autowired
    private MethodEntityService methodEntityService;

    /**
     * 动态参数配置
     */
    @Autowired
    private InitialConfigService configService;

    @Autowired
    private ScriptEngineInitialize engineInitialize;

    @Override
    public void run(String... args) {
        logger.info("------------------------初始化开始！------------------------");

        this.serviceStatusScheduler.initialize();
        this.serviceStatusScheduler.schedule();

        // 从数据库和redis中，装载数据实体
        this.entityManageService.instance();
        this.entityManageService.initLoadEntity();

        // 初始化上下文：为解码器提供设备服务的全局信息
        this.deviceModelContext.initialize();


        // 初始化配置参数
        this.configService.initialize("serverConfig", "serverConfig.json");

        // 从第三方jar扫描解码器，并生成redis记录
        this.methodEntityService.scanJarFile();
        this.methodEntityService.updateEntityList();

        // 脚本引擎的初始化
        this.engineInitialize.initialize();

        this.entityManageScheduler.schedule();

        // 启动对客户端的响应线程
        this.deviceExecuteController.schedule(3);
        this.deviceReportController.schedule();


        logger.info("------------------------初始化完成！------------------------");
    }
}
