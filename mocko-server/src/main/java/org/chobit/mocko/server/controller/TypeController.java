package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.biz.TypeBiz;
import org.chobit.mocko.server.model.request.AppIdRequest;
import org.chobit.mocko.server.model.request.ClassIdRequest;
import org.chobit.mocko.server.model.vo.ClassTreeNode;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


    @PostMapping("/tree")
    public List<ClassTreeNode> findClasses(@Validated @RequestBody AppIdRequest request) {
        return typeBiz.obtainAppClassTree(request.getAppId());
    }


    @PostMapping("/methods")
    public List<ClassTreeNode> findMethods(@Validated @RequestBody ClassIdRequest request) {
        return typeBiz.findMethods(request.getClassId());
    }
}
