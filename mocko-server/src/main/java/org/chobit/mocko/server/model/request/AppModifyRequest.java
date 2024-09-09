package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 应用信息更新请求
 *
 * @author robin
 */
@Data
public class AppModifyRequest {


    /**
     * 应用ID
     */
    @NotBlank(message = "应用Id不可为空")
    private String appId;


    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不可为空")
    private String appName;

}
