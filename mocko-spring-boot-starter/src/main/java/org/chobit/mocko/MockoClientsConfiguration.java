package org.chobit.mocko;

import org.chobit.mocko.client.MockoClientsRegistrar;
import org.chobit.mocko.client.MockoContext;
import org.chobit.mocko.client.Targeter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author rui.zhang
 */
@EnableConfigurationProperties(MockoProperties.class)
@ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
@Configuration(proxyBeanMethods = false)
public class MockoClientsConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public MockoContext mockoContext() {
        return new MockoContext();
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


    @Configuration(proxyBeanMethods = false)
    @Import(MockoClientsRegistrar.class)
    @ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
    public static class MockoRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
            System.out.println(1);
            //logger.debug(
            //      "Not found configuration for registering mapper bean using @MapperScan, MapperFactoryBean and MapperScannerConfigurer.");
        }
    }
}
