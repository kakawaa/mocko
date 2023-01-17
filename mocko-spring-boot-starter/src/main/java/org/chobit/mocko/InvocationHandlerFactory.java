package org.chobit.mocko;

import java.lang.reflect.InvocationHandler;

/**
 * InvocationHandler 工厂接口
 *
 * @author rui.zhang
 */
public interface InvocationHandlerFactory {


    /**
     * 创建InvocationHandler实例
     *
     * @return InvocationHandler实例
     */
    InvocationHandler create();


    interface MethodHandler {


        Object invoke(Object[] args) throws Throwable;
    }


}
