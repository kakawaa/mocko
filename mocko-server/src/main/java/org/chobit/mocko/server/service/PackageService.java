package org.chobit.mocko.server.service;

import org.chobit.mocko.server.service.mapper.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 包Service
 *
 * @author robin
 */
@Service
public class PackageService {


    private final PackageMapper packageMapper;


    @Autowired
    public PackageService(PackageMapper packageMapper) {
        this.packageMapper = packageMapper;
    }

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
