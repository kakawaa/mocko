package org.chobit.mocko.core.others;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ClassTest {


    @Test
    public void typeNames() {
        Type type = ClassTest.class;
        System.out.println(type.getTypeName());
    }


    @Test
    public void classNames() {
        Class<ClassTest> clazz = ClassTest.class;
        System.out.println(clazz.getName());
        System.out.println(clazz.getTypeName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getSimpleName());
    }


    @Test
    public void methodRelates() {
        Method[] methods = ClassTest.class.getMethods();
        for (Method m : methods) {
            Class<?> clazz = m.getDeclaringClass();
            if (clazz == Object.class) {
                continue;
            }
            System.out.println(clazz);
        }
    }


}
