package org.chobit.mocko.server.api;


import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.biz.action.MockAction;
import org.chobit.mocko.server.config.response.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Mocko接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/api")
public class MockoController {


    @Resource
    private MockAction mockAction;


    @PostMapping("/mock")
    public Object mock(@RequestBody MethodMeta meta) {
        return mockAction.queryMockResponse(meta);
    }


}
