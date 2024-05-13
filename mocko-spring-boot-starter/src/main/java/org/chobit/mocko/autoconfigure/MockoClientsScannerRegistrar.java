package org.chobit.mocko.autoconfigure;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * MockoClient实例注册类
 * <p>
 * 配合@MockoClientsScanner注解使用
 *
 * @author robin
 */
public class MockoClientsScannerRegistrar extends MockoClientsRegistrar
        implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {


        AnnotationAttributes mockoClientScanAttr = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(MockoClientsConfiguration.class.getName()));
        if (mockoClientScanAttr != null) {
            registerBeanDefinitions(importingClassMetadata, mockoClientScanAttr, registry,
                    generateBaseBeanName(importingClassMetadata, 0));
        }

        String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());

        super.registerMockoClients(basePackage, registry);
    }
}
