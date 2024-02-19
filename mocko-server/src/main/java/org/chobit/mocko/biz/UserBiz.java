package org.chobit.mocko.biz;

import org.chobit.commons.codec.MD5;
import org.chobit.mocko.helper.Args;
import org.chobit.mocko.helper.AuthContext;
import org.chobit.mocko.model.entity.User;
import org.chobit.mocko.model.request.UserAddRequest;
import org.chobit.mocko.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.chobit.mocko.constants.ResponseCode.CONFIRM_PASSWORD_NOT_MATCH;

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


    /**
     * 新增用户信息
     *
     * @param req 新增用户请求
     * @return 新增记录ID
     */
    public Integer add(UserAddRequest req) {

        Args.checkEquals(req.getPassword(), req.getRePassword(), CONFIRM_PASSWORD_NOT_MATCH);

        return userService.addUser(req.getUsername(), req.getPassword(), req.getNickname());
    }

}
