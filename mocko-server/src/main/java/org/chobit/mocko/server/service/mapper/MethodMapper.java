package org.chobit.mocko.server.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chobit.mocko.server.model.entity.MethodEntity;

/**
 * 方法Mapper
 *
 * @author robin
 */
@Mapper
public interface MethodMapper extends BaseMapper<MethodEntity> {


	/**
	 * 根据methodId获取方法信息
	 *
	 * @param methodId 方法ID
	 * @return 方法信息
	 */
	@Select({"select * from m_method where method_id=#{methodId} limit 1"})
	MethodEntity getByMethodId(@Param("methodId") String methodId);


}
