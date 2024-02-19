package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.User;
import org.chobit.mocko.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户信息处理
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    /**
     * 根据登录信息查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User getByUserPwd(String username, String password) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username)
                .eq(User::getPassword, password);

        return this.getOne(lqw);
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

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickName(nickname);

        return this.getBaseMapper().insert(user);
    }

}
