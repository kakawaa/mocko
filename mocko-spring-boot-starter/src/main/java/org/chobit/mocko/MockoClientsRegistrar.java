package org.chobit.mocko;

import org.chobit.mocko.annotations.MockoClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 执行MockoClient相关类注入
 *
 * @author rui.zhang
 */
class MockoClientsRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {


    /**
     * 资源加载
     */
    private ResourceLoader resourceLoader;

    /**
     * 环境上下文
     */
    private Environment environment;


    MockoClientsRegistrar() {
    }


    /**
     * 将MockoClient相关实例注入到容器
     */
    public void registerMockoClients(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = this.getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MockoClient.class));

        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>(8);

        String basePackage = ClassUtils.getPackageName(metadata.getClassName());
        candidateComponents.addAll(scanner.findCandidateComponents(basePackage));

        for (BeanDefinition comp : candidateComponents) {
            if (!(comp instanceof AnnotatedBeanDefinition)) {
                continue;
            }
            AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) comp;
            AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();

            Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(MockoClient.class.getName());
            String clientName = "$moco." + comp.getBeanClassName();

        }
    }


    private void registerMockoClient(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();
        Class<?> clazz = ClassUtils.resolveClassName(className, null);
        ConfigurableBeanFactory beanFactory = registry instanceof ConfigurableBeanFactory
                ? (ConfigurableBeanFactory) registry : null;


    }


    private String getContextId(ConfigurableBeanFactory beanFactory, Map<String, Object> attributes) {
        return null;
    }


    /**
     * 获取组件扫描器
     *
     * @return 组件扫描器
     */
    ClassPathScanningCandidateComponentProvider getScanner() {
        // 不使用默认的过滤器，改用有指定注解的过滤器
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
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


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
