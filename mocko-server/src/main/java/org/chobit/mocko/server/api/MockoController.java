package org.chobit.mocko.server.api;


import org.chobit.mocko.core.model.MethodMeta;
import org.chobit.mocko.server.biz.action.MockAction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Mocko接口
 *
 * @author robin
 */
@RestController
@RequestMapping("/api")
public class MockoController {


    @Resource
    private MockAction mockAction;


    @PostMapping("/mock")
    public void mock(@RequestBody MethodMeta meta, HttpServletResponse response) throws IOException {
        String json = mockAction.queryMockResponse(meta);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
    }


}
