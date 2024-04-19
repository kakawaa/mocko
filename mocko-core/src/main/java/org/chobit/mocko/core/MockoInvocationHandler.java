package org.chobit.mocko.core;

import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.core.InvocationHandlerFactory.MethodHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * 通过动态代理
 *
 * @author rui.zhang
 */
public class MockoInvocationHandler extends MockoAspectSupport implements InvocationHandler {


    private final Target<?> target;

    private final Map<Method, MethodHandler> dispatch;


    public MockoInvocationHandler(Target<?> target, Decoder decoder, Map<Method, MethodHandler> dispatch) {
        super(decoder);
        this.target = target;
        this.dispatch = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return JsonKit.fromJson("{}", method.getReturnType());
    }
}
