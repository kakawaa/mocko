package org.chobit.mocko.core.contants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 响应编码
 *
 * @author rui.zhang
 */

public enum ResponseCode implements CodeDescEnum {

    /**
     * 请求Mocko Server 出错
     */
    REQUEST_MOCKO_SERVER_ERROR(101, "请求MockoServer错误"),
    ;


    public final int code;

    public final String msg;


    ResponseCode(int code, String msg) {
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
