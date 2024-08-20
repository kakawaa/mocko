package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.MethodResponseEntity;

import java.util.List;

/**
 * @author robin
 */
@Mapper
public interface MethodResponseMapper {

	@Insert({
			"insert into m_method_response (method_id, rule, response, operator)",
			"values",
			"(#{methodId}, #{rule}, #{response}, #{operator})"
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("methodId") String methodId,
	         @Param("rule") String rule,
	         @Param("response") String response,
	         @Param("operator") int operator);


	@Select({
			"select * from m_method_response where method_id=#{methodId}"
	})
	List<MethodResponseEntity> fidByMethodId(@Param("methodId") String methodId);

}
