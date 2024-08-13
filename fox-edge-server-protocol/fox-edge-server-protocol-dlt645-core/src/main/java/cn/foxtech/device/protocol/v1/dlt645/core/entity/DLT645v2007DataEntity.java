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

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class DLT645v2007DataEntity extends DLT645DataEntity {
    /**
     * DI1/DI0
     */
    private byte di0 = 0;
    private byte di1 = 0;
    private byte di2 = 0;
    private byte di3 = 0;


    public String getKey() {
        String key = "";
        key += Integer.toString(this.di3, 16) + ":";
        key += Integer.toString(this.di2, 16) + ":";
        key += Integer.toString(this.di1, 16) + ":";
        key += Integer.toString(this.di0, 16) + "";
        return key.toUpperCase();
    }

    @Override
    public byte[] getDIn() {
        byte[] value = new byte[4];
        value[0] = this.di0;
        value[1] = this.di1;
        value[2] = this.di2;
        value[3] = this.di3;

        return value;
    }

    @Override
    public void setDIn(byte[] value) {
        if (value.length < 4) {
            throw new ProtocolException("数据长度小于4字节!");
        }

        this.di0 = value[0];
        this.di1 = value[1];
        this.di2 = value[2];
        this.di3 = value[3];
    }

    /**
     * 2007版的DIn 4字节
     *
     * @return
     */
    @Override
    public int getDInLen() {
        return 4;
    }
}
