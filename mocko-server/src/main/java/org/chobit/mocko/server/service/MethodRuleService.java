package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.request.MethodRuleAddRequest;
import org.chobit.mocko.server.model.request.MethodRuleModifyRequest;
import org.chobit.mocko.server.model.response.item.MethodRuleItem;
import org.chobit.mocko.server.service.mapper.MethodRuleMapper;
import org.chobit.mocko.server.tools.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 方法规则相关业务
 *
 * @author robin
 */
@Slf4j
@Service
public class MethodRuleService {


	private final MethodRuleMapper methodRuleMapper;


	@Autowired
	public MethodRuleService(MethodRuleMapper methodRuleMapper) {
		this.methodRuleMapper = methodRuleMapper;
	}


	/**
	 * 添加方法规则
	 *
	 * @param request 请求参数
	 * @return 是否添加成功
	 */
	public boolean add(MethodRuleAddRequest request) {
		String username = AuthContext.getUsername();
		return methodRuleMapper.add(request, username);
	}


	/**
	 * 修改方法规则
	 *
	 * @param request 请求参数
	 * @return 是否修改成功
	 */
	public boolean modify(MethodRuleModifyRequest request) {
		String username = AuthContext.getUsername();
		return methodRuleMapper.modify(request, username);
	}


	/**
	 * 删除方法规则
	 *
	 * @param id 规则ID
	 * @return 是否删除成功
	 */
	public boolean delete(int id) {
		return methodRuleMapper.deleteById(id);
	}


	/**
	 * 重置请求信息
	 *
	 * @param ruleId 规则ID
	 */
	public void resetRequestInfo(int ruleId) {
		methodRuleMapper.resetRequestInfo(ruleId);
	}


	/**
	 * 根据方法ID获取规则信息
	 *
	 * @param methodId 方法ID
	 * @return 规则集合
	 */
	public List<MethodRuleItem> findByMethodId(String methodId) {
		return methodRuleMapper.fidByMethodId(methodId);
	}

}
