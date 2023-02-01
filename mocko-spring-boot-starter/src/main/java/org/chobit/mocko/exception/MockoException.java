package org.chobit.mocko.exception;

/**
 * Mocko相关异常定义
 *
 * @author rui.zhang
 */
public class MockoException extends RuntimeException {

    private final int status;

    public MockoException(int status, String message) {
        super(message);
        this.status = status;
    }


    public int getStatus() {
        return status;
    }
}
