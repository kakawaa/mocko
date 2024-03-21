package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.Type;
import org.chobit.mocko.service.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 类信息Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class TypeService extends ServiceImpl<TypeMapper, Type> {


    /**
     * 根据组件ID查询组件信息
     *
     * @param cmpId 组件ID
     * @return 组件信息
     */
    public Type getByTypeId(String cmpId) {
        LambdaQueryWrapper<Type> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Type::getTypeId, cmpId);
        return this.getOne(lqw);
    }


    /**
     * 获取应用下的所有类信息
     *
     * @param appId 应用ID
     * @return 类信息
     */
    public List<Type> findByAppId(String appId) {
        LambdaQueryWrapper<Type> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Type::getAppId, appId);
        return this.list(lqw);
    }


    /**
     * 更新类信息
     *
     * @param type 类信息
     * @return 更新结果
     */
    public Boolean updateType(Type type) {
        return false;
    }


}
