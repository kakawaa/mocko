package org.chobit.mocko.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.model.entity.Method;

/**
 * 方法Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface MethodMapper extends BaseMapper<Method> {
}
