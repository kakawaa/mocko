package org.chobit.mocko;


import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

    public static void main(String[] args) {
        String jarName = getCurrentRunningJarFileName();
        System.out.println("当前运行的Jar包名称为：" + jarName);
    }

    private static String getCurrentRunningJarFileName() {
        try {
            // 获取当前类的ClassLoader对象
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            if (loader instanceof URLClassLoader) {
                // 将ClassLoader转换成URLClassLoader
                URLClassLoader urlClassLoader = (URLClassLoader) loader;

                // 获取加载器的路径数组
                URL[] urls = urlClassLoader.getURLs();

                for (int i = 0; i < urls.length; i++) {
                    // 判断路径是否以file开头并且以.jar结尾
                    if (urls[i].toString().startsWith("file") && urls[i].toString().endsWith(".jar")) {
                        // 提取Jar文件名
                        return urls[i].toURI().getPath().substring(1).replace("/", "\\");
                    }
                }
            } else {
                throw new RuntimeException("无法获取当前运行的Jar包名称！");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}