package org.chobit.mocko.biz;

import com.fasterxml.jackson.databind.JsonNode;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.constants.Constants;
import org.chobit.mocko.constants.ResponseCode;
import org.chobit.mocko.except.MockoServerException;
import org.chobit.mocko.model.ArgInfo;
import org.chobit.mocko.model.MethodMeta;
import org.chobit.mocko.model.entity.App;
import org.chobit.mocko.model.entity.Type;
import org.chobit.mocko.model.entity.Method;
import org.chobit.mocko.service.AppService;
import org.chobit.mocko.service.TypeService;
import org.chobit.mocko.service.MethodService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * Mocko业务处理类
 *
 * @author robin
 */
@Component
public class MockoBiz {


    @Resource
    private AppService appService;
    @Resource
    private TypeService typeService;
    @Resource
    private MethodService methodService;


    public JsonNode queryMockResponse(MethodMeta meta) {
        String methodId = this.computeMethodId(meta);
        Method method = methodService.getByMethodId(methodId);

        checkAndSave(meta, method, methodId);

        if (isBlank(method.getResponse())) {
            throw new MockoServerException(ResponseCode.EMPTY_MOCK_RESPONSE);
        }

        JsonNode result = JsonKit.parse(method.getResponse());
        if (null == result) {
            throw new MockoServerException(ResponseCode.ILLEGAL_MOCK_RESPONSE);
        }

        return result;
    }


    /**
     * 检查并保存方法元数据等信息
     *
     * @param meta     方法元数据
     * @param method   方法信息
     * @param methodId 方法ID
     */
    private void checkAndSave(MethodMeta meta, Method method, String methodId) {

        if (null == method) {
            String classId = computeClassId(meta);
            Type type = typeService.getByTypeId(classId);

            if (null == type) {
                App app = appService.getByAppId(meta.getAppId());
                if (null == app) {
                    this.addApp(meta);
                }
                this.addClass(meta, classId);
            }

            this.addMethod(meta, classId, methodId);

            throw new MockoServerException(ResponseCode.METHOD_NOT_EXISTS);
        }
    }


    /**
     * 保存应用信息
     *
     * @param meta 方法元数据
     */
    private void addApp(MethodMeta meta) {
        App app = new App();
        app.setAppId(meta.getAppId());
        app.setOperatorCode(Constants.SYSTEM);
        appService.save(app);
    }


    /**
     * 保存类信息
     *
     * @param meta    方法元数据
     * @param classId 类ID
     */
    private void addClass(MethodMeta meta, String classId) {
        Type typeInfo = new Type();
        typeInfo.setAppId(meta.getAppId());
        typeInfo.setTypeId(classId);
        typeInfo.setTypeName(meta.getClassName());
        typeInfo.setTypeAlias(meta.getClassAlias());
        typeInfo.setOperatorCode(Constants.SYSTEM);
        typeService.save(typeInfo);
    }


    /**
     * 保存方法信息
     *
     * @param meta     方法元数据
     * @param classId  类ID
     * @param methodId 方法ID
     */
    private void addMethod(MethodMeta meta, String classId, String methodId) {
        Method method = new Method();
        method.setTypeId(classId);
        method.setMethodId(methodId);
        method.setMethodAlias(meta.getMethodAlias());
        method.setMethodName(meta.getMethodName());
        method.setArgs(JsonKit.toJson(meta.getArgs()));
        methodService.save(method);
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


    /**
     * 计算方法ID
     *
     * @param meta 方法元数据
     * @return 方法ID
     */
    private String computeMethodId(MethodMeta meta) {
        StringBuilder builder = new StringBuilder();
        builder.append(meta.getAppId())
                .append(Symbol.SHARP)
                .append(meta.getClassName())
                .append(Symbol.SHARP)
                .append(meta.getMethodName())
                .append(Symbol.SHARP);

        for (ArgInfo arg : meta.getArgs()) {
            builder.append(arg.getArgClass())
                    .append(Symbol.COMMA);
        }

        return MD5.encode(builder.toString());
    }

}
