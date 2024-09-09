package org.chobit.mocko.server.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 方法返回值更新请求
 *
 * @author robin
 */
@Data
public class MethodResponseModifyRequest {

    /**
     * 方法ID
     */
    @NotBlank(message = "方法ID不可为空")
    private String methodId;


    /**
     * 方法返回值
     */
    @NotBlank(message = "方法返回值不可为空")
    private String response;

}
