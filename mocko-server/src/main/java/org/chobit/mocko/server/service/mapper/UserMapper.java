package org.chobit.mocko.server.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.server.model.entity.UserEntity;


/**
 * 用户信息表Mapper
 *
 * @author robin
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
