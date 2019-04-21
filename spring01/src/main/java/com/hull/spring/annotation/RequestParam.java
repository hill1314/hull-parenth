package com.hull.spring.annotation;

import java.lang.annotation.*;

/**
 *
 *
 * @author
 * @create 2018-11-11 下午10:39
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
}
