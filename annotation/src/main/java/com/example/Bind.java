package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2017/8/21
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Bind {

    int value();
}
