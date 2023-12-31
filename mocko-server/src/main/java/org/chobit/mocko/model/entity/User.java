package org.chobit.mocko.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 *
 * @author rui.zhang
 */
@TableName("m_user")
@Data
public class User extends AbstractEntity {


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

}
