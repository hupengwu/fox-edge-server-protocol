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

package cn.foxtech.device.protocol.v1.dlt645.core.entity;

import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public abstract class DLT645DataEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 格式
     */
    private DLT645DataFormatEntity format = new DLT645DataFormatEntity();
    /**
     * 长度
     */
    private int length;
    /**
     * 单位
     */
    private String unit;
    /**
     * 可读
     */
    private boolean read;
    /**
     * 可写
     */
    private boolean write;
    /**
     * 数值
     */
    private Object value = 0.0;

    /**
     * 数值
     */
    private Object value2nd;

    public abstract String getKey();

    public abstract byte[] getDIn();

    public abstract void setDIn(byte[] value);

    public abstract int getDInLen();

    public String toString() {
        if (this.value2nd == null) {
            return this.name + ":" + this.value + this.unit;
        }

        return this.name + ":" + this.value + this.unit + " " + this.value2nd;
    }

    public void decodeValue(byte[] data, Map<String, DLT645DataEntity> dinMap) {

        // DI值
        this.setDIn(data);

        // 获取字典信息
        DLT645DataEntity dict = dinMap.get(this.getKey());
        if (dict == null) {
            throw new ProtocolException("字典中没有该DIn的信息，请在CSV文件中补充该DIn的定义：" + this.getKey());
        }

        this.format = dict.format;
        this.name = dict.name;
        this.read = dict.read;
        this.write = dict.write;
        this.length = dict.length;
        this.unit = dict.unit;


        // 基本值
        this.value = this.format.decodeValue(data, this.format.getFormat(), this.getDInLen(), this.format.getLength());

        // 组合值
        if (this.format.getFormat2nd() != null && !this.format.getFormat2nd().isEmpty()) {
            this.value2nd = this.format.decodeValue(data, this.format.getFormat2nd(), this.getDInLen() + this.format.getLength(), this.format.getLength2nd());
        }
    }
}
