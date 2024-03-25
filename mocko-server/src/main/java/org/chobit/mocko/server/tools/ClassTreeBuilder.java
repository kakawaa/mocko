package org.chobit.mocko.server.tools;

import org.chobit.commons.model.TreeNode;
import org.chobit.commons.model.Tuple2;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.ObjKit;
import org.chobit.commons.utils.StrKit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
public final class ClassTreeBuilder {


    /**
     * 构建类信息树
     *
     * @param typeNameList 全限定类名集合
     * @return 类信息树根节点
     */
    public static TreeNode<String> build(List<String> typeNameList) {

        List<Tuple2<String, String>> classList = typeNameList.stream()
                .filter(StrKit::isNotBlank)
                .map(ClassTreeBuilder::breakClassFullName)
                .collect(Collectors.toList());

        if (Collections2.isEmpty(classList)) {
            return new TreeNode<>();
        }

        TreeNode<String> root = new TreeNode<>(EMPTY);

        classList.stream().filter(e -> isBlank(e._1)).forEach(e -> root.addChild(e._2));

        build(classList, root);

        return root;
    }


    /**
     * 构建类结构树
     *
     * @param classList  类集合
     * @param parentNode 上级节点
     */
    private static void build(List<Tuple2<String, String>> classList, TreeNode<String> parentNode) {
        String parent = buildParentPackage(parentNode);

        Set<String> subSet = analyzeSubNode(classList, parent);

        while (subSet.size() == 1) {
            String sub = subSet.iterator().next();

            if (isBlank(parent)) {
                parent = sub;
                parentNode.setValue(sub);
            } else {
                parent = parent + POINT + sub;
                parentNode.setValue(parentNode.getValue() + POINT + sub);
            }

            subSet = analyzeSubNode(classList, parent);
        }

        if (Collections2.isEmpty(subSet)) {
            return;
        }

        for (String sub : subSet) {
            TreeNode<String> childNode = new TreeNode<>(parentNode, sub);
            parentNode.addChild(childNode);
            build(classList, childNode);
        }
    }


    /**
     * 分析获取子节点信息
     *
     * @param classList 类集合
     * @param parent    上级节点
     * @return 子节点集合
     */
    private static Set<String> analyzeSubNode(List<Tuple2<String, String>> classList, final String parent) {

        Set<String> result = new HashSet<>(2);

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
            if (isNotBlank(parent) || ObjKit.nonEquals(POINT, parent)) {
                // 去掉parent相关的部分（parent为空时是第一次匹配）
                tmp = pkg.substring(parent.length());
            }

            if (isBlank(tmp)) {
                // 处理后子包为空，只剩下类，新增子节点
                result.add(t._2);
                itr.remove();
                continue;
            }

            // 提取子目录名称
            String subPkg = tmp;
            int idx = tmp.indexOf(POINT);
            if (idx > 0) {
                subPkg = tmp.substring(0, idx);
            }

            result.add(subPkg);
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
                builder.insert(0, tmp.getValue() + POINT);
            }
            tmp = tmp.getParent();
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
        String pkg = classFullName.substring(0, idx);
        String clazz = classFullName.substring(idx);
        return new Tuple2<>(pkg, clazz);
    }


    private ClassTreeBuilder() {
    }
}
