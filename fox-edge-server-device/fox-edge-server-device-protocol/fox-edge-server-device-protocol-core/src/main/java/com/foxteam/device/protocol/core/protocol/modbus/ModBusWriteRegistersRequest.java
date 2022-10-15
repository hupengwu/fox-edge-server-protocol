package com.foxteam.device.protocol.core.protocol.modbus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 寄存器状态查询请求
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ModBusWriteRegistersRequest {
    /**
     * 报文实体
     */
    private ModBusEntity entity = new ModBusEntity();

    /**
     * 地址偏移量
     */
    private int memAddr = 0;

    /**
     * 16位的数值
     */
    private int value = 0;
}
