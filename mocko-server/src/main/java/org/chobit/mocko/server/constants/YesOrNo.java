package org.chobit.mocko.server.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 是否枚举
 *
 * @author robin
 */

public enum YesOrNo implements CodeDescEnum {

	/**
	 * 是
	 */
	YES(1, "是"),


	/**
	 * 否
	 */
	NO(0, "否"),

	;


	public final int code;

	public final String desc;


	YesOrNo(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public String getDesc() {
		return this.desc;
	}


	@Override
	public int getCode() {
		return this.code;
	}
}
