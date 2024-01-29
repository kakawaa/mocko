package org.chobit.mocko.client;

import org.chobit.mocko.MockoClientsConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 用来维护mocko相关的Bean
 *
 * @author rui.zhang
 */
public class MockoContext extends NamedContextFactory {


    public MockoContext() {
        super(MockoClientsConfiguration.class, "feign", "feign.client.name");
    }


    @Nullable
    public <T> T getInstanceWithoutAncestors(String name, Class<T> type) {
        try {
            return BeanFactoryUtils.beanOfType(getContext(name), type);
        }
        catch (BeansException ex) {
            return null;
        }
    }

    @Nullable
    public <T> Map<String, T> getInstancesWithoutAncestors(String name, Class<T> type) {
        return getContext(name).getBeansOfType(type);
    }

}
