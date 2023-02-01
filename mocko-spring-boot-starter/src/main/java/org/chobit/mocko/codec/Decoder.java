package org.chobit.mocko.codec;

import org.apache.http.client.fluent.Response;
import org.chobit.mocko.exception.DecodeException;
import org.chobit.mocko.exception.MockoException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author rui.zhang
 */
public interface Decoder {


    /**
     * 将响应信息解析为指定的类型
     *
     * @param response 请求响应
     * @param type     对象类型
     * @return 指定类型实例
     * @throws IOException     读写异常
     * @throws DecodeException 解码异常
     * @throws MockoException  其他异常
     */
    Object decode(Response response, Type type) throws IOException, DecodeException, MockoException;


}
