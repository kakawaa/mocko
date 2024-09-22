package org.chobit.mocko.server.tools;

import org.chobit.mocko.server.api.PingController;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class ClassTreeBuilderTest {


    @Test
    public void build() {
        List<String> classTypeList = new LinkedList<>();
        classTypeList.add(Args.class.getName());
        classTypeList.add(AuthContext.class.getName());
        classTypeList.add(PingController.class.getName());

    }
}
