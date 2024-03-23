package org.chobit.mocko.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 包信息
 *
 * @author robin
 */
@TableName("m_package")
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
