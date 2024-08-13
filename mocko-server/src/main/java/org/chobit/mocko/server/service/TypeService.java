package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.constans.Symbol;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.constants.Constants;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.mocko.server.utils.StringKit.nullOrEmpty;


/**
 * 类信息Service
 *
 * @author robin
 */
@Slf4j
@Service
public class TypeService {


	private final TypeMapper typeMapper;


	@Autowired
	public TypeService(TypeMapper typeMapper) {
		this.typeMapper = typeMapper;
	}


	/**
	 * 根据组件ID查询组件信息
	 *
	 * @param typeId 类ID
	 * @return 组件信息
	 */
	public TypeEntity getByTypeId(String typeId) {
		return typeMapper.getByTypeId(typeId);
	}


	/**
	 * 获取应用下的所有类信息
	 *
	 * @param appId 应用ID
	 * @return 类信息
	 */
	public List<TypeEntity> findByAppId(String appId) {
		return typeMapper.findByAppId(appId);
	}


	/**
	 * 新增类信息记录
	 *
	 * @param meta   方法元数据
	 * @param typeId 类ID
	 * @return 类信息记录
	 */
	public TypeEntity add(MethodMeta meta, String typeId) {

		TypeEntity type = this.getByTypeId(typeId);
		if (null != type) {
			return type;
		}

		type = new TypeEntity();

		String fullName = meta.getClassName();
		int idx = fullName.lastIndexOf(Symbol.POINT);
		String typeName = fullName;
		if (idx > 0 && idx < fullName.length() - 1) {
			typeName = fullName.substring(idx + 1);
		}

		type.setAppId(meta.getAppId());
		type.setTypeId(typeId);
		type.setTypeName(typeName);
		type.setTypeAlias(nullOrEmpty(meta.getClassAlias()));
		type.setFullName(fullName);
		type.setOperatorCode(Constants.SYSTEM);
		typeMapper.add(type);

		return type;
	}


	/**
	 * 新增类信息
	 *
	 * @param type 类信息
	 * @return 记录ID
	 */
	public Integer add(TypeEntity type) {
		typeMapper.add(type);
		return type.getId();
	}


}
