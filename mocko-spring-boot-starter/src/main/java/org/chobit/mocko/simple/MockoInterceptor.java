package org.chobit.mocko.simple;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.chobit.mocko.OperationInvoker;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Mocko方法拦截器定义
 *
 * @author rui.zhang
 */
public class MockoInterceptor extends MockoAspectSupport implements MethodInterceptor, Serializable {



    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        OperationInvoker invoker = () -> {
            try {
                return invocation.proceed();
            } catch (Throwable t) {
                throw new OperationInvoker.WrappedThrowableException(t);
            }
        };


        try {
            return execute(invoker, invocation.getThis(), invocation.getMethod(), invocation.getArguments());
        } catch (OperationInvoker.WrappedThrowableException wte) {
            throw wte.getOriginal();
        }
    }




    protected Object execute(final OperationInvoker invoker, @Nullable final Object target, Method method, Object[] args) {
        return null;
    }

}
