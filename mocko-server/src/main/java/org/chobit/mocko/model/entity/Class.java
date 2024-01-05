package org.chobit.mocko.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类信息
 *
 * @author rui.zhang
 */
@TableName("m_class")
@Data
public class Class extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 类ID
     */
    private String classId;


    /**
     * 类别名
     */
    private String classAlias;


    /**
     * 类名称
     */
    private String className;

}
