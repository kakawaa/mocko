package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodResponseModifyRequest;
import org.chobit.mocko.server.model.response.item.MethodItem;

import java.util.List;

/**
 * 方法Mapper
 *
 * @author robin
 */
@Mapper
public interface MethodMapper {



    /**
     * 查询类下的全部方法
     *
     * @param classId 类ID
     * @return 类下的全部方法
     */
    @Select({"select * from m_method where type_id=#{typeId}"})
    List<MethodEntity> findByClassId(@Param("typeId") String classId);


    /**
     * 更新方法返回值
     *
     * @param req 更新请求
     * @return 是否更新成功
     */
    @Update({"update m_method set response=#{req.response} where method_id=#{req.methodId}"})
    boolean modifyMethodResponse(@Param("req") MethodResponseModifyRequest req);


    /**
     * 重置方法的最新请求时间
     *
     * @param methodId 方法ID
     */
    @Update({"update m_method set last_request_time=now() where method_id=#{methodId}"})
    void resetMethodRequestTime(@Param("methodId") String methodId);


    /**
     * 新增方法记录
     *
     * @param method 方法信息
     */
    @Insert({
            "insert into m_method",
            "(app_id, type_id, method_id, method_alias, method_name, args, response_type)",
            "values",
            "(#{m.appId}, #{m.typeId}, #{m.methodId}, #{m.methodAlias}, #{m.methodName}, #{m.args}, #{m.responseType})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("m") MethodEntity method);


    /**
     * 查询方法信息，支持方法列表
     *
     * @param appId   应用ID
     * @param keyword 关键字
     * @return 方法信息集合
     */
    List<MethodItem> findList(@Param("appId") String appId, @Param("keyword") String keyword);


    /**
     * 根据methodId获取方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    MethodItem getByMethodId(@Param("methodId") String methodId);
}
