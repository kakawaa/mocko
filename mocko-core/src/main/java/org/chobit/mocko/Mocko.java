package org.chobit.mocko;

import org.chobit.mocko.InvocationHandlerFactory.MethodHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理类
 *
 * @author rui.zhang
 */
public class Mocko {


    public static Builder builder() {
        return new Builder();
    }

    private final Contract contract;

    private final InvocationHandlerFactory factory;


    public Mocko(Contract contract, InvocationHandlerFactory factory) {
        this.contract = contract;
        this.factory = factory;
    }

    /**
     * 返回结果实例
     *
     * @param target Target
     * @param <T>    类型泛型
     * @return 结果实例
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Target<T> target) {

        List<MethodMetadata> metadataList = contract.parseAndValidMetadata(target.type());

        Map<Method, MethodHandler> methodToHandler = new LinkedHashMap<>(8);

        for (MethodMetadata metadata : metadataList) {
            methodToHandler.put(metadata.method(), null);
        }

        InvocationHandler handler = factory.create(target, methodToHandler);
        return (T) Proxy.newProxyInstance(target.type().getClassLoader(), new Class<?>[]{target.type()}, handler);
    }


    static class MockoInvocationHandler implements InvocationHandler {


        private final Target<?> target;

        private final Map<Method, MethodHandler> dispatch;


        public MockoInvocationHandler(Target<?> target, Map<Method, MethodHandler> dispatch) {
            this.target = target;
            this.dispatch = dispatch;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return dispatch.get(method).invoke(args);
        }
    }


    public static class Builder {

        private Contract contract = new Contract.Default();

        private InvocationHandlerFactory invocationHandlerFactory = new InvocationHandlerFactory.Default();


        public Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }


        public Builder invocationHandlerFactory(InvocationHandlerFactory invocationHandlerFactory) {
            this.invocationHandlerFactory = invocationHandlerFactory;
            return this;
        }


        public Mocko build() {
            return new Mocko(contract, invocationHandlerFactory);
        }


    }

}
