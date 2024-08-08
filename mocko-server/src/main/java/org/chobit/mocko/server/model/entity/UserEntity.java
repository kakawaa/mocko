package org.chobit.mocko.server.model.entity;

import lombok.Data;

/**
 * 用户信息
 *
 * @author robin
 */
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
