package org.chobit.mocko.server.model.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * mock 策略
 *
 * @author robin
 */
@Data
public final class MethodRuleEntity extends BaseEntity {


	/**
	 * 方法ID
	 */
	private String methodId;


	/**
	 * 规则名称
	 */
	private String ruleTitle;


	/**
	 * 规则表达式
	 */
	private String expression;


	/**
	 * 响应时间
	 */
	private String response;


	/**
	 * 是否启用
	 */
	private Integer switchFlag;


	/**
	 * 备注
	 */
	private String remark;


	/**
	 * 上次请求时间
	 */
	private LocalDateTime lastRequestTime;


	/**
	 * 请求次数
	 */
	private Integer requestCount;


}
