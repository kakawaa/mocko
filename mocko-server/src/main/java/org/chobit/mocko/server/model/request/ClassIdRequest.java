package org.chobit.mocko.server.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 类ID相关请求
 *
 * @author robin
 */
@Data
public class ClassIdRequest {


    /**
     * 类ID
     */
    @NotBlank(message = "类ID不可为空")
    private String classId;


}
