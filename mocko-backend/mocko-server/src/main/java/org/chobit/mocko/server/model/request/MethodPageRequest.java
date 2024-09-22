package org.chobit.mocko.server.model.request;

import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;

import jakarta.validation.constraints.NotBlank;

/**
 * 方法分页查询请求
 *
 * @author robin
 */
@Data
public class MethodPageRequest extends BasePageRequest {


	private static final long serialVersionUID = 7044770722265779991L;


	/**
	 * 应用ID
	 */
	@NotBlank(message = "应用ID不可为空")
	private String appId;


	/**
	 * 关键字
	 */
	private String keyword;
}
