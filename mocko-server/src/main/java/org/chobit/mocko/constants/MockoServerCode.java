package org.chobit.mocko.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 响应编码
 *
 * @author rui.zhang
 */

public enum MockoServerCode implements CodeDescEnum {


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

    /**
     * 方法不存在
     */
    METHOD_NOT_EXISTS(20000, "方法不存在"),
    ;


    public final int code;

    public final String msg;


    MockoServerCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String getDesc() {
        return msg;
    }


    @Override
    public int getCode() {
        return 0;
    }
}
