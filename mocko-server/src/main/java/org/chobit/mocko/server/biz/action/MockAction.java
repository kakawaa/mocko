package org.chobit.mocko.server.biz.action;

import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.utils.Collections2;
import org.chobit.mocko.core.model.ArgInfo;
import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.constants.Constants;
import org.chobit.mocko.server.constants.ResponseCode;
import org.chobit.mocko.server.except.MockoResponseException;
import org.chobit.mocko.server.model.entity.AppEntity;
import org.chobit.mocko.server.model.entity.MethodEntity;
import org.chobit.mocko.server.model.entity.TypeEntity;
import org.chobit.mocko.server.service.AppService;
import org.chobit.mocko.server.service.MethodService;
import org.chobit.mocko.server.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * Mocko业务处理类
 *
 * @author robin
 */
@Component
public class MockAction {


    @Resource
    private AppService appService;
    @Resource
    private TypeService typeService;
    @Resource
    private MethodService methodService;


    /**
     * 查询Mock的结果
     *
     * @param meta 方法元数据
     * @return mock的结果
     */
    public String queryMockResponse(MethodMeta meta) {
        String methodId = this.computeMethodId(meta);
        MethodEntity method = methodService.getByMethodId(methodId);

        if (null == method) {
            checkAndSave(meta, methodId);
            throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
        }

        if (isBlank(method.getResponse())) {
            throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
        }

        methodService.resetRequestTime(methodId);

        return method.getResponse();
    }


    /**
     * 检查并保存方法元数据等信息
     *
     * @param meta 方法元数据
     */
    private void checkAndSave(MethodMeta meta) {

        String classId = computeClassId(meta);
        TypeEntity type = typeService.getByTypeId(classId);

        if (null == type) {
            AppEntity app = appService.getByAppId(meta.getAppId());
            this.addApp(meta, app);
            this.addClass(meta, classId);
        }

        methodService.add(meta, classId);

        throw new MockoResponseException(ResponseCode.EMPTY_MOCK_RESPONSE);
    }


    /**
     * 保存应用信息
     *
     * @param meta 方法元数据
     */
    private void addApp(MethodMeta meta, AppEntity app) {
        if (null != app) {
            return;
        }

        app = new AppEntity();
        app.setAppId(meta.getAppId());
        app.setAppName(meta.getAppId());
        app.setOperatorCode(Constants.SYSTEM);
        appService.add(app);
    }


    /**
     * 保存类信息
     *
     * @param meta 方法元数据
     */
    private TypeEntity addClass(MethodMeta meta) {
        return typeService.add(meta);
    }


}
