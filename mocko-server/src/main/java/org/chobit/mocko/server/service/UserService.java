package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.UserEntity;
import org.chobit.mocko.server.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息处理
 *
 * @author robin
 */
@Slf4j
@Service
public class UserService {


    @Resource
    private UserMapper userMapper;


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
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 用户记录ID
     */
    public Integer addUser(String username, String password, String nickname) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickName(nickname);

        userMapper.add(user);

        return user.getId();
    }

}
