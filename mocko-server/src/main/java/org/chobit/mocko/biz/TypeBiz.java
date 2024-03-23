package org.chobit.mocko.biz;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.Reference;
import org.chobit.commons.model.TreeNode;
import org.chobit.commons.model.Tuple2;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.ObjKit;
import org.chobit.commons.utils.StrKit;
import org.chobit.mocko.model.entity.TypeEntity;
import org.chobit.mocko.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.constans.Symbol.POINT;
import static org.chobit.commons.utils.StrKit.isBlank;
import static org.chobit.commons.utils.StrKit.isNotBlank;


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

        List<TypeEntity> typeList = typeService.findByAppId(appId);
        if (Collections2.isEmpty(typeList)) {
            return null;
        }

        List<Tuple2<String, String>> classList = typeList.stream()
                .map(TypeEntity::getFullName)
                .filter(StrKit::isNotBlank)
                .map(this::breakClassFullName)
                .collect(Collectors.toList());

        if (Collections2.isEmpty(classList)) {
            return null;
        }

        TreeNode<String> root = new TreeNode<>(EMPTY);
        classList.stream().filter(e -> isBlank(e._1)).forEach(e -> root.addChild(e._2));

        for (Tuple2<String, String> t : classList) {

        }

        return root;
    }



    private String findParentPkg(List<Tuple2<String, String>> classList, final String parent){

        for(Tuple2<String, String> t : classList){
            String pkg = t._1;

            // 空包类已做处理
            if(isBlank(pkg)){
                continue;
            }

            // 只处理当前目录下的内容
            if(isNotBlank(parent) && !pkg.startsWith(parent)){
                continue;
            }


        }

    }


    /**
     * 拆解包名和类名
     *
     * @param classFullName 全限定类名
     * @return 包名和类名
     */
    private Tuple2<String, String> breakClassFullName(String classFullName) {
        int idx = classFullName.lastIndexOf(POINT);
        if (idx < 0) {
            return new Tuple2<>(EMPTY, classFullName);
        }
        String pkg = classFullName.substring(0, idx);
        String clazz = classFullName.substring(idx);
        return new Tuple2<>(pkg, clazz);
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
            Reference<String> ref = itr.next();
            if (isBlank(ref.get())) {
                itr.remove();
                continue;
            }

            int idx = ref.get().indexOf(POINT);

            if (idx < 0) {
                // 此处剩下的只有类名了
                parent.addChild(ref.get());
                itr.remove();
                continue;
            }

            String pkg = ref.get().substring(0, idx);
            if (isBlank(tmp) || ObjKit.nonEquals(pkg, tmp)) {
                ref.set(ref.get().substring(idx));
            } else {

            }
            tmp = pkg;
        }
    }


}
