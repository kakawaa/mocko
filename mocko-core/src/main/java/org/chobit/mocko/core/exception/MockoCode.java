package org.chobit.mocko.core.exception;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * Mocko异常编码
 *
 * @author robinF
 */
public enum MockoCode implements CodeDescEnum {

    /**
     * 请求Mocko Server 出错
     */
    REQUEST_MOCKO_SERVER_ERROR(101, "请求MockoServer错误"),
    ;


    public final int code;

    public final String msg;


    MockoCode(int code, String msg) {
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
