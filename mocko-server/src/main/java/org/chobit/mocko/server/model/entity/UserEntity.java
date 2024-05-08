package org.chobit.mocko.server.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 *
 * @author robin
 */
@TableName("m_user")
@Data
public final class UserEntity extends AbstractEntity {


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
