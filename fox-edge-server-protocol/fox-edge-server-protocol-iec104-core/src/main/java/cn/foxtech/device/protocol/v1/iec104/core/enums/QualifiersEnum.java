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

package cn.foxtech.device.protocol.v1.iec104.core.enums;

import lombok.Getter;


/**
 * QualifiersEnum 枚举值定义
 */
public enum QualifiersEnum {

    /**
     * 总召唤限定词
     */
    generalCallQualifiers(AsduTypeIdEnum.generalCall, 0x20),

    /**
     * 总召唤限定词 支持 老版的分组
     */
    generalCallGroupingQualifiers(AsduTypeIdEnum.generalCall, 0x14),
    /**
     * 复位进程限定词
     */
    resetPprocessQualifiers(AsduTypeIdEnum.resetPprocess, 0x01),
    /**
     * 初始化原因 当地电源合上
     */
    localCloseUpQualifiers(AsduTypeIdEnum.initEnd, 0x00),
    /**
     * 初始化原因 当地手动复位
     */
    localMmanualResetQualifiers(AsduTypeIdEnum.initEnd, 0x01),
    /**
     * 远方复位
     */
    distanceResetQualifiers(AsduTypeIdEnum.initEnd, 0x02),
    /**
     * 品质描述词  遥测
     */
    qualityQualifiers(null, 0x00),
    /**
     * 设置命令限定词  选择预置参数 1000 0000
     */
    prefabParameterQualifiers(null, 0x40),
    /**
     * 设置命令限定词  执行激活参数
     */
    activationParameterQualifiers(null, 0x0F);

    @Getter
    private final byte value;
    @Getter
    private final AsduTypeIdEnum typeIdentifier;

    QualifiersEnum(AsduTypeIdEnum typeIdentifier, int value) {
        this.value = (byte) value;
        this.typeIdentifier = typeIdentifier;
    }


    /**
     * 根据传输类型和 限定词的关系返回 限定词的类型
     *
     * @param typeIdentifier Asdu枚举值
     * @param value 数值
     * @return QualifiersEnum
     */
    public static QualifiersEnum getQualifiersEnum(AsduTypeIdEnum typeIdentifier, byte value) {
        for (QualifiersEnum type : QualifiersEnum.values()) {
            if (type.getValue() == value && type.getTypeIdentifier() == typeIdentifier) {
                return type;
            }
        }
        // 品质描述词和设置参数 限定词对应多个值 所以需要做特殊处理
        QualifiersEnum qualifiersEnum = null;
        if ((AsduTypeIdEnum.normalizedTelemetry.equals(typeIdentifier) || AsduTypeIdEnum.scaledTelemetry.equals(typeIdentifier) || AsduTypeIdEnum.shortFloatingPointTelemetry.equals(typeIdentifier)) && qualityQualifiers.getValue() == value) {
            qualifiersEnum = qualityQualifiers;
        }
        if ((AsduTypeIdEnum.readOneParameter.equals(typeIdentifier) || AsduTypeIdEnum.readMultipleParameter.equals(typeIdentifier) || AsduTypeIdEnum.prefabActivationOneParameter.equals(typeIdentifier) || AsduTypeIdEnum.prefabActivationMultipleParameter.equals(typeIdentifier)) && qualityQualifiers.getValue() == value) {
            qualifiersEnum = qualityQualifiers;
        }
        return qualifiersEnum;
    }
}
