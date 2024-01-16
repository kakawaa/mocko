package org.chobit.mocko.controller;

import org.chobit.mocko.model.request.AppModifyRequest;
import org.chobit.mocko.service.AppService;
import org.chobit.mocko.spring.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private AppService appService;

    @PostMapping("/update")
    public Boolean update(@Validated @RequestBody AppModifyRequest request) {
        return appService.updateAppName(request.getAppId(), request.getAppName());
    }


}
