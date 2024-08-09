package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.TypeEntity;

import java.util.List;

/**
 * 组件Mapper
 *
 * @author robin
 */
@Mapper
public interface TypeMapper {


    /**
     * 根据typeId获取类信息
     *
     * @param typeId 类ID
     * @return 类信息
     */
    @Select({"select * from m_type where type_id=#{typeId}"})
    TypeEntity getByTypeId(@Param("typeId") String typeId);


    /**
     * 获取应用下的类信息
     *
     * @param appId 应用ID
     * @return 应用下的类信息
     */
    @Select({"select * from m_type where app_id=#{appId}"})
    List<TypeEntity> findByAppId(@Param("appId") String appId);


    /**
     * 写入新的类
     *
     * @param type 类信息
     */
    @Insert({
            "insert into m_type",
            "(app_id, type_id, type_name, type_alias, full_name, operator_code)",
            "values",
            "(#{t.appId}, #{t.typeId}, #{t.typeName}, #{t.typeAlias}, #{t.fullName}, #{t.operatorCode})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("t") TypeEntity type);

}
