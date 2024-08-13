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

package cn.foxtech.device.script.engine;

import cn.foxtech.common.utils.MapUtils;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ScriptEngineService {
    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final Map<String, Object> engineMap = new ConcurrentHashMap<>();


    public ScriptEngine getScriptEngine(String manufacturer, String deviceType) {
        ScriptEngine engine = (ScriptEngine) MapUtils.getValue(this.engineMap, manufacturer, deviceType);
        if (engine == null) {
            engine = this.manager.getEngineByName("JavaScript");
            MapUtils.setValue(this.engineMap, manufacturer, deviceType, engine);
        }

        return engine;
    }
}
