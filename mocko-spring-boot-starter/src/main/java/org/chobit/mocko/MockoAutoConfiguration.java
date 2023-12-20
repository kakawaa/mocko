package org.chobit.mocko;


import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.client.MockoProperties;
import org.chobit.mocko.simple.MockoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自启动配置
 *
 * @author rui.zhang
 */
@Configuration
@ConditionalOnClass({Mocko.class})
@EnableConfigurationProperties(MockoProperties.class)
public class MockoAutoConfiguration {


    @Bean
    public MockoInterceptor mockoInterceptor() {
        return new MockoInterceptor();
    }


}
