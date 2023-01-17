package org.chobit.mocko;

import ch.qos.logback.core.net.server.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
        Assert.hasText(this.contextId, "contextId不能为空");
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


    <T> T getTarget() {
        MockoContext context = (null != beanFactory ? beanFactory.getBean(MockoContext.class) : applicationContext.getBean(MockoContext.class));

        Client client = context.getInstance(contextId, Client.class);


    }


    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
}
