package org.chobit.mocko.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类信息
 *
 * @author rui.zhang
 */
@TableName("m_type")
@Data
public final class Type extends AbstractEntity {


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
