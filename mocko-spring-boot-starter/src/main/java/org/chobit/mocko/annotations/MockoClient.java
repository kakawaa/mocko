package org.chobit.mocko.annotations;

import java.lang.annotation.*;

/**
 * Mocko客户端注解
 *
 * @author rui.zhang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MockoClient {


    String componentId() default "";


}
