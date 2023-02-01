package org.chobit.mocko.exception;

/**
 * 解码相关异常
 *
 * @author rui.zhang
 */
public class DecodeException extends MockoException {


    private static final long serialVersionUID = -5899000085096218684L;

    public DecodeException(int status, String message) {
        super(status, message);
    }
}
