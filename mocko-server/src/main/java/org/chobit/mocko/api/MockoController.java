package org.chobit.mocko.api;


import com.fasterxml.jackson.databind.JsonNode;
import org.chobit.mocko.biz.action.MockAction;
import org.chobit.mocko.model.MethodMeta;
import org.chobit.mocko.spring.ResponseWrapper;
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
    public JsonNode mock(@RequestBody MethodMeta meta) {
        return mockAction.queryMockResponse(meta);
    }


}
