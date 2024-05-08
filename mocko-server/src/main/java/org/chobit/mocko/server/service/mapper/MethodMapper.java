package org.chobit.mocko.server.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.server.model.entity.MethodEntity;

/**
 * 方法Mapper
 *
 * @author robin
 */
@Mapper
public interface MethodMapper extends BaseMapper<MethodEntity> {
}
