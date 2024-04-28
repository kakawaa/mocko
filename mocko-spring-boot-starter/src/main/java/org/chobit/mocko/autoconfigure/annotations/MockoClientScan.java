package org.chobit.mocko.autoconfigure.annotations;

import org.chobit.mocko.autoconfigure.MockoClientsScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 支持MockoClient
 *
 * @author robin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MockoClientsScannerRegistrar.class)
public @interface MockoClientScan {


    String[] value() default {};
}
