package org.chobit.mocko.server.tools;

import org.chobit.mocko.server.constants.ResponseCode;
import org.chobit.mocko.server.except.MockoServerException;

import java.util.Objects;

/**
 * 参数校验工具类
 *
 * @author robin
 */
public final class Args {


    /**
     * 校验参数是否为空，为空则抛出空指针异常
     */
    public static void checkEquals(String str,
                                   String other,
                                   ResponseCode responseCode) {
        if (!Objects.equals(str, other)) {
            throw new MockoServerException(responseCode);
        }
    }


    private Args() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
