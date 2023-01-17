package org.chobit.mocko.constants;

/**
 * 响应编码
 *
 * @author rui.zhang
 */

public enum ResponseCode {


    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 暂停
     */
    SUSPEND(99, "暂停"),

    /**
     * 未定义错误
     */
    ERROR(1000, "未定义错误"),
    ;


    public final int code;

    public final String msg;


    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
