package com.foxteam.device.protocol.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class FoxEdgeBulkOperateMethodEntity extends FoxEdgeBulkOperateEntity {
    /**
     * 用户操作实体
     */
    private OperateEntity operateEntity = new OperateEntity();

    @Data
    static public class OperateEntity implements Serializable {
        /**
         * 操作名称
         */
        private String operateName = "";

        /**
         * 通信超时
         */
        private Integer timeout = 2000;

        /**
         * 参数集合
         */
        private Map<String, Object> params = new HashMap<>();
    }

}
