package org.chobit.mocko.core;

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


    private final String appId;

    private final String mockUrl;

    private final Target target;

    private final Map<Method, MethodHandler> dispatch;


    public MockoInvocationHandler(String appId, String mockUrl, Target target, Decoder decoder, Map<Method, MethodHandler> dispatch) {
        super(decoder);
        this.appId = appId;
        this.mockUrl = mockUrl;
        this.target = target;
        this.dispatch = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return super.execute(null, this.appId, this.mockUrl, this.target, method, args);
    }
}
