package org.chobit.mocko.core;


import org.chobit.mocko.core.InvocationHandlerFactory.MethodHandler;

import java.lang.reflect.Method;


/**
 * @author rui.zhang
 */
public class MockoMethodHandler implements MethodHandler {


    public MockoMethodHandler(Method defaultMethod) {
    }


    @Override
    public Object invoke(Object[] args) throws Throwable {
        return null;
    }


}
