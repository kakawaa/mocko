package org.chobit.mocko.server.controller;

import org.chobit.commons.model.TreeNode;
import org.chobit.mocko.server.biz.TypeBiz;
import org.chobit.mocko.server.config.response.ResponseWrapper;
import org.chobit.mocko.server.model.request.AppIdRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/classes")
public class TypeController {


    @Resource
    private TypeBiz typeBiz;


    @PostMapping("/all")
    public TreeNode<String> findClasses(@Validated @RequestBody AppIdRequest request) {
        return typeBiz.obtainTree(request.getAppId());
    }
}
