package org.chobit.mocko.server.model.entity;


import lombok.Data;

import java.util.Date;

/**
 * 方法相关信息
 *
 * @author robin
 */
@Data
public final class MethodEntity extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 组件ID
     */
    private String typeId;


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
     * 返回值类型
     */
    private String responseType;


    /**
     * 响应信息
     */
    private String response;


    /**
     * 上次请求时间
     */
    private Date lastRequestTime;

}
