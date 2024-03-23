package org.chobit.mocko.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.mocko.model.entity.PackageEntity;


/**
 * 包信息表Mapper
 *
 * @author rui.zhang
 */
@Mapper
public interface PackageMapper extends BaseMapper<PackageEntity> {


}
