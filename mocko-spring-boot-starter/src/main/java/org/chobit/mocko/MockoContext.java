package org.chobit.mocko;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 用来维护mocko相关的Bean
 *
 * @author rui.zhang
 */
public class MockoContext extends NamedContextFactory<MockoClientSpecification> {


    public MockoContext() {
        super(MockoClientConfiguration.class, "mocko", "mocko.client.name");
    }


    @Nullable
    public <T> T getInstanceWithoutAncestors(String name, Class<T> type) {
        try {
            return BeanFactoryUtils.beanOfType(getContext(name), type);
        } catch (BeansException e) {
            return null;
        }
    }


    @Nullable
    public <T> Map<String, T> getInstancesWithoutAncestors(String name, Class<T> type) {
        return getContext(name).getBeansOfType(type);
    }


    public <T> T getInstance(String contextName, String beanName, Class<T> type) {
        return getContext(contextName).getBean(beanName, type);
    }
}
