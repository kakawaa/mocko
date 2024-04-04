package org.chobit.mocko.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * MockoClient实例注册类
 * <p>
 * 配合@MockoClientsScanner注解使用
 *
 * @author rui.zhang
 */
public class MockoClientsScannerRegistrar extends MockoClientsRegistrar
        implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());

        super.registerMockoClients(basePackage, registry);
    }
}
