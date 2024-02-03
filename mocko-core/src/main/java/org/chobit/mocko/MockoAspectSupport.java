package org.chobit.mocko;

import org.chobit.mocko.annotations.MockOf;
import org.chobit.mocko.annotations.MockoClient;
import org.chobit.mocko.model.ArgInfo;
import org.chobit.mocko.model.MethodMeta;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Mocko切面支持
 *
 * @author rui.zhang
 */
public class MockoAspectSupport {


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
    private MethodMeta parseMethodInfo(Object target, Method method, Object[] args) {

        String className = target.getClass().getCanonicalName();
        MockoClient mockoClient = target.getClass().getAnnotation(MockoClient.class);
        String classAlias = "";
        if (null != mockoClient) {
            classAlias = mockoClient.value();
        }

        String methodName = method.getName();
        MockOf mocko = method.getAnnotation(MockOf.class);
        String methodAlias = "";
        if (null != mocko) {
            methodAlias = mocko.value();
        }

        List<ArgInfo> argList = takeArgList(args);

        Class<?> returnType = method.getReturnType();


        MethodMeta methodMeta = new MethodMeta();
        methodMeta.setClassName(className);
        methodMeta.setClassAlias(classAlias);
        methodMeta.setMethodName(methodName);
        methodMeta.setMethodAlias(methodAlias);
        methodMeta.setArgs(argList);
        methodMeta.setReturnType(returnType);

        return methodMeta;
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
