package org.chobit.mocko;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.chobit.mocko.MockoAspectSupport;
import org.chobit.mocko.OperationInvoker;

import java.io.Serializable;


/**
 * Mocko方法拦截器定义
 *
 * @author rui.zhang
 */
public class MockoInterceptor extends MockoAspectSupport implements MethodInterceptor, Serializable {


    private static final long serialVersionUID = -2894283504201230353L;


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

}
