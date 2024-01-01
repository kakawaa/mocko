package org.chobit.mocko.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 方法相关信息
 *
 * @author rui.zhang
 */
@TableName("m_method")
@Data
public class Method extends AbstractEntity {


    /**
     * 组件ID
     */
    private String cmpId;


    /**
     * 方法ID
     */
    private String methodId;


    /**
     * 方法别名
     */
    private String methodAlias;


    /**
     * 方法名称
     */
    private String methodName;


    /**
     * 参数信息
     */
    private String args;


    /**
     * 响应信息
     */
    private String response;

}
