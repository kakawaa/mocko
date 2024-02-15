package org.chobit.mocko.client;

import org.chobit.mocko.MockoProvider;
import org.chobit.mocko.Target;

public interface Targeter {


    <T> T target(MockoClientFactoryBean factory,
                 MockoProvider.Builder mocko,
                 MockoContext context,
                 Target.DefaultTarget<T> target);


    class DefaultTargeter implements Targeter {

        @Override
        public <T> T target(MockoClientFactoryBean factory,
                            MockoProvider.Builder mocko,
                            MockoContext context,
                            Target.DefaultTarget<T> target) {

            return mocko.build().newInstance(target);
        }
    }

}
