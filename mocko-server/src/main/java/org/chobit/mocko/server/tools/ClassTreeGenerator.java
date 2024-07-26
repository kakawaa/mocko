package org.chobit.mocko.server.tools;

import org.chobit.commons.model.TreeNode;
import org.chobit.commons.model.Tuple2;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.StrKit;
import org.chobit.mocko.server.constants.NodeType;
import org.chobit.mocko.server.model.vo.ClassTreeNode;

import java.util.*;
import java.util.stream.Collectors;

import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.constans.Symbol.POINT;
import static org.chobit.commons.utils.StrKit.isBlank;
import static org.chobit.commons.utils.StrKit.isNotBlank;

/**
 * 类信息树构建者
 *
 * @author robin
 */
public final class ClassTreeGenerator {


    /**
     * 全限定类名和类下方法名集合的映射
     */
    private final Map<String, String> typeIdMap;


    public ClassTreeGenerator(Map<String, String> typeIdMap) {
        this.typeIdMap = typeIdMap;
    }

    /**
     * 构建类信息树
     *
     * @return 类信息树根节点
     */
    public ClassTreeNode generate() {

        List<Tuple2<String, String>> classList = this.typeIdMap.keySet().stream()
                .filter(StrKit::isNotBlank)
                .map(ClassTreeGenerator::breakClassFullName)
                .collect(Collectors.toList());

        if (Collections2.isEmpty(classList)) {
            return new ClassTreeNode();
        }

        ClassTreeNode root = new ClassTreeNode(EMPTY);

        classList.stream().filter(e -> isBlank(e._1)).forEach(e -> root.addChild(e._2));

        this.generate(classList, root);

        return root;
    }


    /**
     * 构建类结构树
     *
     * @param classList  类集合
     * @param parentNode 上级节点
     */
    private void generate(List<Tuple2<String, String>> classList, ClassTreeNode parentNode) {
        String parent = buildParentPackage(parentNode);

        Set<Node> subSet = analyzeSubNode(classList, parent);

        while (subSet.size() == 1) {
            Node sub = subSet.iterator().next();

            if (isBlank(parent)) {
                parent = sub.value;
                parentNode.setValue(sub.value);
                parentNode.setClassNode(NodeType.CLASS == sub.type);
            } else if (NodeType.PACKAGE == sub.type) {
                parent = parent + POINT + sub.value;
                parentNode.setValue(parentNode.getValue() + POINT + sub.value);
            } else if (NodeType.CLASS == sub.type) {
                ClassTreeNode childNode = new ClassTreeNode(parentNode, sub.value);

                String fullName = parent + POINT + sub.value;
                String classId = typeIdMap.get(fullName);

                childNode.setClassNode(true);
                childNode.setClassId(classId);
                parentNode.addChild(childNode);
            }

            subSet = analyzeSubNode(classList, parent);
        }

        if (Collections2.isEmpty(subSet)) {
            return;
        }

        for (Node sub : subSet) {
            ClassTreeNode childNode = new ClassTreeNode(parentNode, sub.value);
            boolean isClassNode = NodeType.CLASS == sub.type;
            childNode.setClassNode(isClassNode);
            if (isClassNode) {
                String fullName = parent + POINT + sub.value;
                String classId = typeIdMap.get(fullName);
                childNode.setClassId(classId);
            }
            parentNode.addChild(childNode);
            generate(classList, childNode);
        }
    }


    /**
     * 分析获取子节点信息
     *
     * @param classList 类集合
     * @param parent    上级节点
     * @return 子节点集合
     */
    private static Set<Node> analyzeSubNode(List<Tuple2<String, String>> classList, final String parent) {

        Set<Node> result = new HashSet<>(2);

        Iterator<Tuple2<String, String>> itr = classList.iterator();
        while (itr.hasNext()) {
            Tuple2<String, String> t = itr.next();

            String pkg = t._1;

            // 空包类已做处理
            if (isBlank(pkg)) {
                continue;
            }
            // 只处理当前目录下的内容
            if (isNotBlank(parent) && !pkg.startsWith(parent)) {
                continue;
            }

            // 处理包名
            String tmp = pkg;
            if (isNotBlank(parent)) {
                if (Objects.equals(tmp, parent)) {
                    tmp = EMPTY;
                } else {
                    // 去掉parent相关的部分（parent为空时是第一次匹配）
                    tmp = pkg.substring(parent.length() + 1);
                }
            }

            if (isBlank(tmp)) {
                // 处理后子包为空，只剩下类，新增子节点
                result.add(new Node(t._2, NodeType.CLASS));
                itr.remove();
                continue;
            }

            // 提取子目录名称
            String subPkg = tmp;
            int idx = tmp.indexOf(POINT);
            if (idx > 0) {
                subPkg = tmp.substring(0, idx);
            }

            result.add(new Node(subPkg));
        }

        return result;
    }


    /**
     * 构建完整的上级包名
     *
     * @param node 当前节点
     * @return 完整的上级包名
     */
    private static String buildParentPackage(TreeNode<String> node) {

        StringBuilder builder = new StringBuilder();

        TreeNode<String> tmp = node;
        while (null != tmp) {
            if (isNotBlank(tmp.getValue())) {
                if (builder.length() == 0) {
                    builder.append(tmp.getValue());
                } else {
                    builder.insert(0, tmp.getValue() + POINT);
                }
            }
            tmp = tmp.parent();
        }

        return builder.toString();
    }


    /**
     * 拆解包名和类名
     *
     * @param classFullName 全限定类名
     * @return 包名和类名
     */
    private static Tuple2<String, String> breakClassFullName(String classFullName) {
        int idx = classFullName.lastIndexOf(POINT);
        if (idx < 0) {
            return new Tuple2<>(EMPTY, classFullName);
        }
        String pkg = classFullName.substring(0, idx + 1);
        String clazz = classFullName.substring(idx + 1);
        return new Tuple2<>(pkg, clazz);
    }


    /**
     * 节点信息
     */
    private static class Node {

        /**
         * 路径
         */
        public final String value;

        /**
         * 类型
         */
        public final NodeType type;


        public Node(String value, NodeType type) {
            this.value = value;
            this.type = type;
        }


        public Node(String value) {
            this(value, NodeType.PACKAGE);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node nodeInfo = (Node) o;
            return Objects.equals(value, nodeInfo.value) && type == nodeInfo.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, type);
        }
    }


}
