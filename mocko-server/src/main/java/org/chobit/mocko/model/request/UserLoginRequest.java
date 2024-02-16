package org.chobit.mocko.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录请求
 *
 * @author rui.zhang
 */
@Data
public class UserLoginRequest {


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不可为空")
    private String username;


    /**
     * 密码
     */
    @NotBlank(message = "密码信息不可为空")
    private String password;


}
