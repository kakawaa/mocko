package org.chobit.mocko.client;

import org.chobit.mocko.Mocko;
import org.chobit.mocko.Target;

public interface Targeter {


    <T> T target(MockoClientFactoryBean factory,
                 Mocko.Builder mocko,
                 MockoContext context,
                 Target.DefaultTarget<T> target);


    class DefaultTargeter implements Targeter {

        @Override
        public <T> T target(MockoClientFactoryBean factory,
                            Mocko.Builder mocko,
                            MockoContext context,
                            Target.DefaultTarget<T> target) {

            return mocko.build().newInstance(target);
        }
    }

}
