package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 方法删除请求
 *
 * @author robin
 */
@Data
public class MethodDeleteRequest {


	/**
	 * 方法ID
	 */
	@NotBlank(message = "方法Id不能为空")
	private String methodId;

}
