package org.chobit.mocko;


import org.chobit.mocko.annotations.Mocko;
import org.chobit.mocko.client.MockoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author rui.zhang
 */
@Configuration
@ConditionalOnClass({Mocko.class})
@EnableConfigurationProperties(MockoProperties.class)
public class MockoAutoConfiguration {

}
