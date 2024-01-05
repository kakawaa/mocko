package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.App;
import org.chobit.mocko.service.mapper.AppMapper;
import org.springframework.stereotype.Service;


/**
 * 应用Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class AppService extends ServiceImpl<AppMapper, App> {

    /**
     * 根据应用Id查询应用信息
     *
     * @param appId 应用ID
     * @return 应用信息
     */
    public App getByAppId(String appId) {
        LambdaQueryWrapper<App> lqw = new LambdaQueryWrapper<>();
        lqw.eq(App::getAppId, appId);
        return this.getOne(lqw);
    }

}
