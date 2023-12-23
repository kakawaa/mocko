package org.chobit.mocko.web;

import org.chobit.mocko.biz.MyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Hello World!
 *
 * @author rui.zhang
 */
@RestController
@RequestMapping("/mocko")
public class HelloController {


    @Autowired
    private MyBiz myBiz;


    @GetMapping("/ping")
    public String ping() {
        return myBiz.ping();
    }

}
