package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.service.mapper.AppMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


/**
 * 应用Service
 *
 * @author robin
 */
@Slf4j
@Service
public class AppService {


    @Resource
    private AppMapper appMapper;


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
     * 获取全部的应用
     *
     * @return 应用集合
     */
    public List<AppEntity> findByUsername(String username) {
        return new LinkedList<>();
    }


    /**
     * 新增app记录
     *
     * @param app app记录
     */
    public void add(AppEntity app) {
        appMapper.add(app);
    }
}
