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
public class DLT645v1997DataEntity extends DLT645DataEntity {
    /**
     * DI1/DI0
     */
    private byte di0l = 0;
    private byte di0h = 0;
    private byte di1l = 0;
    private byte di1h = 0;

    @Override
    public String getKey() {
        String key = "";
        key += Integer.toString(this.di1h, 16) + ":";
        key += Integer.toString(this.di1l, 16) + ":";
        key += Integer.toString(this.di0h, 16) + ":";
        key += Integer.toString(this.di0l, 16) + "";
        return key.toUpperCase();
    }

    @Override
    public byte[] getDIn() {
        byte[] value = new byte[2];
        value[0] = (byte) (this.di0l + (this.di0h << 4));
        value[1] = (byte) (this.di1l + (this.di1h << 4));
        return value;
    }

    @Override
    public void setDIn(byte[] value) {
        if (value.length < 2) {
            throw new ProtocolException("数据长度小于2字节!");
        }

        // DI值
        this.di1h = (byte) ((value[1] & 0xf0) >> 4);
        this.di1l = (byte) (value[1] & 0x0f);
        this.di0h = (byte) ((value[0] & 0xf0) >> 4);
        this.di0l = (byte) (value[0] & 0x0f);
    }

    /**
     * 1997版的DIn2字节
     *
     * @return
     */
    @Override
    public int getDInLen() {
        return 2;
    }
}
