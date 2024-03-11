package org.chobit.mocko.config.intercept;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.helper.AuthContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验拦截器
 *
 * @author rui.zhang
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (null == AuthContext.getUser()) {
            logger.error("用户需要登录后才可以访问当前服务");
            return false;
        }

        return true;
    }


}
