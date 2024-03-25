package org.chobit.mocko.core.model;

import lombok.Data;

/**
 * 方法参数信息
 *
 * @author rui.zhang
 */
@Data
public class ArgInfo {


    /**
     * 参数类型
     */
    private String argClass;


    /**
     * 参数名
     */
    private String argName;


    /**
     * 参数值
     */
    private Object value;


}
