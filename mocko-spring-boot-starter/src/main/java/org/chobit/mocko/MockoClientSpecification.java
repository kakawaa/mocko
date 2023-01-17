package org.chobit.mocko;

import org.springframework.cloud.context.named.NamedContextFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author rui.zhang
 */
public class MockoClientSpecification implements NamedContextFactory.Specification {


    private String name;


    private Class<?>[] configuration;


    public MockoClientSpecification() {
    }

    public MockoClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    @Override
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
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
}
