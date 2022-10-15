package com.foxteam.device.protocol.core.entity;

import com.foxteam.device.protocol.core.channel.FoxEdgeChannelService;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器实体：模板工作模式和单步操作模式
 */
@Data
public abstract class FoxEdgeBulkOperateEntity {
    /**
     * 设备实体
     */
    private DeviceEntity deviceEntity = new DeviceEntity();

    /**
     * 设备配置实体
     */
    private DeviceConfigEntity configEntity = new DeviceConfigEntity();


    /**
     * 通道服务
     */
    private FoxEdgeChannelService channelService;

    /**
     * 设备状态实体
     */
    private DeviceStatusEntity statusEntity = new DeviceStatusEntity();

    /**
     * 数值实体
     */
    private ValueEntity valueEntity = new ValueEntity();

    /**
     * 内部异常：批量操作中，某些步骤可能发生了异常，但不妨碍整个流程的执行，需要提醒开发人员排除障碍
     */
    private Exception exception;


    @Data
    static public class DeviceConfigEntity implements Serializable {
        public static final String TEMPLATE_OPERATE_LIST_KEY = "operate_list";
        public static final String TEMPLATE_OPERATE_NAME_KEY = "operate_name";
        public static final String TEMPLATE_REFERENCE_KEY = "device_ref_template";

        /**
         * 配置集合
         */
        private Map<String, Object> params = new HashMap<>();
    }

    /**
     * 设备状态
     */
    @Data
    static public class DeviceStatusEntity implements Serializable {
        /**
         * ID
         */
        private long id = 0;

        /**
         * 最近通信成功的时间，方便判定设备是否断连
         */
        private long commSucessTime = 0;

        /**
         * 最近访问失败的时间
         */
        private long commFailedTime = 0;

        /**
         * 连续访问失败的次数
         */
        private int commFailedCount = 0;
    }

    @Data
    static public class DeviceEntity implements Serializable {
        /**
         * 设备名称
         */
        private String deviceName;

        /**
         * 设备类型名
         */
        private String deviceType;
    }


    @Data
    static public class ValueEntity implements Serializable {
        private Map<String, Object> values = new HashMap<>();
    }
}
