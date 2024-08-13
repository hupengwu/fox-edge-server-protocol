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

package cn.foxtech.device.scanner;

import cn.foxtech.common.utils.reflect.JarLoaderUtils;
import cn.foxtech.device.protocol.v1.core.method.FoxEdgeMethodTemplate;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class FoxEdgeMethodTemplateScanner {
    private static final Logger logger = Logger.getLogger(FoxEdgeMethodTemplateScanner.class);

    public static void scanMethodPair(List<String> jarFileNameList) {
        try {
            // 动态装载配置文件中指明的解码器JAR包
            for (String line : jarFileNameList) {
                line = line.trim();
                JarLoaderUtils.loadJar(line);
            }

            Map<String, Object> exchangeMethod = FoxEdgeExchangeScanner.scanMethodPair();
            Map<String, Object> reportMethod = FoxEdgeReportScanner.scanMethodPair();
            Map<String, Object> publishMethod = FoxEdgePublishScanner.scanMethodPair();

            // 然后通过扫描注解，生成操作定义表
            FoxEdgeMethodTemplate.inst().setExchangeMethod(exchangeMethod);
            FoxEdgeMethodTemplate.inst().setReportMethod(reportMethod);
            FoxEdgeMethodTemplate.inst().setPublishMethod(publishMethod);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
