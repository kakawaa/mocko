package org.chobit.mocko.server.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 响应编码
 * <p>
 * 1xxxx api
 * 2xxxx mock
 * 3xxxx 权限
 * 4xxxx 业务
 *
 * @author robin
 */
public enum ResponseCode implements CodeDescEnum {


	/**
	 * 暂停
	 */
	SUSPEND(99, "暂停"),


	/**
	 * 用户已登出
	 */
	USER_AUTH_ERROR(100, "用户已登出"),


	/**
	 * 未定义错误
	 */
	ERROR(1000, "未定义错误"),


	/**
	 * 未定义错误
	 */
	ARGUMENT_ERROR(10000, "参数错误错误"),


	/**
	 * 方法不存在
	 */
	METHOD_NOT_EXISTS(20000, "方法不存在"),


	/**
	 * 方法响应信息为空
	 */
	EMPTY_MOCK_RESPONSE(20001, "方法响应信息为空"),


	/**
	 * 没有有效的方法响应信息
	 */
	NONE_VALID_MOCK_RESPONSE(20002, "没有有效的方法响应信息"),


	/**
	 * 方法响应信息无效
	 */
	ILLEGAL_MOCK_RESPONSE(20003, "方法响应信息无效"),


	/**
	 * 用户名或密码错误
	 */
	USER_LOGIN_INFO_ERROR(30000, "用户名或密码错误"),


	/**
	 * 确认密码与密码不一致
	 */
	CONFIRM_PASSWORD_NOT_MATCH(30001, "确认密码与密码不一致"),
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
