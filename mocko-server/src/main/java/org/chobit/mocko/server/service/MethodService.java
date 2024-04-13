package org.chobit.mocko.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.service.mapper.MethodMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 方法Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class MethodService extends ServiceImpl<MethodMapper, MethodEntity> {


    /**
     * 根据方法Id查询方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    public MethodEntity getByMethodId(String methodId) {
        LambdaQueryWrapper<MethodEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MethodEntity::getMethodId, methodId)
                .last("limit 1");
        return this.getOne(lqw);
    }


    /**
     * 根据应用ID查找方法
     *
     * @param appId 应用ID
     * @return 应用下的全部方法
     */
    public List<MethodEntity> findByAppId(String appId) {
        LambdaQueryWrapper<MethodEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MethodEntity::getAppId, appId);
        return this.list(lqw);
    }

}
