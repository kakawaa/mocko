package org.chobit.mocko.server.biz;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.Collections2;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.model.response.item.ClassTreeNode;
import org.chobit.mocko.server.service.MethodService;
import org.chobit.mocko.server.service.TypeService;
import org.chobit.mocko.server.tools.ClassTreeGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.chobit.commons.utils.Collections2.listOf;


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
    @Resource
    private MethodService methodService;


    /**
     * 构建类树
     *
     * @param appId 应用ID
     * @return 类树
     */
    public List<ClassTreeNode> obtainAppClassTree(String appId) {

        List<TypeEntity> typeList = typeService.findByAppId(appId);
        if (Collections2.isEmpty(typeList)) {
            return new LinkedList<>();
        }

        Map<String, String> typeIdMap = typeList.stream().collect(Collectors.toMap(TypeEntity::getFullName, TypeEntity::getTypeId));

        ClassTreeNode root = new ClassTreeGenerator(typeIdMap).generate();

        return listOf(root);
    }


    /**
     * 查找类的方法
     *
     * @param classId 类ID
     * @return 类下的方法信息
     */
    public List<ClassTreeNode> findMethods(String classId) {
        List<MethodEntity> methodList = methodService.findByClassId(classId);
        if (Collections2.isEmpty(methodList)) {
            return new LinkedList<>();
        }

        return methodList.stream()
                .map(e -> {
                    ClassTreeNode node = new ClassTreeNode();
                    node.setMethodNode(true);
                    node.setMethodId(e.getMethodId());
                    node.setValue(e.getMethodName());
                    return node;
                })
                .collect(Collectors.toList());
    }

}
