package org.chobit.mocko.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.Class;
import org.chobit.mocko.service.mapper.ClassMapper;
import org.springframework.stereotype.Service;


/**
 * 类信息Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class ClassService extends ServiceImpl<ClassMapper, Class> {


    /**
     * 根据组件ID查询组件信息
     *
     * @param cmpId 组件ID
     * @return 组件信息
     */
    public Class getByClassId(String cmpId) {

        LambdaQueryWrapper<Class> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Class::getClassId, cmpId);
        return this.getOne(lqw);
    }


}
