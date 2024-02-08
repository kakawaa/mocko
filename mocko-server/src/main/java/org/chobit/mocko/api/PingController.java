package org.chobit.mocko.api;

import org.chobit.mocko.biz.MyBiz;
import org.chobit.mocko.config.response.ResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Hello World!
 *
 * @author rui.zhang
 */
@ResponseWrapper
@Controller
@RequestMapping("/api")
public class PingController {


    @Resource
    private MyBiz myBiz;


    @GetMapping("/ping")
    public String ping() {
        return myBiz.ping();
    }


    @GetMapping("/pong")
    public Map<String, Integer> pong() {
        return myBiz.pong();
    }


    @GetMapping("/foo")
    public String foo(@RequestParam("n") String name) {
        return myBiz.foo(name);
    }


    @GetMapping("/bar")
    public String bar(@RequestParam("n") String name) {
        return myBiz.bar(name);
    }

}
