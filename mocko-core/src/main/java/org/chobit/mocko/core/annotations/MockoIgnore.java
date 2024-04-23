package org.chobit.mocko.core.annotations;

import java.lang.annotation.*;

/**
 * 提示不需要执行mock处理的注解
 *
 * @author robin
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MockoIgnore {

}
