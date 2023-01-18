package org.chobit.mocko.core.utils;

import org.junit.Test;

public class ArgsTest {


    @Test
    public void checkNotNull(){
        Args.checkNotNull(null, "type");
    }


}
