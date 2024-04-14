package org.chobit.mocko.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodResponseUpdateRequest;
import org.chobit.mocko.server.service.mapper.MethodMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.chobit.commons.utils.StrKit.isNotBlank;


/**
 * 方法Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class MethodService extends ServiceImpl<MethodMapper, MethodEntity> {


    /**
     * 根据方法Id查询方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    public MethodEntity getByMethodId(String methodId) {
        LambdaQueryWrapper<MethodEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MethodEntity::getMethodId, methodId)
                .last("limit 1");
        return this.getOne(lqw);
    }


    /**
     * 根据方法ID查找方法
     *
     * @param classId 方法ID
     * @return 类下的全部方法
     */
    public List<MethodEntity> findByClassId(String classId) {
        LambdaQueryWrapper<MethodEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MethodEntity::getTypeId, classId);
        return this.list(lqw);
    }


    /**
     * 更新方法返回值
     *
     * @param req 更新请求
     * @return 是否更新成功
     */
    public boolean changeResponse(MethodResponseUpdateRequest req) {
        LambdaUpdateWrapper<MethodEntity> luw = new LambdaUpdateWrapper<>();
        luw.set(isNotBlank(req.getMethodAlias()), MethodEntity::getResponse, req.getMethodAlias())
                .set(MethodEntity::getResponse, req.getResponse())
                .eq(MethodEntity::getMethodId, req.getMethodId());
        return this.update(luw);
    }

}
