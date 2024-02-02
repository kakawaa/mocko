package org.chobit.mocko.client;

import org.chobit.mocko.Mocko;
import org.chobit.mocko.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author rui.zhang
 */
public class MockoClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {


    private static final Logger logger = LoggerFactory.getLogger(MockoClientFactoryBean.class);

    private Class<?> type;

    private String name;

    private String url;

    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.hasText(this.contextId, "contextId不能为空");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    private <T> T get(MockoContext context, Class<T> type) {
        T instance = context.getInstance(type);
        if (null == instance) {
            throw new IllegalStateException("No bean found of type " + type + " for " + type);
        }
        return instance;
    }


    @Override
    public Object getObject() throws Exception {

        return getTarget();
    }


    <T> T getTarget() {
        MockoContext context =  applicationContext.getBean(MockoContext.class);
        Targeter targeter = this.get(context, Targeter.class);

        Mocko.Builder mocko = this.get(context, Mocko.Builder.class);
        Target.DefaultTarget<T> target = new Target.DefaultTarget(this.type, this.name, this.url);

        return targeter.target(this, mocko, context, target);
    }


    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
}
