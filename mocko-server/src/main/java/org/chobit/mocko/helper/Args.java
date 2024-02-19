package org.chobit.mocko.helper;

import org.chobit.mocko.constants.ResponseCode;
import org.chobit.mocko.except.MockoServerException;

import java.util.Objects;

import static java.lang.String.format;

/**
 * 参数校验工具类
 *
 * @author rui.zhang
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
