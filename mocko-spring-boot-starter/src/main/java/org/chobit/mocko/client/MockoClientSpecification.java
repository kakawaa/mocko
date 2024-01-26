package org.chobit.mocko.client;


import java.util.Arrays;
import java.util.Objects;

/**
 * @author rui.zhang
 */
public class MockoClientSpecification {


    private String name;


    private Class<?>[] configuration;


    public MockoClientSpecification() {
    }

    public MockoClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Class<?>[] getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(Class<?>[] configuration) {
        this.configuration = configuration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MockoClientSpecification that = (MockoClientSpecification) o;
        return Objects.equals(name, that.name) && Arrays.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(configuration);
        return result;
    }


    @Override
    public String toString() {
        return "MockoClientSpecification{" + "name='" +
                this.name + "', " + "configuration=" +
                Arrays.toString(this.configuration) + "}";
    }
}
