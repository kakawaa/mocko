package org.chobit.mocko.server.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.chobit.mocko.server.model.entity.AppEntity;

@Mapper
public interface AppMapper extends BaseMapper<AppEntity> {


	@Select({"select * from m_app where app_id=#{appId} limit 1"})
	AppEntity getByAppId(@Param("appId") String appId);


	@Update({"update m_app set app_name=#{appName} where app_id=#{appId} "})
	boolean modifyAppName(@Param("appName") String appName, @Param("appId")String appId);


}
