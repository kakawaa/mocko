package org.chobit.mocko.autoconfigure.annotations;

import org.chobit.mocko.autoconfigure.MockoClientsScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * MockoClient扫描
 *
 * @author robin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MockoClientsScannerRegistrar.class)
public @interface MockoClientScan {


    /**
     * 要扫描的包
     *
     * @return 要扫描的包的集合
     */
    String[] value() default {};

}
