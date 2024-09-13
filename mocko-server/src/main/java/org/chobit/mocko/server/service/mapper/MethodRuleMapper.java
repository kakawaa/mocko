package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.request.MethodRuleAddRequest;
import org.chobit.mocko.server.model.request.MethodRuleModifyRequest;
import org.chobit.mocko.server.model.response.item.MethodRuleItem;

import java.util.List;

/**
 * @author robin
 */
@Mapper
public interface MethodRuleMapper {


    /**
     * 新增方法规则信息
     *
     * @param req      规则信息
     * @param operator 操作人
     */
    @Insert({
            "insert into m_method_rule (method_id, rule_title, expression, response, remark, operator)",
            "values",
            "(#{methodId}, #{ruleTitle}, #{expression}, #{response}, #{remark}, #{operator})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("item") MethodRuleAddRequest req, @Param("operator") int operator);


    /**
     * 方法规则更新
     *
     * @param req      规则信息
     * @param operator 操作人
     * @return 是否更新成功
     */
    @Update({
            "update m_method_rule set ",
            "rule_title=#{item.ruleTitle}, expression=#{item.expression}, response=#{item.response}, remark=#{item.remark}, operator=#{operator}",
            "where id=#{item.id}"
    })
    boolean modify(@Param("item") MethodRuleModifyRequest req, @Param("operator") int operator);


    /**
     * 根据方法ID查询规则信息
     *
     * @param methodId 方法ID
     * @return 方法返回值规则信息
     */
    @Select({
            "select * from m_method_response where method_id=#{methodId} order by id desc"
    })
    List<MethodRuleItem> fidByMethodId(@Param("methodId") String methodId);

}
