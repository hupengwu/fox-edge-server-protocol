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

package cn.foxtech.common.utils.reflect;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

public class ReflectionUtils {
    public ReflectionUtils() {
    }

    @FunctionalInterface
    public interface SerializableFunction<E, R> extends Function<E, R>, Serializable {
    }

    /**
     * 获取类函数的名称
     * 例如：getMethodName(Integer.toHexString)，返回的就是toHexString
     *
     * @param function
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> String getMethodName(SerializableFunction<E, R> function) {
        try {
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            String implMethodName = serializedLambda.getImplMethodName();

            return implMethodName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取类的函数
     *
     * @param clazz
     * @param function
     * @param <E>
     * @param <R>
     * @param <T>
     * @return
     */
    public static <E, R, T> Method getMethod(Class<T> clazz, SerializableFunction<E, R> function) {
        try {
            String methodName = getMethodName(function);
            return clazz.getMethod(methodName);
        } catch (Exception e) {
            return null;
        }
    }
}
