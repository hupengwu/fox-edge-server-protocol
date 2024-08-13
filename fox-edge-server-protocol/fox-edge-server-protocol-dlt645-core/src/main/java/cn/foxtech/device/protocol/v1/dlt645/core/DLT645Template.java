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

package cn.foxtech.device.protocol.v1.dlt645.core;

import cn.foxtech.device.protocol.v1.core.context.ApplicationContext;
import cn.foxtech.device.protocol.v1.dlt645.core.entity.DLT645DataEntity;
import cn.foxtech.device.protocol.v1.dlt645.core.entity.DLT645v1997DataEntity;
import cn.foxtech.device.protocol.v1.dlt645.core.loader.DLT645v1997JsnLoader;
import cn.foxtech.device.protocol.v1.dlt645.core.loader.DLT645v2007JsnLoader;
import cn.foxtech.device.protocol.v1.utils.ContainerUtils;
import cn.foxtech.device.protocol.v1.utils.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 来自CSV文件的模板信息
 */
public class DLT645Template {
    /**
     * 静态实例
     */
    private static final DLT645Template template = new DLT645Template();
    private final Map<String, Object> datas = new ConcurrentHashMap<>();

    public static DLT645Template inst() {
        return template;
    }

    public synchronized Map<String, DLT645DataEntity> getTemplateByName(String version, String defaultTable) {
        return this.getTemplate(version, defaultTable, "name");
    }

    public synchronized Map<String, DLT645DataEntity> getTemplateByDIn(String version, String defaultTable) {
        return this.getTemplate(version, defaultTable, "key");
    }

    private Map<String, DLT645DataEntity> getTemplate(String version, String templateName, String type) {
        Map<String, Object> deviceTemplateEntity = ApplicationContext.getDeviceModels(templateName);

        // 检测：上下文侧的时间戳和当前模型的时间戳是否一致
        Object updateTime = deviceTemplateEntity.getOrDefault("updateTime", 0L);
        if (updateTime.equals(MapUtils.getValue(this.datas, version, templateName, "updateTime"))) {
            return (Map<String, DLT645DataEntity>) MapUtils.getValue(this.datas, version, templateName, type);
        }

        // 取出JSON模型的数据列表
        Map<String, Object> modelParam = (Map<String, Object>) deviceTemplateEntity.getOrDefault("modelParam", new HashMap<>());
        List<Map<String, Object>> rows = (List<Map<String, Object>>) modelParam.getOrDefault("list", new ArrayList<>());

        List<DLT645DataEntity> entityList = new ArrayList<>();
        if (DLT645Define.PRO_VER_1997.equals(version)) {
            DLT645v1997JsnLoader loader = new DLT645v1997JsnLoader();
            entityList = loader.loadJsnModel(rows);
        }
        if (DLT645Define.PRO_VER_2007.equals(version)) {
            DLT645v2007JsnLoader loader = new DLT645v2007JsnLoader();
            entityList = loader.loadJsnModel(rows);
        }

        Map<String, DLT645DataEntity> nameMap = ContainerUtils.buildMapByKey(entityList, DLT645v1997DataEntity::getName);
        Map<String, DLT645DataEntity> keyMap = ContainerUtils.buildMapByKey(entityList, DLT645v1997DataEntity::getKey);
        MapUtils.setValue(this.datas, version, templateName, "name", nameMap);
        MapUtils.setValue(this.datas, version, templateName, "key", keyMap);
        MapUtils.setValue(this.datas, version, templateName, "updateTime", updateTime);

        return (Map<String, DLT645DataEntity>) MapUtils.getValue(this.datas, version, templateName, type);
    }
}
