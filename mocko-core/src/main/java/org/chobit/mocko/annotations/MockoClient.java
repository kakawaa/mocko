package org.chobit.mocko.annotations;

import java.lang.annotation.*;

/**
 * 用来标识Mocko动态代理类
 *
 * @author rui.zhang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MockoClient {


    String value() default "";


}