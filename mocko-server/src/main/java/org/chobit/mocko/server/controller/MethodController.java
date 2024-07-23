package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.biz.MethodBiz;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodIdRequest;
import org.chobit.mocko.server.model.request.MethodResponseModifyRequest;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方法相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/method")
public class MethodController {


    @Resource
    private MethodBiz methodBiz;


    /**
     * 获取方法信息
     *
     * @param request 查询请求
     * @return 方法信息
     */
    @PostMapping("/get")
    public MethodEntity getByMethodId(@RequestBody @Validated MethodIdRequest request) {
        return methodBiz.getByMethodId(request.getMethodId());
    }


    /**
     * 更新方法返回值
     *
     * @param request 更新请求
     * @return 是否更新成功
     */
    @PostMapping("/change-response")
    public boolean changeResponse(@RequestBody @Validated MethodResponseModifyRequest request) {
        return methodBiz.changeMethodResponse(request);
    }

}
