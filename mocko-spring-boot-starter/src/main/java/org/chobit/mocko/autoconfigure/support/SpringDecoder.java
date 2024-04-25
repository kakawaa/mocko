package org.chobit.mocko.autoconfigure.support;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.chobit.mocko.core.Decoder;
import org.chobit.mocko.core.contants.ResponseCode;
import org.chobit.mocko.core.exception.MockoException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpMessageConverterExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


/**
 * 默认的Spring反序列化实现
 *
 * @author robin
 */
public class SpringDecoder implements Decoder {


    private final ObjectFactory<HttpMessageConverters> messageConverters;


    public SpringDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Nullable
    @Override
    public Object decode(final Response response, Type type)
            throws IOException {
        HttpMessageConverterExtractor<?> extractor = new HttpMessageConverterExtractor<>(
                type, this.messageConverters.getObject().getConverters());

        try (ClientHttpResponse resp = new MockoResponseAdapter(response)) {
            if (HttpStatus.OK != resp.getStatusCode()) {
                throw new MockoException(ResponseCode.REQUEST_MOCKO_SERVER_ERROR);
            }
            return extractor.extractData(resp);
        }
    }


    /**
     * MockoHttp请求适配器
     */
    private static final class MockoResponseAdapter implements ClientHttpResponse {

        private final HttpResponse response;

        private MockoResponseAdapter(Response response) throws IOException {
            this.response = response.returnResponse();
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.valueOf(this.response.getStatusLine().getStatusCode());
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getStatusLine().getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusLine().toString();
        }

        @Override
        public void close() {
            //nothing to do
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.response.getEntity().getContent();
        }

        @Override
        public HttpHeaders getHeaders() {
            Header[] headers = this.response.getAllHeaders();

            HttpHeaders result = new HttpHeaders();

            for (Header header : headers) {
                result.add(header.getName(), header.getValue());
            }

            return result;
        }
    }

}
