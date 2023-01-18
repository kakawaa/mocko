package org.chobit.mocko.core.utils;

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
    public static <T> T checkNotNull(T reference,
                                     String errorMessageTemplate,
                                     Object... errorMessageArgs) {
        if (null == reference) {
            // If either of these parameters is null, the right thing happens anyway
            throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }


    private Args() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
