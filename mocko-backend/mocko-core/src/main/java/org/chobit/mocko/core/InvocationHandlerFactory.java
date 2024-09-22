package org.chobit.mocko.core;

import java.lang.reflect.InvocationHandler;

/**
 * InvocationHandler 工厂接口
 *
 * @author robin
 */
public interface InvocationHandlerFactory {


    /**
     * 创建InvocationHandler实例
     *
     * @param appId   应用ID
     * @param mockUrl mock请求路径
     * @param target  目标类
     * @param decoder 响应解析器
     * @return 方法对应的InvocationHandler
     */
    InvocationHandler create(String appId, String mockUrl, Target target, Decoder decoder);


    final class Default implements InvocationHandlerFactory {

        @Override
        public InvocationHandler create(String appId, String mockUrl, Target target, Decoder decoder) {
            return new MockoInvocationHandler(appId, mockUrl, target, decoder);
        }
    }

}
