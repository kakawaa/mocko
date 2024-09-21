package org.chobit.mocko.server.biz.action;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.core.model.ArgInfo;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.constants.ResponseCode;
import org.chobit.mocko.server.constants.YesOrNo;
import org.chobit.mocko.server.except.MockoResponseException;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.model.response.item.MethodItem;
import org.chobit.mocko.server.model.response.item.MethodRuleItem;
import org.chobit.mocko.server.service.AppService;
import org.chobit.mocko.server.service.MethodRuleService;
import org.chobit.mocko.server.service.MethodService;
import org.chobit.mocko.server.service.TypeService;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * Mocko业务处理类
 *
 * @author robin
 */
@Slf4j
@Component
public class MockAction {


	private final AppService appService;

	private final TypeService typeService;

	private final MethodService methodService;

	private final MethodRuleService methodRuleService;


	@Autowired
	public MockAction(AppService appService, TypeService typeService,
	                  MethodService methodService, MethodRuleService methodRuleService) {
		this.appService = appService;
		this.typeService = typeService;
		this.methodService = methodService;
		this.methodRuleService = methodRuleService;
	}


	/**
	 * 查询Mock的结果
	 *
	 * @param meta 方法元数据
	 * @return mock的结果
	 */
	public String queryMockResponse(MethodMeta meta) {
		String methodId = this.computeMethodId(meta);
		MethodItem method = methodService.getByMethodId(methodId);

		if (null == method) {
			checkAndSave(meta, methodId);
			throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
		}

		List<MethodRuleItem> methodRuleList = methodRuleService.findByMethodId(methodId);
		if (Collections2.isEmpty(methodRuleList)) {
			throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
		}

		// 过滤出正在启用的规则
		methodRuleList = methodRuleList
				.stream().filter(e -> YesOrNo.YES.is(e.getSwitchFlag())).collect(Collectors.toList());
		if (Collections2.isEmpty(methodRuleList)) {
			throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
		}

		// 根据参数匹配出响应信息
		MethodRuleItem rule = this.matchResponse(meta.getArgs(), methodRuleList);
		if (null == rule || isBlank(rule.getResponse())) {
			throw new MockoResponseException(ResponseCode.NONE_VALID_MOCK_RESPONSE);
		}

		// 重置请求信息
		methodService.resetRequestTime(methodId);
		methodRuleService.resetRequestInfo(rule.getId());

		return rule.getResponse();
	}


	private MethodRuleItem matchResponse(List<ArgInfo> args, List<MethodRuleItem> rules) {

		if (Collections2.isEmpty(rules)) {
			return rules.get(0);
		}

		Map<String, Object> map = new LinkedHashMap<>();
		for (int i = 0; i < args.size(); i++) {
			String key = "$" + i;
			map.put(key, args.get(i).getValue());
		}

		for (MethodRuleItem rule : rules) {
			// 不设置表达式时，默认是通用规则
			if (isBlank(rule.getExpression())) {
				return rule;
			}

			// 根据表达式和参数匹配出预期的结果
			boolean isMatched;
			try {
				isMatched = MVEL.evalToBoolean(rule.getExpression(), map);
			} catch (Exception e) {
				logger.info("match response error, expression: {}, args: {}", rule.getExpression(), JsonKit.toJson(map));
				continue;
			}

			if (isMatched) {
				return rule;
			}
		}

		return null;
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
