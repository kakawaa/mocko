package org.chobit.mocko.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author rui.zhang
 */
public class MockoClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware, BeanFactoryAware {


    private static final Logger logger = LoggerFactory.getLogger(MockoClientFactoryBean.class);

    private Class<?> type;

    private String name;

    private String url;

    private String contextId;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;


    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.hasText(this.contextId, "contextId不能为空");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.beanFactory = applicationContext;
    }


    private <T> T get(MockoContext context, Class<T> type) {
        T instance = context.getInstance(contextId, type);
        if (null == instance) {
            throw new IllegalStateException("No bean found of type " + type + " for " + contextId);
        }
        return instance;
    }


    @Override
    public Object getObject() throws Exception {

        return getTarget();
    }


    <T> T getTarget() {
        MockoContext context = (null != beanFactory ? beanFactory.getBean(MockoContext.class) : applicationContext.getBean(MockoContext.class));


        return null;
    }


    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
}
