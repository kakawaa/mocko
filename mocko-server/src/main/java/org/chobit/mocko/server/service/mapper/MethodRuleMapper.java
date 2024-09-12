package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.MethodRuleEntity;

import java.util.List;

/**
 * @author robin
 */
@Mapper
public interface MethodRuleMapper {


    /**
     * 新增方法规则信息
     *
     * @param methodId   方法ID
     * @param expression 规则
     * @param response   返回值* @param operator   操作人
     */
    @Insert({
            "insert into m_method_rule (method_id, expression, response, operator)",
            "values",
            "(#{methodId}, #{rule}, #{response}, #{operator})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("methodId") String methodId,
             @Param("rule") String expression,
             @Param("response") String response,
             @Param("operator") int operator);


    @Select({
            "select * from m_method_response where method_id=#{methodId}"
    })
    List<MethodRuleEntity> fidByMethodId(@Param("methodId") String methodId);

}
