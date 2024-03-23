package org.chobit.mocko.controller;

import org.chobit.mocko.biz.AppBiz;
import org.chobit.mocko.config.response.ResponseWrapper;
import org.chobit.mocko.model.entity.AppEntity;
import org.chobit.mocko.model.request.AppIdRequest;
import org.chobit.mocko.model.request.AppModifyRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/app")
public class AppController {


    @Resource
    private AppBiz appBiz;


    @PostMapping("/update")
    public boolean update(@Validated @RequestBody AppModifyRequest request) {
        return appBiz.update(request);
    }


    @GetMapping("/list")
    public List<AppEntity> findApps() {
        return appBiz.findAll();
    }


    @PostMapping("/get")
    public AppEntity getApp(@Validated @RequestBody AppIdRequest request) {
        return appBiz.getApp(request.getAppId());
    }

}
