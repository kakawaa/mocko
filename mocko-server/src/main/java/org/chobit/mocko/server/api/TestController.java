package org.chobit.mocko.server.api;

import com.zhyea.mocko.server.TestService;
import org.chobit.mocko.server.config.response.ResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 测试接口
 *
 * @author robin
 */
@ResponseWrapper
@Controller
@RequestMapping("/api/test")
public class TestController {


    @Resource
    private TestService testService;


    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
