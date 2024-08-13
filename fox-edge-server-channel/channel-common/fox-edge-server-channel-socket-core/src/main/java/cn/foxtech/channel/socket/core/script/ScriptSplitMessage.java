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

package cn.foxtech.channel.socket.core.script;

import cn.foxtech.channel.socket.core.utils.PackUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.script.Invocable;
import javax.script.ScriptEngine;

/**
 * 报文拆包脚本
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ScriptSplitMessage {
    /**
     * 执行引擎
     */
    private ScriptEngine scriptEngine;
    /**
     * 解码器脚本
     */
    private String script;
    /**
     * 数据格式：HEX或者TXT
     */
    private String format = "Hex";

    public Integer getHeaderLength() {
        try {
            return (Integer) this.scriptEngine.eval("getHeaderLength()");
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getPackLength(int[] pack) {
        try {
            String data = "";
            if (this.format.equals("Hex")) {
                data = PackUtil.byteArrayToHexString(pack);
            }
            if (this.format.equals("TXT")) {
                data = PackUtil.byteArray2String(pack);
            }

            Object value = scriptEngine.eval("getPackLength('" + data + "')");
            if (value instanceof Double) {
                double dv = (Double) value;
                return (int) dv;
            }
            if (value instanceof Float) {
                double dv = (Float) value;
                return (int) dv;
            }
            if (value instanceof Integer) {
                return (Integer) value;
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public Boolean isInvalidPack(int[] pack) {
        try {
            String data = "";
            if (this.format.equals("TXT")) {
                data = PackUtil.byteArray2String(pack);
            } else {
                data = PackUtil.byteArrayToHexString(pack);
            }

            Invocable invoke = (Invocable) scriptEngine;
            return (Boolean)invoke.invokeFunction("isInvalidPack", data);
        } catch (Exception e) {
            return true;
        }
    }


}
