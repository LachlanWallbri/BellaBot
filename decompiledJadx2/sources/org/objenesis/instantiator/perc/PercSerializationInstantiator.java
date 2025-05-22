package org.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
@Instantiator(Typology.SERIALIZATION)
/* loaded from: classes9.dex */
public class PercSerializationInstantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod;
    private Object[] typeArgs;

    public PercSerializationInstantiator(Class<T> cls) {
        Class<T> cls2 = cls;
        while (Serializable.class.isAssignableFrom(cls2)) {
            cls2 = cls2.getSuperclass();
        }
        try {
            this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("noArgConstruct", Class.class, Object.class, Class.forName("COM.newmonics.PercClassLoader.Method"));
            this.newInstanceMethod.setAccessible(true);
            Object invoke = Class.forName("COM.newmonics.PercClassLoader.PercClass").getDeclaredMethod("getPercClass", Class.class).invoke(null, cls2);
            this.typeArgs = new Object[]{cls2, cls, invoke.getClass().getDeclaredMethod("findMethod", String.class).invoke(invoke, "<init>()V")};
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) this.newInstanceMethod.invoke(null, this.typeArgs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ObjenesisException(e);
        }
    }
}
