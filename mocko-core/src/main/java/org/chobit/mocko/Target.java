package org.chobit.mocko;


import java.util.Objects;

import static org.chobit.mocko.tools.Args.checkNotNull;

/**
 * @author rui.zhang
 */
interface Target<T> {


    /**
     * 返回target对应的类
     *
     * @return target 对应的类
     */
    Class<T> type();


    /**
     * Target的名称
     *
     * @return 名称
     */
    String name();


    /**
     * 请求路径
     *
     * @return 路径
     */
    String url();


    class DefaultTarget<T> implements Target<T> {


        private final Class<T> type;

        private final String name;

        private final String url;


        public DefaultTarget(Class<T> type, String url) {
            this(type, url, url);
        }


        public DefaultTarget(Class<T> type, String name, String url) {
            this.type = checkNotNull(type, "type");
            this.name = checkNotNull(name, "name");
            this.url = checkNotNull(url, "url");
        }


        @Override
        public Class<T> type() {
            return this.type;
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public String url() {
            return this.url;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DefaultTarget<?> that = (DefaultTarget<?>) o;
            return Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(url, that.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, name, url);
        }


        @Override
        public String toString() {
            if (url.equals(name)) {
                return "DefaultTarget{type=" + type.getSimpleName() + ", url='" + url + '}';
            }
            return "DefaultTarget{type=" + type.getSimpleName() + ", name='" + name + ", url='" + url + '}';
        }
    }
}
