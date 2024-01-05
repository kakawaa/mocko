package org.chobit.mocko.biz;

import org.chobit.commons.codec.MD5;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.constants.Constants;
import org.chobit.mocko.model.ArgInfo;
import org.chobit.mocko.model.MethodMeta;
import org.chobit.mocko.model.entity.App;
import org.chobit.mocko.model.entity.Class;
import org.chobit.mocko.model.entity.Method;
import org.chobit.mocko.service.AppService;
import org.chobit.mocko.service.ClassService;
import org.chobit.mocko.service.MethodService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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
    private ClassService classService;
    @Resource
    private MethodService methodService;


    /**
     * 检查并保存方法元数据等信息
     *
     * @param meta 方法元数据
     */
    public void checkAndSave(MethodMeta meta) {
        String methodId = this.computeMethodId(meta);
        Method method = methodService.getByMethodId(methodId);

        if (null == method) {
            String classId = computeClassId(meta);
            Class classInfo = classService.getByClassId(classId);

            if (null == classInfo) {
                App app = appService.getByAppId(meta.getAppId());
                if (null == app) {
                    this.addApp(meta);
                }
                this.addClass(meta, classId);
            }

            this.addMethod(meta, classId, methodId);
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
        Class classInfo = new Class();
        classInfo.setAppId(meta.getAppId());
        classInfo.setClassId(classId);
        classInfo.setClassName(meta.getClassName());
        classInfo.setClassAlias(meta.getClassAlias());
        classInfo.setOperatorCode(Constants.SYSTEM);
        classService.save(classInfo);
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
        method.setClassId(classId);
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
