package org.chobit.mocko.server.controller;

import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.request.AppIdRequest;
import org.chobit.mocko.server.model.request.AppModifyRequest;
import org.chobit.mocko.server.service.AppService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


	private final AppService appService;


	@Autowired
	public AppController(AppService appService) {
		this.appService = appService;
	}

	@PostMapping("/update")
	public boolean update(@Validated @RequestBody AppModifyRequest request) {
		return appService.modifyAppName(request.getAppId(), request.getAppName());
	}


	@GetMapping("/list")
	public List<AppEntity> findApps() {
		return appService.findAll();
	}


	@PostMapping("/get")
	public AppEntity getApp(@Validated @RequestBody AppIdRequest request) {
		return appService.getByAppId(request.getAppId());
	}

}
