package org.chobit.mocko;

import org.springframework.cloud.context.named.NamedContextFactory;

/**
 * @author rui.zhang
 */
public class MockoClientSpecification  implements NamedContextFactory.Specification {


    private String name;




    @Override
    public String getName() {
        return null;
    }

    @Override
    public Class<?>[] getConfiguration() {
        return new Class[0];
    }
}
