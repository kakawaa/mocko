package org.chobit.mocko.client;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 用来维护mocko相关的Bean
 *
 * @author rui.zhang
 */
public class MockoContext implements ApplicationContextAware {


    public MockoContext() {
    }

    private ApplicationContext parent;


    public <T> T getInstance(Class<T> clazz) {

        return parent.getBean(clazz);
    }


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        parent = context;
    }
}
