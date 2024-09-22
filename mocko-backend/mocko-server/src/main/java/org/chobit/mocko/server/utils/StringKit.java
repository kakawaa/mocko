package org.chobit.mocko.server.utils;

import static org.chobit.commons.constans.Symbol.EMPTY;

/**
 * 字符串工具类
 *
 * @author robin
 */
public final class StringKit {


	public static String nullOrEmpty(String str) {
		return null == str ? EMPTY : str;
	}

	private StringKit() {
		throw new UnsupportedOperationException();
	}
}
