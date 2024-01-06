package org.chobit.mocko.except;

import lombok.Getter;
import org.chobit.mocko.constants.ResponseCode;

/**
 * Mocko服务端异常
 *
 * @author rui.zhang
 */
@Getter
public class MockoServerException extends RuntimeException {


    private static final long serialVersionUID = 7773203247174587636L;


    private final int code;

    public MockoServerException(ResponseCode code) {
        super(code.msg);
        this.code = code.code;
    }


}
