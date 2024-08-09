package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.constants.Constants;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.service.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 应用Service
 *
 * @author robin
 */
@Slf4j
@Service
public class AppService {


	private final AppMapper appMapper;


	@Autowired
	public AppService(AppMapper appMapper) {
		this.appMapper = appMapper;
	}

	/**
	 * 根据应用Id查询应用信息
	 *
	 * @param appId 应用ID
	 * @return 应用信息
	 */
	public AppEntity getByAppId(String appId) {
		return appMapper.getByAppId(appId);
	}


	/**
	 * 更新应用名称
	 *
	 * @param appId   应用ID
	 * @param appName 应用名称
	 * @return 是否更新成功
	 */
	public Boolean modifyAppName(String appId, String appName) {
		return appMapper.modifyAppName(appId, appName);
	}


	/**
	 * 新增app记录
	 *
	 * @param appId 应用ID
	 */
	public void add(String appId) {
		AppEntity app = new AppEntity();
		app.setAppId(appId);
		app.setAppName(appId);
		app.setOperatorCode(Constants.SYSTEM);

		appMapper.add(app);
	}
}
