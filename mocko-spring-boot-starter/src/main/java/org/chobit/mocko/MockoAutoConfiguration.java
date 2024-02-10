package org.chobit.mocko;


import org.chobit.mocko.annotations.MockOf;
import org.chobit.mocko.client.MockoProperties;
import org.chobit.mocko.simple.MockoInterceptor;
import org.chobit.mocko.simple.MockoPointcutSourceAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;

/**
 * 自启动配置
 *
 * @author rui.zhang
 */
@Configuration( proxyBeanMethods = false)
@ConditionalOnClass({MockOf.class})
@EnableConfigurationProperties(MockoProperties.class)
public class MockoAutoConfiguration {



    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MockoPointcutSourceAdvisor mockoPointAdvisor() {
        MockoPointcutSourceAdvisor advisor = new MockoPointcutSourceAdvisor();
        advisor.setAdvice( new MockoInterceptor());
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return advisor;
    }
}
