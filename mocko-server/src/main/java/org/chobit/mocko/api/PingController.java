package org.chobit.mocko.api;

import org.chobit.mocko.biz.MyBiz;
import org.chobit.mocko.spring.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Hello World!
 *
 * @author rui.zhang
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class PingController {


    @Autowired
    private MyBiz myBiz;


    @GetMapping("/ping")
    public String ping() {
        return myBiz.ping();
    }

}
