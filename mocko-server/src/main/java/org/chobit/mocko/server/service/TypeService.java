package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类信息Service
 *
 * @author robin
 */
@Slf4j
@Service
public class TypeService {


    @Resource
    private TypeMapper typeMapper;


    /**
     * 根据组件ID查询组件信息
     *
     * @param typeId 类ID
     * @return 组件信息
     */
    public TypeEntity getByTypeId(String typeId) {
        return typeMapper.getByTypeId(typeId);
    }


    /**
     * 获取应用下的所有类信息
     *
     * @param appId 应用ID
     * @return 类信息
     */
    public List<TypeEntity> findByAppId(String appId) {
        return typeMapper.findByAppId(appId);
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
