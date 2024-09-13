package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 方法规则新增请求
 */
@Data
public class MethodRuleAddRequest {


    /**
     * 方法ID
     */
    @NotBlank(message = "方法ID不可为空")
    private String methodId;


    /**
     * 规则名称
     */
    @NotBlank(message = "规则名称不可为空")
    private String ruleTitle;


    /**
     * 规则表达式
     */
    private String expression;


    /**
     * 响应时间
     */
    @NotBlank(message = "方法返回值不可为空")
    private String response;


    /**
     * 备注
     */
    private String remark;


}
