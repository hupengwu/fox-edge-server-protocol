package com.foxteam.device.protocol.modbus;

import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.annotation.FoxEdgeOperate;
import com.foxteam.device.protocol.core.exception.ProtocolException;
import com.foxteam.device.protocol.core.protocol.modbus.*;
import com.foxteam.device.protocol.core.utils.HexUtils;
import com.foxteam.device.protocol.modbus.template.JReadRegistersTemplate;
import com.foxteam.device.protocol.modbus.template.ModBusTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取Registers
 */
@FoxEdgeDeviceType(value = "ModBus Device", manufacturer = "Fox Edge")
public class ModBusProtocolWriteRegisters {
    /**
     * 读取保持寄存器
     *
     * @param param 必须包含device_addr和modbus_holding_registers_template两个输入参数
     * @return 16进制文本格式的报文
     */
    @FoxEdgeOperate(name = "Write Single Register", polling = true, type = FoxEdgeOperate.encoder, timeout = 2000)
    public static String packWriteHoldingRegister(Map<String, Object> param) {
        return (String) operateWriteRegister("", param);
    }

    /**
     * 解码保持寄存器
     *
     * @param hexString 16进制文本格式的报文
     * @param param     必须包含 device_addr 和 modbus_holding_registers_template 两个输入参数
     * @return 解码后的数据
     */
    @FoxEdgeOperate(name = "Write Single Register", polling = true, type = FoxEdgeOperate.decoder, timeout = 2000)
    public static Map<String, Object> unpackWriteHoldingRegister(String hexString, Map<String, Object> param) {
        return (Map<String, Object>) operateWriteRegister(hexString, param);
    }

    /**
     * 编码/解码 读取保持寄存器
     *
     * @param hexString 解码报文：如果它为空，那么为编码操作，如果不为空，那么为编码操作
     * @param param     参数表
     * @return 编码为String，解码为Map<String, Object>
     */
    private static Object operateWriteRegister(String hexString, Map<String, Object> param) {
        // 取出设备地址
        Integer devAddr = (Integer) param.get("device_addr");
        String modbusMode = (String) param.get("modbus_mode");
        String templateName = (String) param.get("template_name");
        String operateName = (String) param.get("operate_name");
        String tableName = (String) param.get("table_name");
        String objectName = (String) param.get("object_name");
        Object objectValue = param.get("object_value");

        // 参数缺失检查
        if (objectName == null || objectValue == null || devAddr == null || modbusMode == null || operateName == null || tableName == null || templateName == null) {
            throw new ProtocolException("输入参数异常");
        }

        JReadRegistersTemplate template = ModBusTemplate.newInstance().getTemplate(operateName, templateName, tableName, JReadRegistersTemplate.class);
        if (template == null) {
            throw new ProtocolException("找不到对应的模板");
        }

        ModBusWriteRegistersRequest writeRegistersRequest = template.encode(objectName, objectValue);
        if (writeRegistersRequest == null) {
            throw new ProtocolException("编码失败");
        }

        // 准备参数
        param.put(ModBusConstants.ADDR, devAddr);
        param.put(ModBusConstants.REG_ADDR, writeRegistersRequest.getMemAddr());
        param.put(ModBusConstants.REG_CNT, 1);
        param.put(ModBusConstants.MODE, modbusMode);

        // 选择对应的协议类型
        ModBusProtocol protocol = ModBusProtocolFactory.createProtocol(modbusMode);

        // 编码处理
        if (hexString.isEmpty()) {
            byte[] pack = protocol.packCmdWriteRegisters4Request(writeRegistersRequest);
            return HexUtils.byteArrayToHexString(pack);
        }

        // 解码处理

        byte[] arrCmd = HexUtils.hexStringToByteArray(hexString);
        // 报文解析
        ModBusWriteRegistersRespond respond = protocol.unPackCmdWriteRegisters2Respond(arrCmd);
        if (respond == null) {
            throw new ProtocolException("报文格式不正确，解析失败！");
        }

        return new HashMap<>();
    }
}
