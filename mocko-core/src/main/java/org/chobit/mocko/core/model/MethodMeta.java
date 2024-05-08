package org.chobit.mocko.core.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 方法信息
 *
 * @author robin
 */
@Data
public class MethodMeta {


    /**
     * 应用ID
     */
    @NotBlank(message = "应用ID不可为空")
    private String appId;


    /**
     * 类名称
     */
    @NotBlank(message = "类名不可为空")
    private String className;


    /**
     * 在mocko admin中类的名称
     */
    private String classAlias;


    /**
     * 方法名称
     */
    @NotBlank(message = "方法名不可为空")
    private String methodName;


    /**
     * 在mocko admin中方法的名称
     */
    private String methodAlias;


    /**
     * 参数信息
     */
    private List<ArgInfo> args;


    /**
     * 返回值类型
     */
    private Class<?> returnType;

}
