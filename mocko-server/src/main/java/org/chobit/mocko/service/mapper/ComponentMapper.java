package org.chobit.mocko.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.model.entity.Component;

/**
 * 组件Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface ComponentMapper extends BaseMapper<Component> {
}
