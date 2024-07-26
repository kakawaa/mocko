package org.chobit.mocko.server.config.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.chobit.mocko.server.constants.Constants.TOKEN_FLAG;

/**
 * 权限校验拦截器
 *
 * @author robin
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getRequestURI();
        String url = request.getRequestURL().toString();

        logger.info("Mocko Request, path:{} , url:{} ", path, url);

        String token = request.getHeader(TOKEN_FLAG);

        // TODO 发现存在问题
//        if (isBlank(token) || ObjKit.nonEquals(token, AuthContext.getToken())) {
//            logger.info("Mocko Request is blocked, url:{}, headers:{} ", url, request.getHeaderNames());
//            return false;
//        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }
}
