package org.chobit.mocko.server.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 方法规则编辑请求
 */
@Data
public class MethodRuleModifyRequest extends MethodRuleAddRequest {


    /**
     * 规则ID
     */
    @NotNull(message = "规则ID不可为空")
    private Integer id;

}
