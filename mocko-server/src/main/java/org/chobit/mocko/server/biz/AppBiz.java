package org.chobit.mocko.server.biz;

import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.request.AppModifyRequest;
import org.chobit.mocko.server.service.AppService;
import org.chobit.mocko.server.tools.AuthContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用相关业务处理
 *
 * @author robin
 */
@Component
public class AppBiz {


    @Resource
    private AppService appService;


    /**
     * 更新应用信息
     *
     * @param request 应用更新请求
     * @return true 更新成功, false 更新失败
     */
    public boolean update(AppModifyRequest request) {
        return appService.updateAppName(request.getAppId(), request.getAppName());
    }


    /**
     * 获取用户应用集合
     *
     * @return 用户应用集合
     */
    public List<AppEntity> findAll() {
        return appService.findByUser(AuthContext.getUsername());
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
