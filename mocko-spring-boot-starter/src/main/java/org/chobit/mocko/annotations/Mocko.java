package org.chobit.mocko.annotations;


import java.lang.annotation.*;

/**
 * 注解
 *
 * @author rui.zhang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mocko {

    /**
     * name of method
     */
    String value() default "";

}
