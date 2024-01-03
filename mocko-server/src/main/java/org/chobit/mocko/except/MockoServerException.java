package org.chobit.mocko.except;

import lombok.Getter;
import org.chobit.mocko.constants.MockoServerCode;

/**
 * Mocko服务端异常
 *
 * @author rui.zhang
 */
@Getter
public class MockoServerException extends RuntimeException {


    private static final long serialVersionUID = 7773203247174587636L;


    private int code;

    public MockoServerException(MockoServerCode code) {
        super(code.msg);
        this.code = code.code;
    }


}
