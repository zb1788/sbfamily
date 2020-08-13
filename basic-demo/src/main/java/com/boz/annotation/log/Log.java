package com.boz.annotation.log;

import com.boz.annotation.log.constant.enums.BusinessType;

import java.lang.annotation.*;

/**
 * @author boz
 * @date 2019/7/30
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log{
    /** 模块 */
    String title() default "";

    /** 功能 */
    BusinessType businessType() default BusinessType.OTHER;


    /** 是否保存请求的参数 */
    boolean isSaveRequestData() default true;
}
