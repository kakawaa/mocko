package org.chobit.mocko.server.model.entity;

import lombok.Data;

/**
 * 应用
 *
 * @author robin
 */
@Data
public final class AppEntity extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 应用名称
     */
    private String appName;

}
