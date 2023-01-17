package org.chobit.mocko;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 用来创建mocko实例
 *
 * @author rui.zhang
 */
public class MockoInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
