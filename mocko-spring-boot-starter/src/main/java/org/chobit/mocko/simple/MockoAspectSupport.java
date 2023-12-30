package org.chobit.mocko.simple;

import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.mocko.OperationInvoker;
import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.annotations.MockoClient;
import org.chobit.mocko.model.ArgInfo;
import org.chobit.mocko.model.MethodInfo;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Mocko切面支持
 *
 * @author rui.zhang
 */
class MockoAspectSupport {


    protected Object execute(OperationInvoker invoker, Object target, Method method, Object[] args) {
        return invoker.invoke();
    }


    /**
     * 提取方法信息
     *
     * @param target 执行方法的对象
     * @param method 执行的方法
     * @param args   参数
     * @return 方法信息
     */
    private MethodInfo parseMethodInfo(Object target, Method method, Object[] args) {

        String className = target.getClass().getCanonicalName();
        MockoClient mockoClient = target.getClass().getAnnotation(MockoClient.class);
        String classAlias = "";
        if (null != mockoClient) {
            classAlias = mockoClient.value();
        }

        String methodName = method.getName();
        Mocko mocko = method.getAnnotation(Mocko.class);
        String methodAlias = "";
        if (null != mocko) {
            methodAlias = mocko.value();
        }

        List<ArgInfo> argList = takeArgList(args);

        Class<?> returnType = method.getReturnType();


        String methodId = computeMethodId(className, methodName, argList);

        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethodId(methodId);
        methodInfo.setClassName(className);
        methodInfo.setClassAlias(classAlias);
        methodInfo.setMethodName(methodName);
        methodInfo.setMethodAlias(methodAlias);
        methodInfo.setArgs(argList);

        return methodInfo;
    }


    /**
     * 计算方法ID
     *
     * @param className  类名
     * @param methodName 方法名
     * @param args       参数集合
     * @return 方法ID
     */
    private String computeMethodId(String className, String methodName, List<ArgInfo> args) {
        StringBuilder builder = new StringBuilder();
        builder.append(className)
                .append(Symbol.SHARP).append(methodName)
                .append(Symbol.SHARP);
        for (ArgInfo a : args) {
            builder.append(a.getArgClass()).append(Symbol.COMMA);
        }

        return MD5.encode(builder.toString());
    }


    /**
     * 提取参数信息
     *
     * @param args 参数集合
     * @return 参数信息集合
     */
    private List<ArgInfo> takeArgList(Object[] args) {
        List<ArgInfo> result = new LinkedList<>();
        if (args.length == 0) {
            return result;
        }

        for (int i = 1; i <= args.length; i++) {
            Object o = args[i];

            ArgInfo a = new ArgInfo();
            a.setArgClass(o.getClass().getCanonicalName());
            a.setArgName("arg" + i);

            result.add(a);
        }
        return result;
    }

}
