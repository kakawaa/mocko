package org.chobit.mocko.model;

import lombok.Data;

import java.util.List;

/**
 * 方法信息
 *
 * @author rui.zhang
 */
@Data
public class MethodInfo {


    /**
     * 方法ID
     */
    private String methodId;


    /**
     * 类名称
     */
    private String className;


    /**
     * 在mocko admin中类的名称
     */
    private String classAlias;


    /**
     * 方法名称
     */
    private String methodName;


    /**
     * 在mocko admin中方法的名称
     */
    private String methodAlias;


    /**
     * 参数信息
     */
    private List<ArgInfo> args;

}
