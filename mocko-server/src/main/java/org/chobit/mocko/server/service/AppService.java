package org.chobit.mocko.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.service.mapper.AppMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 应用Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class AppService extends ServiceImpl<AppMapper, AppEntity> {

    /**
     * 根据应用Id查询应用信息
     *
     * @param appId 应用ID
     * @return 应用信息
     */
    public AppEntity getByAppId(String appId) {
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AppEntity::getAppId, appId);
        return this.getOne(lqw);
    }


    /**
     * 更新应用名称
     *
     * @param appId   应用ID
     * @param appName 应用名称
     * @return 是否更新成功
     */
    public Boolean updateAppName(String appId, String appName) {
        LambdaUpdateWrapper<AppEntity> luw = new LambdaUpdateWrapper<>();
        luw.set(AppEntity::getAppName, appName)
                .eq(AppEntity::getAppId, appId);
        return this.update(luw);
    }


    /**
     * 获取全部的应用
     *
     * @return 应用集合
     */
    public List<AppEntity> findByUser(String username) {
        return this.list();
    }

}
