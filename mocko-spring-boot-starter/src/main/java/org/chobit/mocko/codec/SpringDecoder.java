package org.chobit.mocko.codec;

import org.apache.http.client.fluent.Response;
import org.chobit.mocko.exception.DecodeException;
import org.chobit.mocko.exception.MockoException;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * @author rui.zhang
 */
public class SpringDecoder implements Decoder {


    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, MockoException {
        return null;
    }



}
