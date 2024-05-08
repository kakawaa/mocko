package org.chobit.mocko.core.model;

import lombok.Data;

import java.util.List;

/**
 * 类型节点
 * <p>
 * 每个节点需要是直接类型、直接类型的包装类或者是字符串，以及对应的数组
 *
 * @author robin
 */
@Data
public class TypeNode {


    /**
     * 名称
     */
    private String name;


    /**
     * 别名
     */
    private String alias;


    /**
     * 类型
     */
    private Class<?> clazz;


    /**
     * 子节点
     */
    private List<TypeNode> children;

}
