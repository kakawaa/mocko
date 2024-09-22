package org.chobit.mocko.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 处理类
 *
 * @author robin
 */
public class MockoProvider {


    public static Builder builder() {
        return new Builder();
    }


    private final InvocationHandlerFactory factory;

    private final Decoder decoder;

    private final String appId;

    private final String mockUrl;


    public MockoProvider(String appId,
                         String mockUrl,
                         Decoder decoder,
                         InvocationHandlerFactory factory) {
        this.appId = appId;
        this.mockUrl = mockUrl;
        this.decoder = decoder;
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
    public <T> T newInstance(Target target) {
        InvocationHandler handler = factory.create(appId, mockUrl, target, decoder);
        return (T) Proxy.newProxyInstance(target.type().getClassLoader(), new Class<?>[]{target.type()}, handler);
    }


    public static class Builder {

        private InvocationHandlerFactory invocationHandlerFactory = new InvocationHandlerFactory.Default();


        private String appId;


        private String mockUrl;


        private Decoder decoder;


        public Builder decoder(Decoder decoder) {
            this.decoder = decoder;
            return this;
        }


        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }


        public Builder mockUrl(String mockUrl) {
            this.mockUrl = mockUrl;
            return this;
        }


        public Builder invocationHandlerFactory(InvocationHandlerFactory invocationHandlerFactory) {
            this.invocationHandlerFactory = invocationHandlerFactory;
            return this;
        }


        public MockoProvider build() {
            return new MockoProvider(appId, mockUrl, decoder, invocationHandlerFactory);
        }


    }

}
