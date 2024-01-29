package org.chobit.mocko;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MockoClientsConfiguration {




    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

}
