package org.chobit.mocko.server.biz;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.request.MethodResponseModifyRequest;
import org.chobit.mocko.server.model.response.item.MethodItem;
import org.chobit.mocko.server.service.MethodService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 方法业务类
 *
 * @author robin
 */
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
    public MethodItem getByMethodId(String methodId) {
        return methodService.getByMethodId(methodId);
    }


    /**
     * 方法返回值更新请求
     *
     * @param request 请求
     * @return 更新结果
     */
    public boolean changeMethodResponse(MethodResponseModifyRequest request) {
        return methodService.modifyResponse(request);
    }

}
