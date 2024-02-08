package org.chobit.mocko;


import org.chobit.mocko.annotations.MockOf;
import org.chobit.mocko.client.*;
import org.chobit.mocko.simple.MockoInterceptor;
import org.chobit.mocko.simple.MockoPointcutSourceAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

/**
 * 自启动配置
 *
 * @author rui.zhang
 */
@Import(MockoClientsRegistrar.class)
@Configuration( proxyBeanMethods = false)
@ConditionalOnClass({MockOf.class})
@EnableConfigurationProperties(MockoProperties.class)
public class MockoAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public MockoContext mockoContext() {
        MockoContext context = new MockoContext();
        return context;
    }


    @Bean
    @ConditionalOnMissingBean
    public Targeter mockoTargeter() {
        return new Targeter.DefaultTargeter();
    }


    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Mocko.Builder mockoBuilder() {
        return Mocko.builder();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MockoInterceptor mockoInterceptor() {
        return new MockoInterceptor();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MockoPointcutSourceAdvisor mockoPointAdvisor(MockoInterceptor quickLogInterceptor) {
        MockoPointcutSourceAdvisor advisor = new MockoPointcutSourceAdvisor();
        advisor.setAdvice(quickLogInterceptor);
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return advisor;
    }
}
