package org.chobit.mocko.server.biz.action;

import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.utils.Collections2;
import org.chobit.mocko.core.model.ArgInfo;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.constants.ResponseCode;
import org.chobit.mocko.server.except.MockoResponseException;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.AppService;
import org.chobit.mocko.server.service.MethodService;
import org.chobit.mocko.server.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * Mocko业务处理类
 *
 * @author robin
 */
@Component
public class MockAction {


	@Resource
	private AppService appService;
	@Resource
	private TypeService typeService;
	@Resource
	private MethodService methodService;


	/**
	 * 查询Mock的结果
	 *
	 * @param meta 方法元数据
	 * @return mock的结果
	 */
	public String queryMockResponse(MethodMeta meta) {
		String methodId = this.computeMethodId(meta);
		MethodEntity method = methodService.getByMethodId(methodId);

		if (null == method) {
			checkAndSave(meta, methodId);
			throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
		}

		if (isBlank(method.getResponse())) {
			throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
		}

		methodService.resetRequestTime(methodId);

		return method.getResponse();
	}


	/**
	 * 检查并保存方法元数据等信息
	 *
	 * @param meta 方法元数据
	 */
	private void checkAndSave(MethodMeta meta, String methodId) {

		String typeId = computeTypeId(meta);
		TypeEntity type = typeService.getByTypeId(typeId);

		if (null == type) {
			AppEntity app = appService.getByAppId(meta.getAppId());
			if (null == app) {
				appService.add(meta.getAppId());
			}
			typeService.add(meta, typeId);
		}

		methodService.add(meta, typeId, methodId);

		throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
	}


	/**
	 * 计算类ID
	 *
	 * @param meta 方法元数据
	 * @return 类ID
	 */
	private String computeTypeId(MethodMeta meta) {
		String s = meta.getAppId() + Symbol.SHARP + meta.getClassName();
		return MD5.encode(s);
	}


	/**
	 * 计算方法ID
	 *
	 * @param meta 方法元数据
	 * @return 方法ID
	 */
	private String computeMethodId(MethodMeta meta) {

		StringBuilder builder = new StringBuilder();
		builder.append(meta.getAppId())
				.append(Symbol.SHARP)
				.append(meta.getClassName())
				.append(Symbol.SHARP)
				.append(meta.getMethodName())
				.append(Symbol.SHARP);

		if (Collections2.isNotEmpty(meta.getArgs())) {
			for (ArgInfo arg : meta.getArgs()) {
				builder.append(arg.getArgClass())
						.append(Symbol.COMMA);
			}
		}

		return MD5.encode(builder.toString());
	}

}
