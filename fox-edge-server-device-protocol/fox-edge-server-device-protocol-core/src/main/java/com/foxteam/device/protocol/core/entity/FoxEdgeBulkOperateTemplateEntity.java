package com.foxteam.device.protocol.core.entity;

import lombok.Data;

@Data
public class FoxEdgeBulkOperateTemplateEntity extends FoxEdgeBulkOperateEntity {
    /**
     * 设备配置模板：比如设备类型为Modbus，那么该类型的设备，可能会用到设备类型为Modbus的模板对象
     */
    private DeviceConfigEntity configTemplate = new DeviceConfigEntity();
}
