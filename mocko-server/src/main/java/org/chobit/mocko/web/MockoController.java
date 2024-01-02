package org.chobit.mocko.web;


import com.fasterxml.jackson.databind.JsonNode;
import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.model.MethodMeta;
import org.chobit.mocko.spring.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Mocko接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/api")
public class MockoController {


    @PostMapping("")
    public JsonNode mock(@RequestBody MethodMeta info) {
        return JsonKit.parse("{}");
    }


}
