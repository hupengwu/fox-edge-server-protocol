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

package cn.foxtech.device.protocol.v1.hikvision.fire.core.entity.infobj;

import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import cn.foxtech.device.protocol.v1.hikvision.fire.core.utils.TimeUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息对象: 注册包
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class InfObjDeviceSoftVerEntity extends InfObjEntity {
    /**
     * 主版本号（1 字节）
     */
    private int mainVersion = 0;
    /**
     * 次版本号（1 字节）
     */
    private int secondVersion = 0;

    /**
     * 时间标签(6 字节)：控制单元中时间标签传输，秒在前，年在后，取自系统当前时间，如 15:14:17 11/9/19；
     */
    private String time = "2000-01-01 00:00:00";

    public static void decodeEntity(byte[] data, InfObjDeviceSoftVerEntity entity) {
        if (data.length != entity.size()) {
            throw new ProtocolException("信息对象" + entity.getClass().getSimpleName() + "，必须长度为" + entity.size());
        }


        int index = 0;

        // 系统类型(1 字节)
        entity.mainVersion = data[index++] & 0xff;

        // 系统地址(1 字节)
        entity.secondVersion = data[index++] & 0xff;

        // 时间标签(6 字节)
        entity.time = TimeUtil.decodeTime6byte(data, index);
        index += 6;
    }

    public static byte[] encodeEntity(InfObjDeviceSoftVerEntity entity) {
        byte[] data = new byte[entity.size()];


        int index = 0;

        // 系统类型(1 字节)
        data[index++] = (byte) entity.mainVersion;

        // 系统地址(1 字节)
        data[index++] = (byte) entity.secondVersion;


        // 时间标签(6 字节)
        TimeUtil.encodeTime6byte(entity.time, data, index);
        index += 6;

        return data;
    }

    @Override
    public List<Integer> getAduSizes(byte[] data, int offset, int aduLength) {
        // 信息体的数量
        int count = data[offset + 1];

        // 类型标志[1 字节]+信息体数量[1 字节]+多个信息体对象[N 字节]
        int length = count * this.size();

        if (aduLength != 2 + length) {
            throw new ProtocolException("验证ADU的长度与具体的格式，不匹配");
        }

        // 返回列表
        List<Integer> aduList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            aduList.add(this.size());
        }
        return aduList;
    }

    public int size() {
        return 2 + 6;
    }


    @Override
    public void decode(byte[] data) {
        decodeEntity(data, this);
    }

    @Override
    public byte[] encode() {
        return encodeEntity(this);
    }


}
