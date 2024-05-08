package org.chobit.mocko.core;


/**
 * 方法触发函数接口
 *
 * @author robin
 */
@FunctionalInterface
public interface OperationInvoker {


    /**
     * 执行方法
     *
     * @return 执行结果
     * @throws WrappedThrowableException 异常信息
     */
    Object invoke() throws WrappedThrowableException;


    /**
     * Wrap any exception thrown while invoking {@link #invoke()}.
     */
    class WrappedThrowableException extends RuntimeException {

        private static final long serialVersionUID = -6473452644685184291L;
        
        private final Throwable original;

        public WrappedThrowableException(Throwable original) {
            super(original.getMessage(), original);
            this.original = original;
        }

        public Throwable getOriginal() {
            return this.original;
        }
    }


}
