package org.chobit.mocko.server.model.entity;

import lombok.Data;

/**
 * 类信息
 *
 * @author robin
 */
@Data
public class TypeEntity extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 类ID
     */
    private String typeId;


    /**
     * 类别名
     */
    private String typeAlias;


    /**
     * 类名称
     */
    private String typeName;


    /**
     * 全限定类名
     */
    private String fullName;


    /**
     * 类描述信息
     */
    private String remark;

}
