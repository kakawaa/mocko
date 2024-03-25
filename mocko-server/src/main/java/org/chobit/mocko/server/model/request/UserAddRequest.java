package org.chobit.mocko.server.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 新增用户请求
 *
 * @author robin
 */
@Data
public class UserAddRequest implements Serializable {


    private static final long serialVersionUID = -2290314969468831667L;


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不可为空")
    private String username;


    /**
     * 密码
     */
    @NotBlank(message = "密码不可为空")
    private String password;


    /**
     * 密码确认
     */
    @NotBlank(message = "密码确认不可为空")
    private String rePassword;


    /**
     * 昵称
     */
    @NotBlank(message = "昵称不可为空")
    private String nickname;


}
