package org.chobit.mocko.tools;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射相关工具类
 *
 * @author rui.zhang
 */
public final class ReflectionUtils {


    /**
     * synthetic方法修饰符int值
     */
    private static final int SYNTHETIC = 0x00001000;


    /**
     * 判断是否是default方法
     *
     * @param method 方法名
     * @return true 是default方法， false 不也是default方法
     */
    public static boolean isDefault(Method method) {

        Class<?> clazz = method.getDeclaringClass();

        // 只有接口中有default方法
        if (!clazz.isInterface()) {
            return false;
        }

        // Default methods are public non-abstract, non-synthetic, and non-static instance methods
        // declared in an interface.
        // method.isDefault() is not sufficient for our usage as it does not check
        // for synthetic methods. As a result, it picks up overridden methods as well as actual default
        // methods.
        return (
                (method.getModifiers()
                        & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC | SYNTHETIC))
                        == Modifier.PUBLIC
        );
    }


    /**
     * 判断是否是静态方法
     *
     * @param method 待分析方法
     * @return true 是静态方法， false 不是静态方法
     */
    public static boolean isStatic(Method method) {
        return (method.getModifiers() & Modifier.STATIC) != 0;
    }


    private ReflectionUtils() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed!");
    }
}
