package org.chobit.mocko.server.biz;

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


    public Map<String, Integer> pong() {
        Map<String, Integer> result = new HashMap<>(2);
        result.put("A", 1);
        result.put("B", 2);
        return result;
    }


    public String foo(String foo) {
        String r = fooService.bar(foo);
        return r;
    }
}
