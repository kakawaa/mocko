package org.chobit.mocko.autoconfigure;


import org.chobit.commons.utils.JsonKit;
import org.chobit.mocko.core.model.MethodMeta;

public class Test {

    public static void main(String[] args) {
        String json = "{\"appId\":\"mocko-server\",\"className\":\"org.chobit.mocko.server.biz.MyBiz\",\"methodName\":\"toString\",\"returnType\":\"java.lang.String\"}";
        MethodMeta meta = JsonKit.fromJson(json, MethodMeta.class);
        System.out.println(meta);
    }
}