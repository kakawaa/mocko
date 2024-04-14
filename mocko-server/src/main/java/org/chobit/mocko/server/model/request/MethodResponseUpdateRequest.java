package org.chobit.mocko.server.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 方法返回值更新请求
 *
 * @author robin
 */
@Data
public class MethodResponseUpdateRequest {

    /**
     * 方法ID
     */
    @NotBlank(message = "方法ID不可为空")
    private String methodId;


    /**
     * 方法名称
     */
    private String methodName;


    /**
     * 方法别名
     */
    private String methodAlias;


    /**
     * 方法返回值
     */
    @NotBlank(message = "方法返回值不可为空")
    private String response;

}
