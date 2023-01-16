package org.chobit.mocko.core.exceptions;


/**
 * 异常类
 *
 * @author rui.zhang
 */
public class MockoException extends RuntimeException {


    public MockoException() {
        super();
    }

    public MockoException(String msg) {
        super(msg);
    }


    public MockoException(Throwable t) {
        super(t);
    }


    public MockoException(String msg, Throwable t) {
        super(msg, t);
    }

}
