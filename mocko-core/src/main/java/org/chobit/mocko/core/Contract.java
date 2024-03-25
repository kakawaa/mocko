package org.chobit.mocko.core;

import org.chobit.mocko.core.annotations.Mocko;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.chobit.commons.utils.ReflectKit.isDefault;
import static org.chobit.commons.utils.ReflectKit.isStatic;

/**
 * 解析类，获取MethodMetadata
 *
 * @author rui.zhang
 */
public interface Contract {


    /**
     * 解析类中的方法元数据
     *
     * @param targetType 目标类
     * @return 方法元数据
     */
    List<MethodMetadata> parseAndValidMetadata(Class<?> targetType);


    abstract class BaseContract implements Contract {


        /**
         * 解析类中的方法元数据
         *
         * @param targetType 目标类
         * @return 方法元数据
         */
        @Override
        public List<MethodMetadata> parseAndValidMetadata(Class<?> targetType) {

            final Map<String, MethodMetadata> metadataMap = new LinkedHashMap<>(8);

            Method[] methods = targetType.getMethods();
            for (final Method method : methods) {
                // 没有@Mocko注解的方法，静态方法，Object的方法，default方法都不会被处理
                if (!method.isAnnotationPresent(Mocko.class)) {
                    continue;
                }
                if (method.getDeclaringClass() == Object.class) {
                    continue;
                }
                if (isStatic(method)) {
                    continue;
                }
                if (isDefault(method)) {
                    continue;
                }

                final MethodMetadata metadata = parseAndValidMetadata(targetType, method);
                metadataMap.putIfAbsent(metadata.configKey(), metadata);
            }

            return new ArrayList<>(metadataMap.values());
        }


        /**
         * 解析方法元数据
         *
         * @param targetType 方法所属类
         * @param method     方法
         * @return 方法对应的元数据
         */
        private MethodMetadata parseAndValidMetadata(Class<?> targetType, Method method) {
            final MethodMetadata metadata = new MethodMetadata();

            metadata.targetType(targetType);
            metadata.method(method);
            metadata.returnType(method.getGenericReturnType());
            metadata.configKey(configKey(targetType, method));

            return metadata;
        }


        /**
         * 获取方法对应的configKey
         *
         * @param targetType 方法所属类
         * @param method     方法
         * @return 方法的configKey
         */
        private String configKey(Class<?> targetType, Method method) {
            StringBuilder builder = new StringBuilder();

            builder.append(targetType.getSimpleName())
                    .append('#')
                    .append(method.getName())
                    .append('(');

            for (Class<?> clazz : method.getParameterTypes()) {
                builder.append(clazz.getSimpleName())
                        .append(',');
            }

            if (method.getParameterTypes().length > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }

            builder.append(')');

            return builder.toString();
        }
    }


    class Default extends BaseContract {

    }

}


