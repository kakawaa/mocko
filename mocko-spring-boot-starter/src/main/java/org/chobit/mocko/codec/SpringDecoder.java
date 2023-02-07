package org.chobit.mocko.codec;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.chobit.mocko.exception.DecodeException;
import org.chobit.mocko.exception.MockoException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.*;


/**
 * @author rui.zhang
 */
public class SpringDecoder implements Decoder {


    private final ObjectFactory<HttpMessageConverters> messageConverters;


    public SpringDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }


    @Override
    public Object decode(HttpResponse response, Type type) throws IOException, DecodeException, MockoException {

        if (type instanceof Class || type instanceof ParameterizedType || type instanceof WildcardType) {
            List<HttpMessageConverter<?>> converters = messageConverters.getObject().getConverters();
            HttpMessageConverterExtractor<?> extractor = new HttpMessageConverterExtractor<>(type, converters);

            return extractor.extractData(new HttpClientResponseAdapter(response));
        }

        throw new DecodeException(response.getStatusLine().getStatusCode(), "type is not an instance of Class or ParameterizedType: " + type);
    }


    private static final class HttpClientResponseAdapter implements ClientHttpResponse {


        private final HttpResponse response;

        public HttpClientResponseAdapter(HttpResponse response) {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.valueOf(response.getStatusLine().getStatusCode());
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return response.getStatusLine().getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            HttpStatus status = this.getStatusCode();
            return status.getReasonPhrase();
        }

        @Override
        public void close() {
            try {
                response.getEntity().getContent().close();
            } catch (IOException e) {
                // Ignore exception
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return response.getEntity().getContent();
        }

        @Override
        public HttpHeaders getHeaders() {
            Header[] headers = response.getAllHeaders();
            HttpHeaders httpHeaders = new HttpHeaders();
            for (Header h : headers) {
                List<String> value = new LinkedList<>();
                value.add(h.getValue());
                httpHeaders.put(h.getName(), value);
            }
            return httpHeaders;
        }
    }


}
