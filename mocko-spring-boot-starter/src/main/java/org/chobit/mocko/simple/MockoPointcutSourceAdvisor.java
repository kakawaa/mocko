package org.chobit.mocko.simple;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Mocko切面定义
 *
 * @author rui.zhang
 */
public class MockoPointcutSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    private static final long serialVersionUID = 1353656757082963204L;


    private final MockoMethodMatcher matcher = new MockoMethodMatcher(true);


    private final Pointcut pointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return matcher.matches(method, targetClass);
        }
    };

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
