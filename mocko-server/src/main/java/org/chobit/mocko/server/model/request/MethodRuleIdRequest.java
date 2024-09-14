package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 方法规则ID相关请求
 *
 * @author robin
 */
@Data
public class MethodRuleIdRequest {


	/**
	 * 规则ID
	 */
	@NotNull(message = "规则ID不可为空")
	private Integer id;


}
