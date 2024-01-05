package org.chobit.mocko.simple;

import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.annotations.MockoClient;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Mocko方法适配器
 *
 * @author rui.zhang
 */
public class MockoMethodMatcher extends StaticMethodMatcher {


    private final boolean checkInherited;


    public MockoMethodMatcher(boolean checkInherited) {
        this.checkInherited = checkInherited;
    }


    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (matchesMethod(method)) {
            return true;
        }

        if (matchesClass(targetClass)) {
            return true;
        }

        // Proxy classes never have annotations on their redeclared methods.
        if (Proxy.isProxyClass(targetClass)) {
            return false;
        }

        // The method may be on an interface, so let's check on the target class as well.
        Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        return specificMethod != method && matchesMethod(specificMethod);
    }


    private boolean matchesMethod(Method method) {
        return (this.checkInherited ? AnnotatedElementUtils.hasAnnotation(method, Mocko.class) :
                method.isAnnotationPresent(Mocko.class));
    }


    private boolean matchesClass(Class<?> targetClass) {
        return (
                this.checkInherited ?
                        AnnotatedElementUtils.hasAnnotation(targetClass, MockoClient.class) || AnnotatedElementUtils.hasAnnotation(targetClass, Mocko.class)
                        : targetClass.isAnnotationPresent(MockoClient.class) || targetClass.isAnnotationPresent(Mocko.class)
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        ;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MockoMethodMatcher that = (MockoMethodMatcher) o;
        return checkInherited == that.checkInherited;
    }


    @Override
    public int hashCode() {
        return Objects.hash(checkInherited);
    }


    @Override
    public String toString() {
        return getClass().getName() + ": " + this.checkInherited;
    }
}
