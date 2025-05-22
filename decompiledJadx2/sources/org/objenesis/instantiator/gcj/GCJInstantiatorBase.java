package org.objenesis.instantiator.gcj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class GCJInstantiatorBase<T> implements ObjectInstantiator<T> {
    static ObjectInputStream dummyStream;
    static Method newObjectMethod;
    protected final Class<T> type;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class DummyStream extends ObjectInputStream {
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public abstract T newInstance();

    private static void initialize() {
        if (newObjectMethod == null) {
            try {
                newObjectMethod = ObjectInputStream.class.getDeclaredMethod("newObject", Class.class, Class.class);
                newObjectMethod.setAccessible(true);
                dummyStream = new DummyStream();
            } catch (IOException | NoSuchMethodException | RuntimeException e) {
                throw new ObjenesisException(e);
            }
        }
    }

    public GCJInstantiatorBase(Class<T> cls) {
        this.type = cls;
        initialize();
    }
}
