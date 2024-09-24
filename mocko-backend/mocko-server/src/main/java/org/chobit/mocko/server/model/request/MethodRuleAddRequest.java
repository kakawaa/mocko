package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chobit.commons.validation.EnumValue;
import org.chobit.mocko.server.constants.YesOrNo;

/**
 * 方法规则新增请求
 *
 * @author robin
 */
@Data
public class MethodRuleAddRequest {


	/**
	 * 方法ID
	 */
	@NotBlank(message = "方法ID不可为空")
	private String methodId;


	/**
	 * 规则名称
	 */
	@NotBlank(message = "规则名称不可为空")
	private String ruleTitle;


	/**
	 * 规则表达式
	 */
	private String expression;


	/**
	 * 响应时间
	 */
	@NotBlank(message = "方法返回值不可为空")
	private String response;


	/**
	 * 开关标记
	 */
	@EnumValue(enumClass = YesOrNo.class, message = "开关标记值非法")
	@NotBlank(message = "开关标记不可为空")
	private Integer switchFlag;


	/**
	 * 备注
	 */
	private String remark;


}
