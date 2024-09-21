package org.chobit.mocko.server.model.response.item;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import static org.chobit.commons.constans.CommonConstants.COMMON_DATETIME_PATTERN;

/**
 * 方法信息
 *
 * @author robin
 */
@Data
public class MethodItem {


    /**
     * 应用ID
     */
    private String appId;

    /**
     * 类ID
     */
    private String typeId;

    /**
     * 方法ID
     */
    private String methodId;

    /**
     * 类描述
     */
    private String typeAlias;

    /**
     * 类全名
     */
    private String typeName;

    /**
     * 方法描述
     */
    private String methodAlias;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法返回值类型
     */
    private String responseType;

    /**
     * 方法记录时间
     */
    private LocalDateTime createTime;

    /**
     * 方法上次请求时间
     */
    @JsonFormat(pattern = COMMON_DATETIME_PATTERN)
    private LocalDateTime lastRequestTime;

}
