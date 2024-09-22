package org.chobit.mocko.autoconfigure;

import org.chobit.mocko.core.MockoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author robin
 */
@EnableConfigurationProperties(MockoProperties.class)
@ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
@Configuration(proxyBeanMethods = false)
public class MockoClientsConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(MockoClientsConfiguration.class);


    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public MockoProvider.Builder mockoBuilder(MockoProperties properties) {
        MockoProvider.Builder builder = MockoProvider.builder();
        builder.appId(properties.getAppId());
        builder.mockUrl(properties.getMockUrl());
        return builder;
    }


    @Configuration(proxyBeanMethods = false)
    @Import(MockoClientsAutoConfiguredRegistrar.class)
    @ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
    public static class MockoRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
            logger.debug("Not found configuration for registering mocko bean using MockoClientFactoryBean.");
        }
    }
}
