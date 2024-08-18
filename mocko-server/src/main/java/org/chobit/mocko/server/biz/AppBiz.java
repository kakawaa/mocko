package org.chobit.mocko.server.biz;

import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.request.AppModifyRequest;
import org.chobit.mocko.server.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 应用相关业务处理
 *
 * @author robin
 */
@Component
public class AppBiz {


	private final AppService appService;

	@Autowired
	public AppBiz(AppService appService) {
		this.appService = appService;
	}


	/**
	 * 更新应用信息
	 *
	 * @param request 应用更新请求
	 * @return true 更新成功, false 更新失败
	 */
	public boolean update(AppModifyRequest request) {
		return appService.modifyAppName(request.getAppId(), request.getAppName());
	}


	/**
	 * 获取应用信息
	 *
	 * @param appId 应用ID
	 * @return 应用信息
	 */
	public AppEntity getApp(String appId) {
		return appService.getByAppId(appId);
	}


}
