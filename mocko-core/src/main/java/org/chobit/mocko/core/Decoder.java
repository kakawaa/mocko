package org.chobit.mocko.core;

import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 返回值解析器
 *
 * @author robin
 */
public interface Decoder {


    /**
     * 执行解析
     *
     * @param response 请求响应
     * @param type     返回值类型
     * @return 返回值对象
     * @throws IOException 异常
     */
    Object decode(final Response response, Type type) throws IOException;

}
