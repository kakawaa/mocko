package org.chobit.mocko.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodDeleteRequest;
import org.chobit.mocko.server.model.request.MethodModifyRequest;
import org.chobit.mocko.server.model.request.MethodPageRequest;
import org.chobit.mocko.server.model.response.item.MethodItem;
import org.chobit.mocko.server.service.mapper.MethodMapper;
import org.chobit.mocko.server.service.mapper.MethodRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.chobit.mocko.server.utils.StringKit.nullOrEmpty;


/**
 * 方法Service
 *
 * @author robin
 */
@Slf4j
@Service
public class MethodService {


    private final MethodMapper methodMapper;

    private final MethodRuleMapper methodRuleMapper;


    @Autowired
    public MethodService(MethodMapper methodMapper, MethodRuleMapper methodRuleMapper) {
        this.methodMapper = methodMapper;
        this.methodRuleMapper = methodRuleMapper;
    }


    /**
     * 根据方法Id查询方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    public MethodItem getByMethodId(String methodId) {
	    return methodMapper.getByMethodId(methodId);
    }


    /**
     * 根据方法ID查找方法
     *
     * @param classId 方法ID
     * @return 类下的全部方法
     */
    public List<MethodEntity> findByClassId(String classId) {
        return methodMapper.findByClassId(classId);
    }


    /**
     * 更新方法的上次调用时间
     *
     * @param methodId 方法ID
     */
    public void resetRequestTime(String methodId) {
        methodMapper.resetMethodRequestTime(methodId);
    }


    /**
     * 新增方法记录
     *
     * @param meta     方法元数据
     * @param classId  类ID
     * @param methodId 方法ID
     */
    public void add(MethodMeta meta, String classId, String methodId) {
        MethodEntity method = new MethodEntity();
        method.setAppId(meta.getAppId());
        method.setTypeId(classId);
        method.setMethodId(methodId);
        method.setMethodAlias(nullOrEmpty(meta.getMethodAlias()));
        method.setMethodName(nullOrEmpty(meta.getMethodName()));
        method.setArgs(JsonKit.toJson(meta.getArgs()));
        method.setResponseType(meta.getReturnType().getTypeName());

        methodMapper.add(method);
    }


    /**
     * 分页查询方法信息
     *
     * @param request 查询请求
     * @return 方法信息
     */
    public PageResult<MethodItem> findInPage(MethodPageRequest request) {
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<MethodItem> list = methodMapper.findList(request.getAppId(), request.getKeyword());
        PageInfo<MethodItem> page = new PageInfo<>(list);

        PageResult<MethodItem> result = new PageResult<>(list);
        result.setPageNo(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());

        return result;
    }


    /**
     * 更新方法信息
     *
     * @param request 更新请求
     * @return 是否更新成功
     */
    public boolean modify(MethodModifyRequest request) {
        return methodMapper.modify(request);
    }


    /**
     * 删除方法记录及对应的规则记录
     *
     * @param request 请求信息
     * @return 是否删除成功
     */
    @Transactional
    public boolean delete(MethodDeleteRequest request) {
        boolean isSuccess = methodMapper.delete(request.getMethodId());
        int count = methodRuleMapper.deleteByMethodId(request.getMethodId());
        return isSuccess && count >= 0;
    }

}
