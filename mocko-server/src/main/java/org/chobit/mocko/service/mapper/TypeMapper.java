package org.chobit.mocko.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.model.entity.Type;

/**
 * 组件Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
}
