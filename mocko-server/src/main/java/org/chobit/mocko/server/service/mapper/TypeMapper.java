package org.chobit.mocko.server.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.server.model.entity.TypeEntity;

/**
 * 组件Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface TypeMapper extends BaseMapper<TypeEntity> {
}
