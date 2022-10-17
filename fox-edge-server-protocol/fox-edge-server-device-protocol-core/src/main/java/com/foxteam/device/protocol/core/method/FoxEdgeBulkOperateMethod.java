package com.foxteam.device.protocol.core.method;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * 批量操作函数信息：控制器框架在扫描解码器后，形成的批量操作函数信息
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class FoxEdgeBulkOperateMethod extends FoxEdgeBaseMethod {
    /**
     * 编码函数
     */
    private Method method;

    /**
     * 整体超时
     */
    private int timeout = 8000;
}
