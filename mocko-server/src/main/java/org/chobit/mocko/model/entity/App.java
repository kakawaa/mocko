package org.chobit.mocko.model.entity;

import lombok.Data;

/**
 * 应用
 *
 * @author rui.zhang
 */
@Data
public class App extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 应用名称
     */
    private String appName;

}
