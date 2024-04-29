package org.chobit.mocko.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 通过动态代理
 *
 * @author rui.zhang
 */
public class MockoInvocationHandler extends MockoAspectSupport implements InvocationHandler {


    private final String appId;

    private final String mockUrl;

    private final Target target;


    public MockoInvocationHandler(String appId, String mockUrl, Target target, Decoder decoder) {
        super(decoder);
        this.appId = appId;
        this.mockUrl = mockUrl;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return super.execute(null, this.appId, this.mockUrl, this.target, method, args);
    }
}
