package org.chobit.mocko.biz;

import com.fasterxml.jackson.databind.JsonNode;
import org.chobit.mocko.model.MethodMeta;
import org.chobit.mocko.model.entity.Method;
import org.chobit.mocko.service.AppService;
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
    private MethodService methodService;


    public JsonNode check(MethodMeta mm) {
        if (null == mm) {
            return null;
        }

        Method method = methodService.getByMethodId(mm.getMethodId());
        if(null == method){

        }

        return null;
    }


}
