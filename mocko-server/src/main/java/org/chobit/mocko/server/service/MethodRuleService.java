package org.chobit.mocko.server.service;

import lombok.extern.slf4j.Slf4j;
import org.chobit.mocko.server.model.request.MethodRuleAddRequest;
import org.chobit.mocko.server.service.mapper.MethodRuleMapper;
import org.chobit.mocko.server.tools.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 方法规则相关业务
 *
 * @author robin
 */
@Slf4j
@Service
public class MethodRuleService {


    private final MethodRuleMapper methodRuleMapper;


    @Autowired
    public MethodRuleService(MethodRuleMapper methodRuleMapper) {
        this.methodRuleMapper = methodRuleMapper;
    }



    public boolean add(MethodRuleAddRequest request){
        int userId = AuthContext.getUser()


        return true;
    }

}
