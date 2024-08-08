package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodResponseModifyRequest;

import java.util.List;

/**
 * 方法Mapper
 *
 * @author robin
 */
@Mapper
public interface MethodMapper {


    /**
     * 根据methodId获取方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    @Select({"select * from m_method where method_id=#{methodId} limit 1"})
    MethodEntity getByMethodId(@Param("methodId") String methodId);


    /**
     * 查询类下的全部方法
     *
     * @param classId 类ID
     * @return 类下的全部方法
     */
    @Select({"select * from m_method where type_id=#{typeId}"})
    List<MethodEntity> findByCLassId(@Param("typeId") String classId);


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
     * @return 是否重置成功
     */
    @Update({"update m_method set last_request_time=now() where method_id=#{methodId}"})
    boolean resetMethodRequestTime(@Param("methodId") String methodId);
}
