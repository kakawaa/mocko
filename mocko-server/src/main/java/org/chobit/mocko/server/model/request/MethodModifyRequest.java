package org.chobit.mocko.server.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 方法信息更新请求
 *
 * @author robin
 */
@Data
public class MethodModifyRequest {


	/**
	 * 方法ID
	 */
	@NotBlank(message = "方法ID不可为空")
	private String methodId;


	/**
	 * 类名
	 */
	private String typeName;


	/**
	 * 方法名称
	 */
	private String methodName;


	/**
	 * 方法描述
	 */
	@NotBlank(message = "方法描述不可为空")
	private String methodAlias;


	/**
	 * 返回值类型
	 */
	private String responseType;


}
