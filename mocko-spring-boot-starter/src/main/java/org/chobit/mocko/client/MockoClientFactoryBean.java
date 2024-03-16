package org.chobit.mocko.client;

import org.chobit.mocko.MockoProvider;
import org.chobit.mocko.Target;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * @author rui.zhang
 */
public class MockoClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {


    private Class<?> type;

    private String name;

    private String url;

    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
    }


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }


    @Override
    public Object getObject() throws Exception {
        return getTarget();
    }


    <T> T getTarget() {
        MockoProvider.Builder mocko = applicationContext.getBean(MockoProvider.Builder.class);
        Target.DefaultTarget<T> target = new Target.DefaultTarget(this.type, this.name, this.url);

        return mocko.build().newInstance(target);
    }


    @Override
    public Class<?> getObjectType() {
        return this.type;
    }


    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MockoClientFactoryBean that = (MockoClientFactoryBean) o;
        return Objects.equals(type, that.type)
                && Objects.equals(name, that.name)
                && Objects.equals(url, that.url)
                && Objects.equals(applicationContext, that.applicationContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, url, applicationContext);
    }
}
