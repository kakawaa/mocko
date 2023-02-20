package org.chobit.mocko;


import org.chobit.mocko.tools.Args;

import java.util.Objects;

/**
 * @author rui.zhang
 */
public interface Target<T> {


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
            this.type = Args.checkNotNull(type, "type");
            this.name = Args.checkNotNull(name, "name");
            this.url = Args.checkNotNull(url, "url");
        }


        @Override
        public Class<T> type() {
            return null;
        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public String url() {
            return null;
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
