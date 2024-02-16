package org.chobit.mocko.controller;

import org.chobit.mocko.biz.UserBiz;
import org.chobit.mocko.config.response.ResponseWrapper;
import org.chobit.mocko.model.request.UserLoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author rui.zhang
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class HomeController {


    @Resource
    private UserBiz userBiz;


    @PostMapping("/login")
    public boolean login(@Valid @RequestBody UserLoginRequest request) {
        return userBiz.queryAndCheck(request.getUsername(), request.getPassword());
    }


}
