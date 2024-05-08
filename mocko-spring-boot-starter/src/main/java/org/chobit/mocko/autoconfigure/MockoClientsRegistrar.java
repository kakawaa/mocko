package org.chobit.mocko.autoconfigure;

import org.chobit.mocko.core.annotations.MockoClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * MockoClient实例注入抽象类
 *
 * @author robin
 */
public abstract class MockoClientsRegistrar {


    /**
     * 将MockoClient相关实例注入到容器
     */
    protected void registerMockoClients(Collection<String> basePackages, BeanDefinitionRegistry registry) {
        basePackages.forEach(e -> this.registerMockoClients(e, registry));
    }

    /**
     * 将MockoClient相关实例注入到容器
     */
    protected void registerMockoClients(String basePackage, BeanDefinitionRegistry registry) {

        ClassPathScanningCandidateComponentProvider scanner = this.getScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(MockoClient.class));

        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>(8);

        candidateComponents.addAll(scanner.findCandidateComponents(basePackage));

        for (BeanDefinition comp : candidateComponents) {
            if (!(comp instanceof AnnotatedBeanDefinition)) {
                continue;
            }
            AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) comp;
            AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();

            Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(MockoClient.class.getName());

            this.registerMockoClient(registry, annotationMetadata, attributes);
        }
    }


    private void registerMockoClient(BeanDefinitionRegistry registry,
                                     AnnotationMetadata metadata,
                                     Map<String, Object> attributes) {

        final String className = metadata.getClassName();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MockoClientFactoryBean.class);
        builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        builder.addPropertyValue("name", className);
        builder.addPropertyValue("type", className);
        builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        String alias = className + "MockoClient";



        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.setAttribute(FactoryBean.OBJECT_TYPE_ATTRIBUTE, className);

        // has a default, won't be null
        boolean primary = (Boolean) attributes.get("primary");

        beanDefinition.setPrimary(primary);

        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);


        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className, new String[]{alias});
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }


    /**
     * 获取组件扫描器
     *
     * @return 组件扫描器
     */
    private ClassPathScanningCandidateComponentProvider getScanner() {
        // 不使用默认的过滤器，改用有指定注解的过滤器
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }
}
