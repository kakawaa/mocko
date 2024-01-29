package org.chobit.mocko.service;

import org.chobit.mocko.annotations.MockoClient;

/**
 * 测试用mock接口
 *
 * @author rui.zhang
 */
@MockoClient
public interface IFooService {


    /**
     * 测试方法
     *
     * @param name 名称
     * @return any value
     */
    String bar(String name);

}
