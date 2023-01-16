package org.chobit.mocko;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @author rui.zhang
 */
public class MockoClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware, BeanFactoryAware {


    private Class<?> type;

    private String name;

    private String url;

    private String componentId;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.componentId, "componentId不能为空");
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

    @Override
    public Object getObject() throws Exception {

        return null;
    }


    <T> T getTarget(){

    }



    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
}
