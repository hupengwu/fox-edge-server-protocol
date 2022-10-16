package com.foxteam.device.protocol.modbus;


import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.annotation.FoxEdgeOperate;
import com.foxteam.device.protocol.core.exception.ProtocolException;
import com.foxteam.device.protocol.core.protocol.modbus.ModBusConstants;
import com.foxteam.device.protocol.core.protocol.modbus.ModBusProtocol;
import com.foxteam.device.protocol.core.protocol.modbus.ModBusProtocolFactory;
import com.foxteam.device.protocol.core.protocol.modbus.ModBusReadStatusRespond;
import com.foxteam.device.protocol.core.utils.HexUtils;
import com.foxteam.device.protocol.modbus.template.JReadStatusTemplate;
import com.foxteam.device.protocol.core.template.TemplateFactory;

import java.util.Map;

/**
 * 读取Status
 */
@FoxEdgeDeviceType(value = "ModBus Device", manufacturer = "Fox Edge")
public class ModBusProtocolReadStatus {
    /**
     * 读取保持寄存器
     *
     * @param param 必须包含device_addr和modbus_holding_registers_template两个输入参数
     * @return 16进制文本格式的报文
     */
    @FoxEdgeOperate(name = "Read Coil Status", polling = true, type = FoxEdgeOperate.encoder, timeout = 2000)
    public static String packReadCoilStatus(Map<String, Object> param) {
        return (String) operateReadStatus("", param);
    }

    /**
     * 解码保持寄存器
     *
     * @param hexString 16进制文本格式的报文
     * @param param     必须包含 device_addr 和 modbus_holding_registers_template 两个输入参数
     * @return 解码后的数据
     */
    @FoxEdgeOperate(name = "Read Coil Status", polling = true, type = FoxEdgeOperate.decoder, timeout = 2000)
    public static Map<String, Object> unpackReadCoilStatus(String hexString, Map<String, Object> param) {
        return (Map<String, Object>) operateReadStatus(hexString, param);
    }


    /**
     * 读取保持寄存器
     *
     * @param param 必须包含device_addr和modbus_holding_registers_template两个输入参数
     * @return 16进制文本格式的报文
     */
    @FoxEdgeOperate(name = "Read Input Status", polling = true, type = FoxEdgeOperate.encoder, timeout = 2000)
    public static String packReadInputStatus(Map<String, Object> param) {
        return (String) operateReadStatus("", param);
    }

    /**
     * 解码保持寄存器
     *
     * @param hexString 16进制文本格式的报文
     * @param param     必须包含 device_addr 和 modbus_holding_registers_template 两个输入参数
     * @return 解码后的数据
     */
    @FoxEdgeOperate(name = "Read Input Status", polling = true, type = FoxEdgeOperate.decoder, timeout = 2000)
    public static Map<String, Object> unpackReadInputStatus(String hexString, Map<String, Object> param) {
        return (Map<String, Object>) operateReadStatus(hexString, param);
    }

    /**
     * 编码/解码 读取保持寄存器
     *
     * @param hexString 解码报文：如果它为空，那么为编码操作，如果不为空，那么为编码操作
     * @param param     参数表
     * @return 编码为String，解码为Map<String, Object>
     */
    private static Object operateReadStatus(String hexString, Map<String, Object> param) {
        // 取出设备地址
        Integer devAddr = (Integer) param.get("device_addr");
        Integer regAddr = (Integer) param.get("reg_addr");
        Integer regCnd = (Integer) (param.get("reg_cnt"));
        String modbusMode = (String) param.get("modbus_mode");
        String templateName = (String) param.get("template_name");
        String operateName = (String) param.get("operate_name");
        String tableName = (String) param.get("table_name");

        // 参数缺失检查
        if (devAddr == null || regAddr == null || regCnd == null || modbusMode == null || operateName == null || tableName == null || templateName == null) {
            throw new ProtocolException("输入参数异常");
        }

        JReadStatusTemplate template = TemplateFactory.getTemplate("fox-edge-server-device-protocol-modbus").getTemplate(templateName, tableName, JReadStatusTemplate.class);

        // 确定命令字
        Byte func = 0;
        if (JReadStatusTemplate.READ_COIL_STATUS.equals(operateName)) {
            func = 0x01;
        }
        if (JReadStatusTemplate.WRITE_SINGLE_STATUS.equals(operateName)) {
            func = 0x02;
        }

        // 准备参数
        param.put(ModBusConstants.ADDR, devAddr);
        param.put(ModBusConstants.REG_ADDR, regAddr);
        param.put(ModBusConstants.REG_CNT, regCnd);
        param.put(ModBusConstants.MODE, modbusMode);

        // 选择对应的协议类型
        ModBusProtocol protocol = ModBusProtocolFactory.createProtocol(modbusMode);

        // 编码处理
        if (hexString.isEmpty()) {
            // 返回处理数据
            byte[] pack = protocol.packCmdReadStatus4Map(func, param);
            return HexUtils.byteArrayToHexString(pack);
        }

        // 解码处理

        byte[] arrCmd = HexUtils.hexStringToByteArray(hexString);
        // 报文解析
        ModBusReadStatusRespond respond = protocol.unPackCmdReadStatus2Respond(arrCmd);
        if (respond == null) {
            throw new ProtocolException("报文格式不正确，解析失败！");
        }

        // 使用模板拆解数据
        return template.decode(regAddr, regCnd, respond.getStatus());
    }
}
