package org.chobit.mocko.client;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来维护mocko相关的Bean
 *
 * @author rui.zhang
 */
public class MockoContext implements DisposableBean, ApplicationContextAware {


    private final Map<String, AnnotationConfigApplicationContext> contexts = new ConcurrentHashMap<>();


    private ApplicationContext parent;

    public MockoContext() {
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


    public <T> T getInstance(String contextName, Class<T> type) {
        return getContext(contextName).getBean(type.getName(), type);
    }


    private AnnotationConfigApplicationContext getContext(String name) {
        if (!this.contexts.containsKey(name)) {
            synchronized (this.contexts) {
                if (!this.contexts.containsKey(name)) {
                    this.contexts.put(name, null);
                }
            }
        }
        return this.contexts.get(name);
    }


    @Override
    public void destroy() throws Exception {
        Collection<AnnotationConfigApplicationContext> values = this.contexts.values();
        for (AnnotationConfigApplicationContext context : values) {
            context.close();
        }
        this.contexts.clear();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.parent = context;
    }
}
