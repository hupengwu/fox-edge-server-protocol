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

package cn.foxtech.device.protocol.v1.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 解码器所在的Device服务，进程空间信息的上下文
 */
public class ApplicationContext {
    /**
     * 上下文信息
     */
    private static IApplicationContext context;

    private static Map<String,Object> defaultMap = new HashMap<>();

    /**
     * 绑定一个自带Map的具体上下文实现
     *
     * @param ctx
     */
    public static void initialize(IApplicationContext ctx) {
        context = ctx;
    }


    /**
     * 获得设备模型
     *
     * @param modelName 模型名称
     * @return 模型的结构
     */
    public static Map<String, Object> getDeviceModels(String modelName) {
        if (context == null){
            return defaultMap;
        }
        return context.getDeviceModels(modelName);
    }
}
