package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chobit.mocko.model.entity.Package;
import org.chobit.mocko.service.mapper.PackageMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 包Service
 *
 * @author robin
 */
@Service
public class PackageService extends ServiceImpl<PackageMapper, Package> {


    /**
     * 根据应用ID查找包名
     *
     * @param appId 应用ID
     * @return 包名集合
     */
    public List<String> findByAppId(String appId) {
        LambdaQueryWrapper<Package> lqw = new LambdaQueryWrapper<>();
        lqw.select(Package::getPkgName)
                .eq(Package::getAppId, appId);
        return this.listObjs(lqw);
    }


}
