package org.chobit.mocko;

import org.chobit.mocko.InvocationHandlerFactory.MethodHandler;

import java.lang.invoke.MethodHandles.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author rui.zhang
 */
public class DefaultMethodHandler implements MethodHandler {


    private final MethodHandle unboundHandle;

    private MethodHandle handle;


    public DefaultMethodHandler(Method defaultMethod) {
        Class<?> declaringClass = defaultMethod.getDeclaringClass();

        try {
            Lookup lookup = readLookup(declaringClass);
            this.unboundHandle = lookup.unreflectSpecial(defaultMethod, declaringClass);
        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException |
                 NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }


    private Lookup readLookup(Class<?> declaringClass) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        try {
            return safeReadLookup(declaringClass);
        } catch (NoSuchMethodException e) {
            return legacyReadLookup();
        }
    }


    private Lookup safeReadLookup(Class<?> declaringClass) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Lookup lookup = MethodHandles.lookup();

        Object privateLookup = MethodHandles.class.getMethod("privateLookupIn", Class.class, Lookup.class)
                .invoke(null, declaringClass, lookup);
        return (Lookup) privateLookup;
    }


    private Lookup legacyReadLookup() throws NoSuchFieldException, IllegalAccessException {
        Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
        field.setAccessible(true);
        return (Lookup) field.get(null);
    }


    public void bindTo(Object proxy) {
        if (null != handle) {
            throw new IllegalStateException("Attempted to rebind a default method handler that already bound");
        }
        handle = unboundHandle.bindTo(proxy);
    }


    @Override
    public Object invoke(Object[] args) throws Throwable {
        if (null == handle) {
            throw new IllegalStateException("Default method handler invoked before proxy has been bound.");
        }
        return handle.invokeWithArguments(args);
    }


}
