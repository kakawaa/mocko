package org.chobit.mocko.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 应用信息更新请求
 *
 * @author rui.zhang
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
