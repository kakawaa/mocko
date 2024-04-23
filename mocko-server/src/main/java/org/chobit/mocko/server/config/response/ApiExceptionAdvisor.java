package org.chobit.mocko.server.config.response;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.enums.CommonStatusCode;
import org.chobit.commons.model.response.Result;
import org.chobit.mocko.server.except.MockoServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author robin
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvisor {


    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = MockoServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object mockoExceptionHandler(MockoServerException e) {
        logger.warn("Mock返回值错误", e);
        return null;
    }


    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> paramExceptionHandler(MissingServletRequestParameterException e) {

        Result<?> r = new Result<>(CommonStatusCode.FAIL);

        String parameterName = e.getParameterName();
        r.setMsg(e.getMessage());

        logger.warn("请求参数错误, param:{}", parameterName, e);
        return r;
    }


    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> exceptionHandler(Exception e) {

        Result<?> r = new Result<>(CommonStatusCode.FAIL);
        r.setMsg("内部异常");

        logger.warn("发现内部异常：{}", r.getMsg(), e);
        return r;
    }

}