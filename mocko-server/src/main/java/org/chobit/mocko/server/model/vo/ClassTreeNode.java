package org.chobit.mocko.server.model.vo;

import lombok.Data;
import org.chobit.commons.model.TreeNode;

/**
 * 类信息树节点
 *
 * @author robin
 */
@Data
public class ClassTreeNode extends TreeNode<String> {


    /**
     * 是否是类
     */
    private boolean isClass;


    /**
     * 是否是方法
     */
    private boolean isMethod;


    /**
     * 方法ID
     */
    private String methodId;


}
