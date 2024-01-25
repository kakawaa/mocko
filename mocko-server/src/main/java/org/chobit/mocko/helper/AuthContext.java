package org.chobit.mocko.helper;

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
     * 将用户名保存到线程缓存
     *
     * @param username 用户名
     */
    public static void addUsername(String username) {
        T_LOCAL_USERNAME.set(username);
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
     * 清理当前线程的缓存
     */
    public static void clear() {
        T_LOCAL_USERNAME.remove();
    }


    private AuthContext() {
        throw new UnsupportedOperationException("private constructor, cannot be accessed!");
    }
}
