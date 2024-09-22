package org.chobit.mocko.server.model.response.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 方法规则信息
 *
 * @author robin
 */
@Data
public class MethodRuleItem {


	/**
	 * 规则ID
	 */
	private Integer id;


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
	 * 开关标记
	 */
	private Integer switchFlag;


	/**
	 * 备注
	 */
	private String remark;


	/**
	 * 上次请求时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastRequestTime;


	/**
	 * 请求次数
	 */
	private Integer requestCount;
}
