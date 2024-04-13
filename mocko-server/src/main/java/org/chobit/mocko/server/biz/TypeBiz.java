package org.chobit.mocko.server.biz;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.TreeNode;
import org.chobit.commons.utils.Collections2;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.MethodService;
import org.chobit.mocko.server.service.TypeService;
import org.chobit.mocko.server.tools.ClassTreeGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    public List<TreeNode<String>> obtainAppClassTree(String appId) {

        List<TypeEntity> typeList = typeService.findByAppId(appId);
        if (Collections2.isEmpty(typeList)) {
            return null;
        }


        List<MethodEntity> methodList = methodService.findByAppId(appId);
        Map<MethodEntity, String> methodMap = methodList.stream().collect(Collectors.toMap(Function.identity(), MethodEntity::getTypeId));
        Map<String, String> typeIdMap = typeList.stream().collect(Collectors.toMap(TypeEntity::getTypeId, TypeEntity::getFullName));

        Map<String, List<MethodEntity>> typeMethodMap = new HashMap<>(16);
        methodMap.forEach((k, v)->{
            String typeFullName = typeIdMap.get(v);

            List<MethodEntity> list = typeMethodMap.get(typeFullName);
            if(null == list){
                list = new LinkedList<>();
                typeMethodMap.put(v, list);
            }

            list.add(k);
        });


        TreeNode<String> root = ClassTreeGenerator.generate(typeNameList);

        return listOf(root);
    }


}
