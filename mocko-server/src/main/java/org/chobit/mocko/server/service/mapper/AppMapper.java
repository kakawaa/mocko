package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.AppEntity;

@Mapper
public interface AppMapper {


    /**
     * 获取应用信息
     *
     * @param appId 应用ID
     * @return 应用信息
     */
    @Select({"select * from m_app where app_id=#{appId} limit 1"})
    AppEntity getByAppId(@Param("appId") String appId);


    /**
     * 修改应用名称
     *
     * @param appName 应用名称
     * @param appId   应用ID
     * @return 应用名称
     */
    @Update({"update m_app set app_name=#{appName}",
            "where app_id=#{appId} "})
    boolean modifyAppName(@Param("appName") String appName, @Param("appId") String appId);


    /**
     * 新增应用记录
     *
     * @param app 应用信息
     */
    @Insert({
            "insert into m_app (app_id, app_name, operator_code)",
            "values",
            "(#{app.appId}, #{app.appName}, #{app.operatorCode})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("app") AppEntity app);

}
