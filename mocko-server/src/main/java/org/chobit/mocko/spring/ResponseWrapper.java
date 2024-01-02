package org.chobit.mocko.spring;


import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author robin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseWrapper {



}