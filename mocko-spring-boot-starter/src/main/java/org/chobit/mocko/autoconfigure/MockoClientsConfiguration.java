package org.chobit.mocko.autoconfigure;

import org.chobit.mocko.autoconfigure.support.SpringDecoder;
import org.chobit.mocko.core.Decoder;
import org.chobit.mocko.core.MockoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
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


    private static final Logger logger = LoggerFactory.getLogger(MockoClientsConfiguration.class);



    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;



    @Bean
    @ConditionalOnMissingBean
    public Decoder mockoDecoder(){
        return new SpringDecoder(messageConverters);
    }



    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public MockoProvider.Builder mockoBuilder() {
        return MockoProvider.builder();
    }


    @Configuration(proxyBeanMethods = false)
    @Import(MockoClientsAutoConfiguredRegistrar.class)
    @ConditionalOnProperty(name = "mocko.enabled", matchIfMissing = true)
    public static class MockoRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
            logger.debug( "Not found configuration for registering mocko bean using MockoClientFactoryBean.");
        }
    }
}
