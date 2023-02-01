package org.chobit.mocko.codec;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.chobit.mocko.exception.DecodeException;
import org.chobit.mocko.exception.MockoException;

import java.io.IOException;
import java.lang.reflect.Type;

import static java.lang.String.format;


/**
 * @author rui.zhang
 */
public class StringDecoder implements Decoder {


    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, MockoException {
        HttpResponse r = response.returnResponse();
        int status = r.getStatusLine().getStatusCode();
        if (String.class.equals(type)) {
            return EntityUtils.toString(r.getEntity());
        }
        throw new DecodeException(status, format("不支持解析%s类型", type));
    }


}
