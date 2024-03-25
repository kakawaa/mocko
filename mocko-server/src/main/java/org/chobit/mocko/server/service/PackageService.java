package org.chobit.mocko.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chobit.mocko.server.model.entity.PackageEntity;
import org.chobit.mocko.server.service.mapper.PackageMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 包Service
 *
 * @author robin
 */
@Service
public class PackageService extends ServiceImpl<PackageMapper, PackageEntity> {


    /**
     * 根据应用ID查找包名
     *
     * @param appId 应用ID
     * @return 包名集合
     */
    public List<String> findByAppId(String appId) {
        LambdaQueryWrapper<PackageEntity> lqw = new LambdaQueryWrapper<>();
        lqw.select(PackageEntity::getPkgName)
                .eq(PackageEntity::getAppId, appId);
        return this.listObjs(lqw);
    }


}
