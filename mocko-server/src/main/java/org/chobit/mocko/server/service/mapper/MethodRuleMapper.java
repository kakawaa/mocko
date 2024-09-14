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
	boolean add(@Param("item") MethodRuleAddRequest req, @Param("operator") String operator);


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
	boolean modify(@Param("item") MethodRuleModifyRequest req, @Param("operator") String operator);


	/**
	 * 根据方法ID查询规则信息
	 *
	 * @param methodId 方法ID
	 * @return 方法返回值规则信息
	 */
	@Select({"select * from m_method_rule where method_id=#{methodId} order by id desc"})
	List<MethodRuleItem> fidByMethodId(@Param("methodId") String methodId);


	/**
	 * 删除方法对应的规则记录
	 *
	 * @param methodId 方法ID
	 * @return 删除的条数
	 */
	@Delete({"delete from m_method_rule where method_id=#{methodId}"})
	int deleteByMethodId(@Param("methodId") String methodId);


	/**
	 * 根据ID删除规则记录
	 *
	 * @param id 规则ID
	 * @return 是否删除成功
	 */
	@Delete({"delete from m_method_rule where id=#{id}"})
	boolean deleteById(@Param("id") int id);


	/**
	 * 重置请求次数和最后请求时间
	 *
	 * @param id 规则ID
	 */
	@Update({"update m_method_rule set ",
			"last_request_time=now(), request_count=request_count+1 ",
			"where id=#{id}"})
	void resetRequestInfo(@Param("id") int id);

}
