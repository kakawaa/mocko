package org.chobit.mocko.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.Component;
import org.chobit.mocko.service.mapper.ComponentMapper;
import org.springframework.stereotype.Service;


/**
 * 组件Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class ComponentService extends ServiceImpl<ComponentMapper, Component> {

}
