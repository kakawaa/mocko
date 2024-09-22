package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.UserEntity;
import org.chobit.mocko.server.model.request.UserAddRequest;
import org.chobit.mocko.server.service.mapper.UserMapper;
import org.chobit.mocko.server.tools.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.chobit.mocko.server.constants.ResponseCode.CONFIRM_PASSWORD_NOT_MATCH;

/**
 * 用户信息处理
 *
 * @author robin
 */
@Slf4j
@Service
public class UserService {


	private final UserMapper userMapper;


	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	/**
	 * 根据登录信息查询用户
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserEntity getByUserPwd(String username, String password) {
		return userMapper.getByUserAndPassword(username, password);
	}


	/**
	 * 新增用户信息
	 *
	 * @param req 用户信息
	 * @return 用户记录ID
	 */
	public Integer addUser(UserAddRequest req) {

		Args.checkEquals(req.getPassword(), req.getRePassword(), CONFIRM_PASSWORD_NOT_MATCH);

		UserEntity user = new UserEntity();
		user.setUsername(req.getUsername());
		user.setPassword(req.getPassword());
		user.setNickName(req.getNickname());

		userMapper.add(user);

		return user.getId();
	}

}
