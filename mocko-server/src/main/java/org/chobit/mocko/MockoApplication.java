package org.chobit.mocko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Mocko服务中心启动类
 *
 * @author rui.zhang
 */
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
public class MockoApplication {


    public static void main(String[] args) {
        SpringApplication.run(MockoApplication.class, args);
    }

}
