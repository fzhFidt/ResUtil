package com.example.res.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResField {
    int resId() default -1;//默认ViewId为-1
    FieldType fieldType() default FieldType.ID;//哪种类型
}
