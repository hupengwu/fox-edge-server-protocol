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

package cn.foxtech.device.protocol.v1.midea.dcm9.uitls;

import cn.foxtech.device.protocol.v1.midea.dcm9.enums.Type;

public class TypeValueUtils {
    public static Object getTypeValueText(Type type, int value) {
        switch (type) {
            case EC0: {//运行模式设定
                switch (value) {
                    case 0x00:
                        return "自动";
                    case 0x01:
                        return "制冷";
                    case 0x02:
                        return "除湿";
                    case 0x03:
                        return "送风";
                    case 0x04:
                        return "制热";
                    default:
                        return "";
                }
            }
            case EC1: {//内风机风速设定
                switch (value) {
                    case 0x00:
                        return "停";
                    case 0x01:
                        return "低风";
                    case 0x02:
                        return "中风";
                    case 0x03:
                        return "高风";
                    default:
                        return "";
                }
            }
            case EC2: {//摆风功能设定
                switch (value) {
                    case 0x00:
                        return "停止";
                    case 0x01:
                        return "运转";
                    default:
                        return "";
                }
            }
            case EC3: {//屏蔽本地操作
                switch (value) {
                    case 0x00:
                        return "允许";
                    case 0x01:
                        return "屏蔽";
                    default:
                        return "";
                }
            }
            default:
                return value;
        }
    }

    public static Object getTypeValueInteger(Type type, String text) {
        switch (type) {
            case EC0: {//运行模式设定
                if (text.equals("自动")) {
                    return 0x00;
                }
                if (text.equals("制冷")) {
                    return 0x01;
                }
                if (text.equals("除湿")) {
                    return 0x02;
                }
                if (text.equals("送风")) {
                    return 0x03;
                }
                if (text.equals("制热")) {
                    return 0x04;
                }

                return 0x00;
            }
            case EC1: {//内风机风速设定
                if (text.equals("停")) {
                    return 0x00;
                }
                if (text.equals("低风")) {
                    return 0x01;
                }
                if (text.equals("中风")) {
                    return 0x02;
                }
                if (text.equals("高风")) {
                    return 0x03;
                }

                return 0x00;
            }
            case EC2: {//摆风功能设定
                if (text.equals("停止")) {
                    return 0x00;
                }
                if (text.equals("运转")) {
                    return 0x01;
                }

                return 0x00;
            }
            case EC3: {//屏蔽本地操作
                if (text.equals("允许")) {
                    return 0x00;
                }
                if (text.equals("屏蔽")) {
                    return 0x01;
                }

                return 0x00;
            }
            default:
                return 0x00;
        }
    }
}

