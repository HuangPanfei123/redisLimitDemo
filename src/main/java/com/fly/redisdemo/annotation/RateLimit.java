package com.fly.redisdemo.annotation;


import java.lang.annotation.*;

/**
 * 限流注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    /**
     * 限流唯一标示
     *
     * @return
     */
    String key() default "";

    /**
     * 限流时间
     *
     * @return
     */
    int time();

    /**
     * 限流次数
     *
     * @return
     */
    int count();
}
