package org.chobit.mocko.server.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chobit.mocko.core.annotations.Mocko;
import org.chobit.mocko.server.service.IFooService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试业务类
 *
 * @author robin
 */
@Mocko
@Component
public class MyBiz {


    @Resource
    private IFooService fooService;


    public String ping() {
        return "pong";
    }


    public Map<String, Integer> mapInfo() {
        Map<String, Integer> result = new HashMap<>(2);
        result.put("A", 1);
        result.put("B", 2);
        return result;
    }


    public Staff staff() {
        return new Staff(1, "Tom", 18);
    }


    public String foo(String foo) {
        return fooService.foo(foo);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Staff {

        private Integer id;

        private String name;

        private Integer age;

    }
}
