package org.chobit.mocko.autoconfigure;

import org.chobit.mocko.autoconfigure.annotations.MockoClientScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

        Collection<String> basePackages = this.parseBasePackages(importingClassMetadata);

        super.registerMockoClients(basePackages, registry);
    }


    /**
     * 调整MockoClientScan的包解析
     *
     * @param importingClassMetadata 导入类的元数据
     * @return basePackage集合
     */
    private Collection<String> parseBasePackages(AnnotationMetadata importingClassMetadata) {

        AnnotationAttributes attr = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(MockoClientScan.class.getName()));

        Set<String> result = new HashSet<>(8);
        if (attr != null) {
            String[] basePackages = attr.getStringArray("value");
            result.addAll(Arrays.asList(basePackages));
        }

        String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        result.add(basePackage);

        return result;
    }
}
