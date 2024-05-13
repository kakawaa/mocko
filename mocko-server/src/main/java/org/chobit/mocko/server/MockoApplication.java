package org.chobit.mocko.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.autoconfigure.annotations.MockoClientScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Mocko服务中心启动类
 *
 * @author robin
 */
@MockoClientScan({
        "com.zhyea.mocko"
})
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
public class MockoApplication {


    public static void main(String[] args) {
        SpringApplication.run(MockoApplication.class, args);
    }


    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = JsonKit.mapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }


}
