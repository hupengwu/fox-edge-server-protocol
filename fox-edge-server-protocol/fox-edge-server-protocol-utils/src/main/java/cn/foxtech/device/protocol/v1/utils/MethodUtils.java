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

package cn.foxtech.device.protocol.v1.utils;


import java.util.Collection;
import java.util.Map;

public class MethodUtils {
    /**
     * 是否包含null或者empty的参数
     * @param map map结构的数据
     * @param paramList 参数列表
     * @return 是否包含empty
     */
    public static boolean hasEmpty(Map<String, Object> map, Object... paramList) {
        for (Object key : paramList) {
            if (isEmpty(map.get(key))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否为empty
     * @param param 对象
     * @return 是否为empty
     */
    private static boolean isEmpty(Object param) {
        if (param == null) {
            return true;
        }

        // 检查：常用的类型是否为isEmpty：String/Map/
        if (param instanceof String && ((String) (param)).isEmpty()) {
            return true;
        }
        if (param instanceof Map && ((Map) (param)).isEmpty()) {
            return true;
        }
        return param instanceof Collection && ((Collection) (param)).isEmpty();
    }

    /**
     * 是否包含null或者empty的参数
     *
     * @param paramList 对象列表
     * @return 是否包含empty
     */
    public static boolean hasEmpty(Object... paramList) {
        for (Object param : paramList) {
            if (isEmpty(param)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否包含null的参数
     *
     * @param paramList 参数列表
     * @return 是否包含null
     */
    public static boolean hasNull(Object... paramList) {
        for (Object param : paramList) {
            if (param == null) {
                return true;
            }
        }

        return false;
    }
}

