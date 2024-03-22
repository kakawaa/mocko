package org.chobit.mocko.biz;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.Reference;
import org.chobit.commons.model.TreeNode;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.ObjKit;
import org.chobit.commons.utils.StrKit;
import org.chobit.mocko.model.entity.Type;
import org.chobit.mocko.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.constans.Symbol.POINT;
import static org.chobit.commons.utils.StrKit.isBlank;


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

        List<Reference<String>> classFullNameList = typeList.stream()
                .map(Type::getFullName)
                .filter(StrKit::isNotBlank)
                .map(Reference::new)
                .collect(Collectors.toList());

        if (Collections2.isEmpty(classFullNameList)) {
            return null;
        }

        TreeNode<String> root = new TreeNode<>(EMPTY);

        return root;
    }


    /**
     * 构建类树
     *
     * @param typeList 类信息集合
     */
    private void buildTypeTree(List<Reference<String>> typeList, TreeNode<String> parent) {
        Iterator<Reference<String>> itr = typeList.iterator();
        String tmp = null;
        while (itr.hasNext()) {
            Reference<String> clzRef = itr.next();
            if (isBlank(clzRef.get())) {
                itr.remove();
                continue;
            }

            int idx = clzRef.get().indexOf(POINT);

            if (idx < 0) {
                // 此处剩下的只有类名了
                parent.addChild(clzRef.get());
                itr.remove();
                continue;
            }

            String pkg = clzRef.get().substring(0, idx);
            if(isBlank(tmp) || ObjKit.nonEquals(pkg, tmp)){
                clzRef.set(clzRef.get().substring(idx));
            }else{
            }
            tmp = pkg;
        }
    }


}
