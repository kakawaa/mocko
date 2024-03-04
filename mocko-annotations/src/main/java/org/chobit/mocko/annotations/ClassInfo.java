package org.chobit.mocko.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类信息
 *
 * @author rui.zhang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo {

    /**
     * 类信息
     *
     * @return 类信息
     */
    String value();

}
