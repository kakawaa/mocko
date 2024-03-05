package org.chobit.mocko.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 应用model
 *
 * @author rui.zhang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {


    /**
     * model信息
     *
     * @return model信息
     */
    String value();

}
