package org.chobit.mocko;


import org.apache.ibatis.jdbc.SQL;

public class MyApp {

    public static void main(String[] args) {

        new SQL() {
        };

        new MyApp() {
            {
                f();
            }
        };
    }


    public int f() {
        return 1;
    }

}
