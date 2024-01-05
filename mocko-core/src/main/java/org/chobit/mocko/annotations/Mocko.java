package org.chobit.mocko.annotations;


import java.lang.annotation.*;

/**
 * Mocko注解
 * <p>
 * 用于标记类或方法，说明相关方法可以使用Mocko提供的服务
 * </p>
 *
 * @author rui.zhang
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mocko {


    /**
     * 方法说明
     */
    String value() default "";


}
