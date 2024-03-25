package org.chobit.mocko.core.exception;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * Mocko相关异常定义
 *
 * @author rui.zhang
 */
public class MockoException extends RuntimeException {

    private static final long serialVersionUID = -7220237856322209094L;
    
    private final int status;

    public MockoException(CodeDescEnum codeDesc) {
        super(codeDesc.getDesc());
        this.status = codeDesc.getCode();
    }


    public int getStatus() {
        return status;
    }
}
