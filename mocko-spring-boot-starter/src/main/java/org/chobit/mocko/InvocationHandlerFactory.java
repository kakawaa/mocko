package org.chobit.mocko;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

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
    InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch);


    interface MethodHandler {


        Object invoke(Object[] args) throws Throwable;
    }


}
