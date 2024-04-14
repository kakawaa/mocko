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


    public ClassTreeNode() {
    }


    public ClassTreeNode(String value) {
        super(value);
    }


    public ClassTreeNode(TreeNode<String> parent, String value) {
        super(parent, value);
    }


    /**
     * 是否是类
     */
    private boolean isClassNode;


    /**
     * 类ID
     */
    private String classId;


    /**
     * 是否是方法
     */
    private boolean isMethodNode;


    /**
     * 方法ID
     */
    private String methodId;


}
