package org.chobit.mocko.server.model.request;

import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;

@Data
public class MethodPageRequest extends BasePageRequest {


    private static final long serialVersionUID = 7044770722265779991L;


    /**
     * 应用ID
     */
    private String appId;


    /**
     * 关键字
     */
    private String keyword;
}
