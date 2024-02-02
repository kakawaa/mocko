package org.chobit.mocko.client;


/**
 * @author rui.zhang
 */
public class MockoClientSpecification {


    private String name;



    public MockoClientSpecification() {
    }

    public MockoClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
