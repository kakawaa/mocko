package org.chobit.mocko.autoconfigure;


import org.chobit.mocko.autoconfigure.support.SpringDecoder;
import org.chobit.mocko.core.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
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
@EnableConfigurationProperties(MockoProperties.class)
@ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class MockoAutoConfiguration {


    @Value("${spring.application.name}")
    private String appId;


    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;


    @ConditionalOnMissingBean
    @Bean
    private Decoder mockoDecoder() {
        return new SpringDecoder(messageConverters);
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MockoPointcutSourceAdvisor mockoPointAdvisor(MockoProperties mockoProperties, Decoder decoder) {
        mockoProperties.setAppId(appId);
        MockoPointcutSourceAdvisor advisor = new MockoPointcutSourceAdvisor();
        advisor.setAdvice(new MockoInterceptor(mockoProperties, decoder));
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return advisor;
    }
}
