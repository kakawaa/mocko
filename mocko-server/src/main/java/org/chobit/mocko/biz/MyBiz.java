package org.chobit.mocko.biz;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试业务类
 *
 * @author robin
 */
@Component
public class MyBiz {


    public String ping() {
        return "pong";
    }


    public Map<String, Integer> pong() {
        Map<String, Integer> result = new HashMap<>(2);
        result.put("A", 1);
        result.put("B", 2);
        return result;
    }
}
