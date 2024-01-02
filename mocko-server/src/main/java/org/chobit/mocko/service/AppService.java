package org.chobit.mocko.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.App;
import org.chobit.mocko.service.mapper.AppMapper;
import org.springframework.stereotype.Service;


/**
 * 应用Service
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class AppService extends ServiceImpl<AppMapper, App> {


}
