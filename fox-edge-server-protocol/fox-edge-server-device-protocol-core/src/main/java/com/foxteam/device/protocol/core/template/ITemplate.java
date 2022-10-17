package com.foxteam.device.protocol.core.template;

/**
 * 配置模板接口
 */
public interface ITemplate {
    /**
     * 系统级模板：代表的是文件格式
     *
     * @return
     */
    String getSysTemplateName();

    /**
     * 转载CSV模板文件
     *
     * @param table
     */
    void loadCsvFile(String table);
}
