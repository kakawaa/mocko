package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.constants.Constants;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.mapper.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类信息Service
 *
 * @author robin
 */
@Slf4j
@Service
public class TypeService {


    @Resource
    private TypeMapper typeMapper;


    /**
     * 根据组件ID查询组件信息
     *
     * @param typeId 类ID
     * @return 组件信息
     */
    public TypeEntity getByTypeId(String typeId) {
        return typeMapper.getByTypeId(typeId);
    }


    /**
     * 获取应用下的所有类信息
     *
     * @param appId 应用ID
     * @return 类信息
     */
    public List<TypeEntity> findByAppId(String appId) {
        return typeMapper.findByAppId(appId);
    }


    /**
     * 更新类信息
     *
     * @param type 类信息
     * @return 更新结果
     */
    public Boolean updateType(TypeEntity type) {
        return false;
    }


    /**
     * 新增类信息记录
     *
     * @param meta 方法元数据
     * @return 类信息记录
     */
    public TypeEntity add(MethodMeta meta) {

        String classId = this.computeClassId(meta);

        TypeEntity type = this.getByTypeId(classId);
        if (null != type) {
            return type;
        }

        type = new TypeEntity();

        String fullName = meta.getClassName();
        int idx = fullName.lastIndexOf(Symbol.POINT);
        String typeName = fullName;
        if (idx > 0 && idx < fullName.length() - 1) {
            typeName = fullName.substring(idx + 1);
        }

        type.setAppId(meta.getAppId());
        type.setTypeId(classId);
        type.setTypeName(typeName);
        type.setTypeAlias(meta.getClassAlias());
        type.setFullName(fullName);
        type.setOperatorCode(Constants.SYSTEM);
        typeMapper.add(type);

        return type;
    }


    /**
     * 新增类信息
     *
     * @param type 类信息
     * @return 记录ID
     */
    public Integer add(TypeEntity type) {
        typeMapper.add(type);
        return type.getId();
    }


    /**
     * 计算类ID
     *
     * @param meta 方法元数据
     * @return 类ID
     */
    private String computeClassId(MethodMeta meta) {
        String s = meta.getAppId() + Symbol.SHARP + meta.getClassName();
        return MD5.encode(s);
    }
}
