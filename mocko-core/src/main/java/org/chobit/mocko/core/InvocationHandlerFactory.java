package org.chobit.mocko.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * InvocationHandler 工厂接口
 *
 * @author rui.zhang
 */
public interface InvocationHandlerFactory {


    /**
     * 创建InvocationHandler实例
     *
     * @param target   目标类
     * @param dispatch 方法和Handler的映射
     * @return 方法对应的InvocationHandler
     */
    InvocationHandler create(Target<?> target, Map<Method, MethodHandler> dispatch);


    interface MethodHandler {

        /**
         * 执行方法调用
         *
         * @param args 方法参数
         * @return 方法执行结果
         * @throws Throwable 抛出的异常
         */
        Object invoke(Object[] args) throws Throwable;
    }


     final class Default implements InvocationHandlerFactory {

        @Override
        public InvocationHandler create(Target<?> target, Map<Method, MethodHandler> dispatch) {
            return new MockoInvocationHandler(target, dispatch);
        }
    }

}
