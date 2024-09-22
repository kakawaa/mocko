package org.chobit.mocko.server.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 方法ID相关请求
 *
 * @author robin
 */
@Data
public class MethodIdRequest {


    /**
     * 方法ID
     */
    @NotBlank(message = "方法ID不可为空")
    private String methodId;


}
