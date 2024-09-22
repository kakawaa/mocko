package org.chobit.mocko.server.model.request;

import lombok.Data;

/**
 * 方法规则开关请求
 *
 * @author robin
 */
@Data
public class MethodRuleSwitchRequest {


	/**
	 * 规则ID
	 */
	private Integer id;


	/**
	 * 开关标记
	 */
	private Integer switchFlag;


}
