package org.chobit.mocko.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.model.entity.User;
import org.chobit.mocko.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户信息处理
 *
 * @author rui.zhang
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
