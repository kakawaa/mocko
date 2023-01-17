package org.chobit.mocko.model;

/**
 * Mocko 响应
 *
 * @author rui.zhang
 */
public class MockoResponse<T> {

    /**
     * 编码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
