package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.UserEntity;


/**
 * 用户信息表Mapper
 *
 * @author robin
 */
@Mapper
public interface UserMapper {


    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息集合
     */
    @Select({"select * from m_user where username=#{username} and password=#{password}"})
    UserEntity getByUserAndPassword(@Param("username") String username,
                                    @Param("password") String password);


    /**
     * 新增用户记录
     *
     * @param entity 用户信息
     */
    @Insert({
            "insert into m_user (username, password, nickname)",
            "values",
            "(#{item.username}, #{item.password}, #{item.nickname})",
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("item") UserEntity entity);
}
