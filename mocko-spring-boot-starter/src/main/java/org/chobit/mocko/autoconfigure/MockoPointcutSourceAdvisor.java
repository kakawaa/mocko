package org.chobit.mocko.autoconfigure;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * Mocko切面定义
 *
 * @author rui.zhang
 */
public class MockoPointcutSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    private static final long serialVersionUID = 1353656757082963204L;


    private final MockoMethodMatcher matcher = new MockoMethodMatcher(true);


    private final Pointcut pointcut = new Pointcut() {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return matcher;
        }
    };

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
