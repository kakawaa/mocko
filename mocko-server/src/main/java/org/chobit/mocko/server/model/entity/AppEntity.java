package org.chobit.mocko.server.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 应用
 *
 * @author robin
 */
@TableName("m_app")
@Data
public final class AppEntity extends AbstractEntity {


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 应用名称
     */
    private String appName;

}
