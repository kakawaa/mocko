package org.chobit.mocko.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * 执行MockoClient相关类注入
 *
 * @author rui.zhang
 */
public class MockoClientsAutoConfiguredRegistrar extends MockoClientsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {


    private static final Logger logger = LoggerFactory.getLogger(MockoClientsAutoConfiguredRegistrar.class);


    @Nullable
    private BeanFactory beanFactory;


    public MockoClientsAutoConfiguredRegistrar() {
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {

        List<String> packages = new LinkedList<>();
        if (null != this.beanFactory) {
            packages = AutoConfigurationPackages.get(this.beanFactory);
        }

        if (logger.isDebugEnabled()) {
            packages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
        }

        registerMockoClients(packages, registry);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
