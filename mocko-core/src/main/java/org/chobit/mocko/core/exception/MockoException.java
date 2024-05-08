package org.chobit.mocko.core.exception;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * Mocko相关异常定义
 *
 * @author robin
 */
public class MockoException extends RuntimeException {

    private static final long serialVersionUID = -7220237856322209094L;

    private final int status;

    public MockoException(CodeDescEnum codeDesc) {
        super(codeDesc.getDesc());
        this.status = codeDesc.getCode();
    }

    public MockoException(CodeDescEnum codeDesc, Throwable t) {
        super(codeDesc.getDesc(), t);
        this.status = codeDesc.getCode();
    }


    public int getStatus() {
        return status;
    }
}
