package org.chobit.mocko.core.annotations;

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


    /**
     * Mock实现的组件名称
     *
     * @return 组件名称
     */
    String value() default "";


    /**
     * 组件的Qualifier值
     *
     * @return Qualifier值
     */
    String qualifier() default "";


    /**
     * 标识组件是否是primary bean
     *
     * @return true 是， false 否
     */
    boolean primary() default true;


}