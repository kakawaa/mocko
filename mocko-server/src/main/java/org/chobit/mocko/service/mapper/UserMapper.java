package org.chobit.mocko.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.model.entity.UserEntity;


/**
 * 用户信息表Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
