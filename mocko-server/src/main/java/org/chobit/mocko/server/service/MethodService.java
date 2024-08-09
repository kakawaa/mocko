package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodResponseModifyRequest;
import org.chobit.mocko.server.service.mapper.MethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 方法Service
 *
 * @author robin
 */
@Slf4j
@Service
public class MethodService {


	private final MethodMapper methodMapper;


	@Autowired
	public MethodService(MethodMapper methodMapper) {
		this.methodMapper = methodMapper;
	}

	/**
	 * 根据方法Id查询方法信息
	 *
	 * @param methodId 方法ID
	 * @return 方法信息
	 */
	public MethodEntity getByMethodId(String methodId) {
		return methodMapper.getByMethodId(methodId);
	}


	/**
	 * 根据方法ID查找方法
	 *
	 * @param classId 方法ID
	 * @return 类下的全部方法
	 */
	public List<MethodEntity> findByClassId(String classId) {
		return methodMapper.findByCLassId(classId);
	}


	/**
	 * 更新方法返回值
	 *
	 * @param req 更新请求
	 * @return 是否更新成功
	 */
	public boolean modifyResponse(MethodResponseModifyRequest req) {

		return methodMapper.modifyMethodResponse(req);
	}


	/**
	 * 更新方法的上次调用时间
	 *
	 * @param methodId 方法ID
	 */
	public void resetRequestTime(String methodId) {
		methodMapper.resetMethodRequestTime(methodId);
	}


	/**
	 * 新增方法记录
	 *
	 * @param meta     方法元数据
	 * @param classId  类ID
	 * @param methodId 方法ID
	 */
	public void add(MethodMeta meta, String classId, String methodId) {
		MethodEntity method = new MethodEntity();
		method.setAppId(meta.getAppId());
		method.setTypeId(classId);
		method.setMethodId(methodId);
		method.setMethodAlias(meta.getMethodAlias());
		method.setMethodName(meta.getMethodName());
		method.setArgs(JsonKit.toJson(meta.getArgs()));
		method.setResponseType(meta.getReturnType().getTypeName());

		methodMapper.add(method);
	}


	/**
	 * 新增方法记录
	 *
	 * @param method 方法对象
	 * @return 记录ID
	 */
	public Integer add(MethodEntity method) {
		methodMapper.add(method);
		return method.getId();
	}


}
