package org.chobit.mocko.helper;

import org.chobit.mocko.model.entity.User;

/**
 * 用户登录信息及权限信息上下文
 *
 * @author rui.zhang
 */
public final class AuthContext {


    /**
     * username线程缓存
     */
    private static final ThreadLocal<String> T_LOCAL_USERNAME = new InheritableThreadLocal<>();


    /**
     * user信息线程缓存
     */
    private static final ThreadLocal<User> T_LOCAL_USER = new InheritableThreadLocal<>();


    /**
     * 将用户名保存到线程缓存
     *
     * @param username 用户名
     */
    public static void addUsername(String username) {
        T_LOCAL_USERNAME.set(username);
    }

    /**
     * 将用户信息保存到线程缓存
     *
     * @param user 用户信息
     */
    public static void addUser(User user) {
        T_LOCAL_USER.set(user);
    }


    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return T_LOCAL_USERNAME.get();
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public static User getUser() {
        return T_LOCAL_USER.get();
    }


    /**
     * 清理当前线程的缓存
     */
    public static void clear() {
        T_LOCAL_USERNAME.remove();
        T_LOCAL_USER.remove();
    }


    private AuthContext() {
        throw new UnsupportedOperationException("private constructor, cannot be accessed!");
    }
}
