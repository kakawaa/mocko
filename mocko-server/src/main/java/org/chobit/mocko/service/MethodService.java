package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.Method;
import org.chobit.mocko.service.mapper.MethodMapper;
import org.springframework.stereotype.Service;


/**
 * 方法Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class MethodService extends ServiceImpl<MethodMapper, Method> {


    public Method getByMethodId(String methodId) {
        LambdaQueryWrapper<Method> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Method::getMethodId, methodId);
        return this.getOne(lqw);
    }

}
