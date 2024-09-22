package org.chobit.mocko.autoconfigure;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.chobit.mocko.core.Decoder;
import org.chobit.mocko.core.MockoAspectSupport;
import org.chobit.mocko.core.OperationInvoker;

import java.io.Serializable;


/**
 * Mocko方法拦截器定义
 *
 * @author robin
 */
public class MockoInterceptor extends MockoAspectSupport implements MethodInterceptor, Serializable {


    private static final long serialVersionUID = -2894283504201230353L;


    private final MockoProperties mockoProperties;

    public MockoInterceptor(MockoProperties mockoProperties, Decoder decoder) {
        super(decoder);
        this.mockoProperties = mockoProperties;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        OperationInvoker invoker = () -> {
            try {
                return invocation.proceed();
            } catch (Throwable t) {
                throw new OperationInvoker.WrappedThrowableException(t);
            }
        };

        String appId = mockoProperties.getAppId();
        String mockUrl = mockoProperties.getMockUrl();

        try {
            return execute(invoker, appId, mockUrl, invocation.getThis(), invocation.getMethod(), invocation.getArguments());
        } catch (OperationInvoker.WrappedThrowableException wte) {
            throw wte.getOriginal();
        }
    }

}
