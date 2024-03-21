package org.chobit.mocko.biz;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.TreeNode;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.StrKit;
import org.chobit.mocko.model.entity.Type;
import org.chobit.mocko.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 类相关操作
 *
 * @author robin
 */
@Slf4j
@Component
public class TypeBiz {


    @Resource
    private TypeService typeService;


    /**
     * 构建类树
     *
     * @param appId 应用ID
     * @return 类树
     */
    public TreeNode<String> buildTypeTree(String appId) {

        List<Type> typeList = typeService.findByAppId(appId);
        if (Collections2.isEmpty(typeList)) {
            return null;
        }

        List<String> classFullNameList = typeList.stream()
                .map(Type::getFullName)
                .filter(StrKit::isNotBlank)
                .collect(Collectors.toList());

        if (Collections2.isEmpty(classFullNameList)) {
            return null;
        }

        TreeNode<String> root = new TreeNode<>();

        return root;
    }


    /**
     * 构建类树
     *
     * @param parent   上级节点
     * @param typeList 类信息集合
     * @return 类信息树
     */
    private TreeNode<String> buildTypeTree(TreeNode<String> parent, List<String> typeList) {
        return null;
    }


}
