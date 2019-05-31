package com.datasource.springdatas.config;

import java.lang.annotation.*;

/**
 * @author xuechaofu
 * @date 2019/5/30 20:32
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {

    String dataSource() default "";
}

