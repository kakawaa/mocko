package org.chobit.mocko.tools;


import org.junit.jupiter.api.Test;

public class ArgsTest {


    @Test
    public void checkNotNull(){
        Args.checkNotNull(null, "type");
    }


}
