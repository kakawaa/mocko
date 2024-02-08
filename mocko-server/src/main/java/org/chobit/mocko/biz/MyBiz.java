package org.chobit.mocko.biz;

import org.chobit.mocko.service.IBarService;
import org.chobit.mocko.service.IFooService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试业务类
 *
 * @author robin
 */
@Component
public class MyBiz {


    @Resource
    private IFooService fooService;

    @Resource
    private IBarService barService;


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
        return fooService.bar(foo);
    }


    public String bar(String foo) {
        return barService.foo(foo);
    }
}
