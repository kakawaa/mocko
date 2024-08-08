package org.chobit.mocko.server.model.entity;


import lombok.Data;

/**
 * 包信息
 *
 * @author robin
 */
@Data
public final class PackageEntity extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 包名
     */
    private String pkgName;


    /**
     * 父级包名
     */
    private String parentName;


}
