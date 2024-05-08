package org.chobit.mocko.core;


import java.util.Objects;

import static org.chobit.mocko.core.tools.Args.checkNotNull;

/**
 * @author robin
 */
public interface Target {


    /**
     * 返回target对应的类
     *
     * @return target 对应的类
     */
    Class<?> type();


    /**
     * Target的名称
     *
     * @return 名称
     */
    String name();


    class DefaultTarget implements Target {


        private final Class<?> type;

        private final String name;


        public DefaultTarget(Class<?> type) {
            this(type, type.getName());
        }


        public DefaultTarget(Class<?> type, String name) {
            this.type = checkNotNull(type, "type");
            this.name = checkNotNull(name, "name");
        }


        @Override
        public Class<?> type() {
            return this.type;
        }

        @Override
        public String name() {
            return this.name;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DefaultTarget that = (DefaultTarget) o;
            return Objects.equals(type, that.type) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, name);
        }


        @Override
        public String toString() {
            if (Objects.equals(type.getName(), name)) {
                return "DefaultTarget{type=" + type.getSimpleName() + '}';
            }
            return "DefaultTarget{type=" + type.getSimpleName() + ", name='" + name + '}';
        }
    }
}
