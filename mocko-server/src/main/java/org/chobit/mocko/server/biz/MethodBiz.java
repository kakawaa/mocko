package org.chobit.mocko.server.biz;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.core.annotations.Mocko;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.request.MethodResponseUpdateRequest;
import org.chobit.mocko.server.service.MethodService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 方法业务类
 *
 * @author rui.zhang
 */
@Mocko
@Slf4j
@Component
public class MethodBiz {


    @Resource
    private MethodService methodService;


    /**
     * 获取方法信息
     *
     * @param methodId 方法ID
     * @return 方法信息
     */
    public MethodEntity getByMethodId(String methodId) {
        return methodService.getByMethodId(methodId);
    }


    /**
     * 方法返回值更新请求
     *
     * @param request 请求
     * @return 更新结果
     */
    public boolean changeMethodResponse(MethodResponseUpdateRequest request) {
        return methodService.changeResponse(request);
    }

}
