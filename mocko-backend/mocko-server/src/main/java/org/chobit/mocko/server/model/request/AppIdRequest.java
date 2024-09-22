package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


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
