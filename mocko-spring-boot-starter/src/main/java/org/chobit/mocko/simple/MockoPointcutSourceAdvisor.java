package org.chobit.mocko.simple;

import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.annotations.MockoClient;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * Mocko切面定义
 *
 * @author rui.zhang
 */
public class MockoPointcutSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    private static final long serialVersionUID = 1353656757082963204L;


    private final Pointcut pointcut = new AnnotationMatchingPointcut(MockoClient.class, Mocko.class, true);

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
