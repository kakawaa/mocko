package org.chobit.mocko.server.tools;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.chobit.mocko.server.model.response.item.UserItem;

import java.util.concurrent.TimeUnit;

/**
 * 用户登录信息及权限信息上下文
 *
 * @author robin
 */
public final class AuthContext {


	/**
	 * 用户信息缓存
	 */
	private static final Cache<String, UserItem> USER_CACHE =
			Caffeine.newBuilder().maximumSize(1024).expireAfterAccess(30, TimeUnit.MINUTES).build();


	/**
	 * user信息线程缓存
	 */
	private static final ThreadLocal<String> T_SESSION_ID = new InheritableThreadLocal<>();


	/**
	 * 将用户信息保存到线程缓存
	 *
	 * @param user 用户信息
	 */
	public static void addUser(UserItem user) {
		String sessionId = T_SESSION_ID.get();
		USER_CACHE.put(sessionId, user);
	}


	/**
	 * 获取用户信息
	 *
	 * @return 用户信息
	 */
	public static UserItem getUser() {
		String sessionId = T_SESSION_ID.get();
		return USER_CACHE.getIfPresent(sessionId);
	}


	/**
	 * 获取用户名
	 *
	 * @return 用户名
	 */
	public static String getUsername() {
		UserItem user = getUser();
		return null == user ? null : user.getUsername();
	}


	/**
	 * 获取当前的token信息
	 *
	 * @return token信息
	 */
	public static String getToken() {
		UserItem user = getUser();
		return null == user ? null : user.getToken();
	}


	/**
	 * 设置sessionId
	 *
	 * @param sessionId sessionId
	 */
	public static void setSessionId(String sessionId) {
		T_SESSION_ID.set(sessionId);
	}


	/**
	 * 移除sessionId
	 */
	public static void removeSessionId() {
		T_SESSION_ID.remove();
	}


	/**
	 * 清理当前线程的缓存
	 */
	public static void clear() {
		String sessionId = T_SESSION_ID.get();
		USER_CACHE.invalidate(sessionId);
		T_SESSION_ID.remove();
	}


	private AuthContext() {
		throw new UnsupportedOperationException("private constructor, cannot be accessed!");
	}
}
