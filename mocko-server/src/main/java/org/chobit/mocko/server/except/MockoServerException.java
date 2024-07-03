package org.chobit.mocko.server.except;

import lombok.Getter;
import org.chobit.mocko.server.constants.ResponseCode;
import org.chobit.spring.autoconfigure.rw.exception.RwException;

/**
 * Mocko服务端异常
 *
 * @author robin
 */
@Getter
public class MockoServerException extends RwException {


	private static final long serialVersionUID = 7773203247174587636L;


	public MockoServerException(ResponseCode code) {
		super(code.code, code.msg);
	}

	public MockoServerException(ResponseCode code, Throwable t) {
		super(code.code, code.msg, t);
	}


}
