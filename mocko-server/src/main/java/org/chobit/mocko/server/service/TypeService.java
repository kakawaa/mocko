package org.chobit.mocko.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 类信息Service
 *
 * @author robin
 */
@Slf4j
@Service
public class TypeService extends ServiceImpl<TypeMapper, TypeEntity> {


    /**
     * 根据组件ID查询组件信息
     *
     * @param cmpId 组件ID
     * @return 组件信息
     */
    public TypeEntity getByTypeId(String cmpId) {
        LambdaQueryWrapper<TypeEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TypeEntity::getTypeId, cmpId);
        return this.getOne(lqw);
    }


    /**
     * 获取应用下的所有类信息
     *
     * @param appId 应用ID
     * @return 类信息
     */
    public List<TypeEntity> findByAppId(String appId) {
        LambdaQueryWrapper<TypeEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TypeEntity::getAppId, appId);
        return this.list(lqw);
    }


    /**
     * 更新类信息
     *
     * @param type 类信息
     * @return 更新结果
     */
    public Boolean updateType(TypeEntity type) {
        return false;
    }


}
