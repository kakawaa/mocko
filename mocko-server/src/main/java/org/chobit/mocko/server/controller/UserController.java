package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.biz.UserBiz;
import org.chobit.mocko.server.config.response.ResponseWrapper;
import org.chobit.mocko.server.model.request.UserAddRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户信息相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/user")
public class UserController {


    @Resource
    private UserBiz userBiz;


    @PostMapping("/add")
    public Integer add(@Valid @RequestBody UserAddRequest request) {
        return userBiz.add(request);
    }


}
