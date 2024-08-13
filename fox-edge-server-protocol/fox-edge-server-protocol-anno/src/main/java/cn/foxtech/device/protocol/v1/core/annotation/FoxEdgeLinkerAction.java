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

package cn.foxtech.device.protocol.v1.core.annotation;

import java.lang.annotation.*;

/**
 * 链路状态注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FoxEdgeLinkerAction {
    public static final String CREATE_LINKER_REQUEST = "createLinkerRequest";
    public static final String CREATE_LINKER_RESPOND = "createLinkerRespond";
    public static final String ACTIVE_LINKER_REQUEST = "activeLinkerRequest";
    public static final String ACTIVE_LINKER_RESPOND = "activeLinkerRespond";
    public static final String INTERCEPT_LINKER_RESPOND = "interceptLinkerRespond";

    /**
     * 设备类型
     *
     * @return
     */
    String value() default "";
}
