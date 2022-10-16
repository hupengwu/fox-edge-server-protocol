package com.foxteam.device.protocol.mitsubishi.plc.fx.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MitsubishiPlcFxTemplate {
    private static final Map<String, Object> template = new ConcurrentHashMap<>();

    public static MitsubishiPlcFxTemplate newInstance() {
        return new MitsubishiPlcFxTemplate();
    }

    /**
     * 验证操作名
     *
     * @param operateName 操作名
     * @return 是否支持
     */
    public boolean verityOperateName(String operateName) {
        if (JReadRegistersTemplate.WRITE_SINGLE_REGISTER.equals(operateName) || JReadRegistersTemplate.READ_INPUT_REGISTER.equals(operateName) || JReadRegistersTemplate.READ_HOLDING_REGISTER.equals(operateName)) {
            return true;
        }
        return JReadStatusTemplate.WRITE_SINGLE_STATUS.equals(operateName) || JReadStatusTemplate.READ_COIL_STATUS.equals(operateName) || JReadStatusTemplate.WRITE_SINGLE_STATUS.equals(operateName);
    }

    /**
     * 装载从CSV文件中模板格式
     *
     * @param defaultTable
     * @return
     */
    private Object loadTemplate(String operateName, String defaultTable) {
        if (JReadRegistersTemplate.WRITE_SINGLE_REGISTER.equals(operateName) || JReadRegistersTemplate.READ_INPUT_REGISTER.equals(operateName) || JReadRegistersTemplate.READ_HOLDING_REGISTER.equals(operateName)) {
            JReadRegistersTemplate registersTemplate = new JReadRegistersTemplate();
            registersTemplate.loadCsvFile(defaultTable);

            return registersTemplate;
        }
        if (JReadStatusTemplate.WRITE_SINGLE_STATUS.equals(operateName) || JReadStatusTemplate.READ_COIL_STATUS.equals(operateName) || JReadStatusTemplate.WRITE_SINGLE_STATUS.equals(operateName)) {
            JReadStatusTemplate statusTemplate = new JReadStatusTemplate();
            statusTemplate.loadCsvFile(defaultTable);

            return statusTemplate;
        }

        return null;
    }

    /**
     * 获得模板
     *
     * @param operateName  操作名称，比如Read Holding Register
     * @param templateName 模板名称，比如Read System Measures Table
     * @param defaultTable CSV文件名称，比如101.CETUPS_Read System Measures Table.csv
     * @param clazz        模板对象类型 比如JHoldingRegistersTemplate.class
     * @param <T>          模板对象类型，比如JHoldingRegistersTemplate
     * @return 模板对象 比如jHoldingRegistersTemplate
     */
    public <T> T getTemplate(String operateName, String templateName, String defaultTable, Class<T> clazz) {
        // 验证：操作名是否是规定范围内的名称
        if (!verityOperateName(operateName)) {
            return null;
        }

        // 获得该操作类型的模板列表
        Map<String, Object> template4operate = (Map<String, Object>) template.get(operateName);
        if (template4operate == null) {
            template4operate = new ConcurrentHashMap<>();
            template.put(operateName, template4operate);
        }

        // 获得该模板名称的模板
        Object template = template4operate.get(templateName);
        if (template == null) {
            // 不存在的话，就根据表名称构造模板
            template = this.loadTemplate(operateName, defaultTable);
            if (template == null) {
                return null;
            }

            if (template instanceof JReadRegistersTemplate) {
                JReadRegistersTemplate jReadRegistersTemplate = (JReadRegistersTemplate) template;
                jReadRegistersTemplate.setTemplate_name(templateName);
                jReadRegistersTemplate.getOperate().setOperate_name(operateName);
                jReadRegistersTemplate.getOperate().getDecoder_param().setTable(defaultTable);
            }
            if (template instanceof JReadStatusTemplate) {
                JReadStatusTemplate jReadStatusTemplate = (JReadStatusTemplate) template;
                jReadStatusTemplate.setTemplate_name(templateName);
                jReadStatusTemplate.getOperate().setOperate_name(operateName);
                jReadStatusTemplate.getOperate().getDecoder_param().setTable(defaultTable);
            }

            // 保存模板
            template4operate.put(templateName, template);
        }

        // 转换对象类型
        if (clazz.isInstance(template)) {
            return (T) template;
        }

        return null;
    }
}
