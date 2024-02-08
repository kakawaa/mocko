package org.chobit.mocko.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://baidu.com", name = "barService")
public interface IBarService {


    @GetMapping("/user/{id}")
    String foo(String bar);

}
