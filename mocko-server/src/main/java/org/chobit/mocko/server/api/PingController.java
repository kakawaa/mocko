package org.chobit.mocko.server.api;

import org.chobit.mocko.server.biz.MyBiz;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Hello World!
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/api")
public class PingController {


    @Resource
    private MyBiz myBiz;


    @GetMapping("/ping")
    public String ping() {
        return myBiz.ping();
    }


    @GetMapping("/map")
    public Map<String, Integer> newMap() {
        return myBiz.mapInfo();
    }


    @GetMapping("/staff")
    public MyBiz.Staff newStaff() {
        return myBiz.staff();
    }


    @GetMapping("/foo")
    public String foo(@RequestParam("n") String name) {
        return myBiz.foo(name);
    }

}
