package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.biz.UserBiz;
import org.chobit.mocko.server.model.request.UserLoginRequest;
import org.chobit.mocko.server.tools.AuthContext;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class HomeController {


    @Resource
    private UserBiz userBiz;


    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginRequest request) {
        return userBiz.checkPassword(request.getUsername(), request.getPassword());
    }


    @PostMapping("/logout")
    public boolean logout() {
        AuthContext.clear();
        return true;
    }


}
