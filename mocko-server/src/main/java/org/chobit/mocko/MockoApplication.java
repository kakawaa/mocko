package org.chobit.mocko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * Mocko服务中心启动类
 *
 * @author rui.zhang
 */
@EnableCaching
@SpringBootApplication
public class MockoApplication {


    public static void main(String[] args) {
        SpringApplication.run(MockoApplication.class, args);
    }

}
