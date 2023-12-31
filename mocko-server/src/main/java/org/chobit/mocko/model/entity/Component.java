package org.chobit.mocko.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类信息
 *
 * @author rui.zhang
 */
@TableName("m_component")
@Data
public class Component extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 组件ID
     */
    private String cmpId;


    /**
     * 组件别名
     */
    private String cmpAlias;


    /**
     * 类名称
     */
    private String className;

}
