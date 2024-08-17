package org.chobit.mocko.server.config.intercept;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.ObjKit;
import org.chobit.mocko.server.except.MockoServerException;
import org.chobit.mocko.server.tools.AuthContext;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.utils.StrKit.isBlank;
import static org.chobit.mocko.server.constants.Constants.TOKEN_FLAG;
import static org.chobit.mocko.server.constants.ResponseCode.USER_AUTH_ERROR;

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
		token = String.valueOf(token).replace("Bear ", EMPTY);


        if (isBlank(token) || ObjKit.nonEquals(token, AuthContext.getToken())) {
            logger.info("Mocko Request is blocked, url:{}, headers:{} ", url, request.getHeaderNames());
           throw new MockoServerException(USER_AUTH_ERROR);
        }

		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request,
	                       HttpServletResponse response,
	                       Object handler, @Nullable ModelAndView modelAndView) throws Exception {

	}
}
