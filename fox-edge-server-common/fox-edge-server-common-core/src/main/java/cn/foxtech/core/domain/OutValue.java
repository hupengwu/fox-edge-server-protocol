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

package cn.foxtech.core.domain;

/**
 * 基础数据输出
 *    在JAVA中，Integer、String、Long、byte[]等基础对象，在java函数中不支持返回
 *    所以建立这么一个对象，用于在函数中带回数据
 */
public final class OutValue {
    private Object obj = null;
    public Object getObj(){
        return this.obj;
    }
    public void setObject(Object obj){
        this.obj = obj;
    }
}
