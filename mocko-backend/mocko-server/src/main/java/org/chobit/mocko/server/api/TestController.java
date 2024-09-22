package org.chobit.mocko.server.api;

import com.zhyea.mocko.server.TestService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/test")
public class TestController {


    @Resource
    private TestService testService;


    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
