package org.chobit.mocko.server.service;

import org.chobit.mocko.server.service.mapper.PackageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 包Service
 *
 * @author robin
 */
@Service
public class PackageService  {


    @Resource
    private PackageMapper packageMapper;


    /**
     * 根据应用ID查找包名
     *
     * @param appId 应用ID
     * @return 包名集合
     */
    public List<String> findByAppId(String appId) {
        return packageMapper.findByAppId(appId);
    }


}
