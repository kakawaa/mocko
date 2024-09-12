package org.chobit.mocko.server.model.entity;


import lombok.Data;

import java.util.Date;

/**
 * mock 策略
 *
 * @author robin
 */
@Data
public final class MethodRuleEntity extends BaseEntity {


    /**
     * 方法ID
     */
    private String methodId;


    /**
     * 策略表达式
     */
    private String expression;


    /**
     * 响应时间
     */
    private String response;


    /**
     * 备注
     */
    private String remark;


    /**
     * 上次请求时间
     */
    private Date lastRequestTime;


}
