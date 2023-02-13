package org.chobit.mocko;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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





    abstract class BaseContract implements Contract{


        @Override
        public List<MethodMetadata> parseAndValidMetadata(Class<?> targetType) {


            final Map<String, MethodMetadata> result = new LinkedHashMap<>(8);

            Method[] methods = targetType.getMethods();
            for(final Method method :  methods){

            }

            return null;
        }
    }

}


