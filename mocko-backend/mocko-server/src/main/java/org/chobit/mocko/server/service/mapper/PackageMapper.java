package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 包信息表Mapper
 *
 * @author robin
 */
@Mapper
public interface PackageMapper {


    /**
     * 获取应用下的全部包名
     *
     * @param appId 应用ID
     * @return 应用下的包集合
     */
    @Select({"select pkg_name from m_package where app_id=#{appId}"})
    List<String> findByAppId(@Param("appId") String appId);


}
