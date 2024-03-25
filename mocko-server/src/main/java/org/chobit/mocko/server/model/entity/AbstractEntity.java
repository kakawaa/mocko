package org.chobit.mocko.server.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * entity基础类
 *
 * @author rui.zhang
 */
@Data
abstract class AbstractEntity {


    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 操作人code
     */
    private String operatorCode;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
