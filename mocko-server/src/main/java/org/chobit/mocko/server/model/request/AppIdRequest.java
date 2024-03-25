package org.chobit.mocko.server.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 根据应用ID执行的请求
 *
 * @author robin
 */
@Data
public class AppIdRequest {


    /**
     * 应用ID
     */
    @NotBlank(message = "应用Id不可为空")
    private String appId;

}
