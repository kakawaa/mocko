package org.chobit.mocko;

import org.chobit.mocko.InvocationHandlerFactory.MethodHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * 用来创建mocko实例
 *
 * @author rui.zhang
 */
public class MockoInvocationHandler implements InvocationHandler {


    private final Target<?> target;

    private final Map<Method, MethodHandler> dispatch;


    public MockoInvocationHandler(Target<?> target, Map<Method, MethodHandler> dispatch) {
        this.target = target;
        this.dispatch = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
