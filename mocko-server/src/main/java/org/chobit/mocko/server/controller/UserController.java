package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.model.request.UserAddRequest;
import org.chobit.mocko.server.service.UserService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


	private final UserService userService;


	@Autowired
	public UserController(UserService userBiz) {
		this.userService = userBiz;
	}


	@PostMapping("/add")
	public Integer add(@RequestBody @Valid UserAddRequest request) {
		return userService.addUser(request);
	}


}
