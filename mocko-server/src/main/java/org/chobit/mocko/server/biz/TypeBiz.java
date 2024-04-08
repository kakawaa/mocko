package org.chobit.mocko.server.biz;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.TreeNode;
import org.chobit.commons.utils.Collections2;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.TypeService;
import org.chobit.mocko.server.tools.ClassTreeBuilder;
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
    public TreeNode<String> obtainTree(String appId) {

        List<TypeEntity> typeList = typeService.findByAppId(appId);
        if (Collections2.isEmpty(typeList)) {
            return null;
        }

        List<String> typeNameList = typeList.stream().map(TypeEntity::getFullName).collect(Collectors.toList());

        return ClassTreeBuilder.build(typeNameList);
    }


}
