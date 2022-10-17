package com.foxteam.device.protocol.core.annotation;

import java.lang.annotation.*;

/**
 * 批量操作注解：告知框架，这是一个批量操作函数，可以启动一个线程来执行它
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FoxEdgeBulkOperate {
    /**
     * 参数描述
     *
     * @return
     */
    String name() default "";

    /**
     * 最大通信超时
     *
     * @return
     */
    int timeout() default 8000;
}
