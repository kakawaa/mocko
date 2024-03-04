package org.chobit.mocko.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 应用模块注解
 *
 * @author rui.zhang
 */
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

    /**
     * 模块相关信息
     *
     * @return 模块信息
     */
    String value();


}
