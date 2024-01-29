package org.chobit.mocko;


import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.client.MockoClientSpecification;
import org.chobit.mocko.client.MockoClientsRegistrar;
import org.chobit.mocko.client.MockoContext;
import org.chobit.mocko.client.MockoProperties;
import org.chobit.mocko.simple.MockoInterceptor;
import org.chobit.mocko.simple.MockoPointcutSourceAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.List;

/**
 * 自启动配置
 *
 * @author rui.zhang
 */
@Import(MockoClientsRegistrar.class)
@Configuration
@ConditionalOnClass({Mocko.class})
@EnableConfigurationProperties(MockoProperties.class)
public class MockoAutoConfiguration {


    @Autowired(required = false)
    private List<MockoClientSpecification> configurations = new ArrayList<>(8);


    @Bean
    public MockoContext mockoContext(){
        MockoContext context = new MockoContext();
        return context;
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


    /*@Autowired
    public void setConfigurations(List<MockoClientSpecification> configurations) {
        this.configurations = configurations;
    }*/
}
