package org.chobit.mocko.biz;

import org.chobit.commons.codec.MD5;
import org.chobit.mocko.helper.AuthContext;
import org.chobit.mocko.model.entity.User;
import org.chobit.mocko.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户相关业务处理
 *
 * @author rui.zhang
 */
@Component
public class UserBiz {


    @Resource
    private UserService userService;

    @Value("${mocko.server.salt}")
    private String pwdSalt;


    /**
     * 用户登录检查
     *
     * @param username 用户名
     * @param password 密码
     * @return true 登陆成功， false 登录失败
     */
    public boolean queryAndCheck(String username, String password) {
        password = MD5.encode(password + pwdSalt);
        User user = userService.getByUserPwd(username, password);

        if (null == user) {
            return false;
        }

        AuthContext.addUsername(username);
        AuthContext.addUser(user);

        return true;
    }


}
