package org.chobit.mocko.server.config.intercept;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.tools.AuthContext;
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

        String path = request.getRequestURI();
        String url = request.getRequestURL().toString();

        if (null == AuthContext.getUser()) {
            logger.error("用户需要登录后才可以访问当前服务, path:{}", path);
            return false;
        }

        return true;
    }


}
