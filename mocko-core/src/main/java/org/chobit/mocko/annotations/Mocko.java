package org.chobit.mocko.annotations;


import java.lang.annotation.*;

/**
 * Mocko注解
 * <p>
 * 用于标记方法，说明这个方法可以使用Mocko提供的服务
 * </p>
 *
 * @author rui.zhang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mocko {


    /**
     * 方法ID
     * <p>
     * 在服务端会为每个方法对应的记录设置一个ID
     * </p>
     *
     * @return 方法ID
     */
    String mid();

    /**
     * 同mid
     */
    String value();

}
