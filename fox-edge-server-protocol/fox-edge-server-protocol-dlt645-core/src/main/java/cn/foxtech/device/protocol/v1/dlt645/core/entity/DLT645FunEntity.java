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

import cn.foxtech.device.protocol.v1.dlt645.core.DLT645Define;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 07和09版本的控制代码含义
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class DLT645FunEntity {
    private static final String func_v07_00000 = "保留";
    private static final String func_v07_01000 = "广播校时";
    private static final String func_v07_10001 = "读数据";
    private static final String func_v07_10010 = "读后续数据";
    private static final String func_v07_10011 = "读通信地址";
    private static final String func_v07_10100 = "写数据";
    private static final String func_v07_10101 = "写通信地址";
    private static final String func_v07_10110 = "冻结";
    private static final String func_v07_10111 = "更改通信速率";
    private static final String func_v07_11000 = "修改密码";
    private static final String func_v07_11001 = "最大需量清零";
    private static final String func_v07_11010 = "电表清零";
    private static final String func_v07_11011 = "事件清零";

    private static final String func_v97_00000 = "保留";
    private static final String func_v97_01000 = "广播校时";
    private static final String func_v97_00001 = "读数据";
    private static final String func_v97_00010 = "读后续数据";
    private static final String func_v97_00011 = "重读数据";
    private static final String func_v97_00100 = "写数据";
    private static final String func_v97_01010 = "写通信地址";
    private static final String func_v97_01100 = "更改通信速率";
    private static final String func_v97_01111 = "修改密码";
    private static final String func_v97_10000 = "最大需量清零";
    /**
     * 方向：主站发出=false，从站应答=true
     */
    private boolean direct = false;
    /**
     * 从站是否异常应答
     */
    private boolean error = false;
    /**
     * 功能代码
     */
    private byte code = 0;
    /**
     * 是否最后的尾部
     */
    private boolean next = false;

    public static DLT645FunEntity decodeEntity(byte func) {
        DLT645FunEntity dlt645FunEntity = new DLT645FunEntity();
        dlt645FunEntity.decode(func);
        return dlt645FunEntity;
    }

    public static int getCodev1997(String text) {
        if (func_v97_00000.equals(text)) {
            return 0b00000;
        }
        if (func_v97_01000.equals(text)) {
            return 0b01000;
        }
        if (func_v97_00001.equals(text)) {
            return 0b00001;
        }
        if (func_v97_00010.equals(text)) {
            return 0b00010;
        }
        if (func_v97_00100.equals(text)) {
            return 0b00100;
        }
        if (func_v97_01010.equals(text)) {
            return 0b01010;
        }
        if (func_v97_01100.equals(text)) {
            return 0b01100;
        }
        if (func_v97_01111.equals(text)) {
            return 0b01111;
        }
        if (func_v97_10000.equals(text)) {
            return 0b10000;
        }

        return 0b00000;
    }

    /**
     * 编码
     *
     * @return 功能码
     */
    public byte encode() {
        int func = 0;
        if (this.direct) {
            func |= 0x80;
        }
        if (this.error) {
            func |= 0x40;
        }
        if (this.next) {
            func |= 0x20;
        }
        func |= this.code & 0x1F;

        return (byte) func;
    }

    /**
     * 生成功能码
     *
     * @param dlt645FunEntity
     * @return
     */
    public byte encodeEntity(DLT645FunEntity dlt645FunEntity) {
        return dlt645FunEntity.encode();
    }

    /**
     * 解码
     *
     * @param func
     */
    public void decode(byte func) {
        this.direct = (func & 0x80) > 0;
        this.error = (func & 0x40) > 0;
        this.next = (func & 0x20) > 0;
        this.code = (byte) (func & 0x1F);
    }

    public String getCodeTextV1997() {
        if (this.code == 0b00000) {
            return func_v97_00000;
        }
        if (this.code == 0b01000) {
            return func_v97_01000;
        }
        if (this.code == 0b00001) {
            return func_v97_00001;
        }
        if (this.code == 0b00010) {
            return func_v97_00010;
        }
        if (this.code == 0b00100) {
            return func_v97_00100;
        }
        if (this.code == 0b01010) {
            return func_v97_01010;
        }
        if (this.code == 0b01100) {
            return func_v97_01100;
        }
        if (this.code == 0b01111) {
            return func_v97_01111;
        }
        if (this.code == 0b10000) {
            return func_v97_10000;
        }

        return "";
    }

    public String getCodeTextV2007() {
        if (this.code == 0b00000) {
            return func_v07_00000;
        }
        if (this.code == 0b01000) {
            return func_v07_01000;
        }
        if (this.code == 0b10001) {
            return func_v07_10001;
        }
        if (this.code == 0b10010) {
            return func_v07_10010;
        }
        if (this.code == 0b10011) {
            return func_v07_10011;
        }
        if (this.code == 0b10100) {
            return func_v07_10100;
        }
        if (this.code == 0b10101) {
            return func_v07_10101;
        }
        if (this.code == 0b10110) {
            return func_v07_10110;
        }
        if (this.code == 0b10111) {
            return func_v07_10111;
        }
        if (this.code == 0b11000) {
            return func_v07_11000;
        }
        if (this.code == 0b11001) {
            return func_v07_11001;
        }
        if (this.code == 0b11010) {
            return func_v07_11010;
        }
        if (this.code == 0b11011) {
            return func_v07_11011;
        }

        return "";
    }

    /**
     * 获取文本描述
     *
     * @param ver v07 或者是 v97
     * @return 文本描述
     */
    public String getMessage(String ver) {
        String message = "";
        if (this.direct) {
            message += "从站发出:";
        } else {
            message += "主站发出:";
        }

        if (ver.equalsIgnoreCase(DLT645Define.PRO_VER_1997)) {
            message += this.getCodeTextV1997();
        }
        if (ver.equalsIgnoreCase(DLT645Define.PRO_VER_2007)) {
            message += this.getCodeTextV2007();
        }


        if (this.error) {
            message += ":异常";
        } else {
            message += ":正常";
        }


        if (this.next) {
            message += ":还有后续帧";
        } else {
            message += ":这是末尾帧";
        }

        return message;
    }
}
