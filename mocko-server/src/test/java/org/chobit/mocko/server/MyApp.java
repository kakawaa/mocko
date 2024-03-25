package org.chobit.mocko.server;


import org.chobit.commons.codec.MD5;

public class MyApp {

    public static void main(String[] args) {
        String salt = "MayTheForceBeWithYou!";
        String pwd = "admin";
        String result = MD5.encode(pwd + salt);

        System.out.println(result);
    }


}
