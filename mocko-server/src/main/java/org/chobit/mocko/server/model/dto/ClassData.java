package org.chobit.mocko.server.model.dto;

import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * 类相关数据
 *
 * @author robin
 */
public class ClassData extends TypeEntity {


    /**
     * 方法信息集合
     */
    private final List<MethodEntity> methodList = new LinkedList<>();


    /**
     * 添加类下的方法信息
     *
     * @param method 方法信息
     */
    public void addMethod(MethodEntity method) {
        this.methodList.add(method);
    }

}
