package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.model.request.MethodRuleAddRequest;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方法规则相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/method/rule")
public class MethodRuleController {


    private Metho


    /**
     * 新增方法规则
     *
     * @param request 方法规则新增请求
     * @return 是否新增成功
     */
    @PostMapping("/add")
    public boolean add(@RequestBody @Validated MethodRuleAddRequest request) {

    }


}
