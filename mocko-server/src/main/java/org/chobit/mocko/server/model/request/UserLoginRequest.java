package org.chobit.mocko.server.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author robin
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = -5351030703447170573L;


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


}
