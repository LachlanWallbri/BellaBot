package org.objenesis;

import java.io.Serializable;
import org.objenesis.instantiator.ObjectInstantiator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ObjenesisHelper {
    private static final Objenesis OBJENESIS_STD = new ObjenesisStd();
    private static final Objenesis OBJENESIS_SERIALIZER = new ObjenesisSerializer();

    private ObjenesisHelper() {
    }

    public static <T> T newInstance(Class<T> cls) {
        return (T) OBJENESIS_STD.newInstance(cls);
    }

    public static <T extends Serializable> T newSerializableInstance(Class<T> cls) {
        return (T) OBJENESIS_SERIALIZER.newInstance(cls);
    }

    public static <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls) {
        return OBJENESIS_STD.getInstantiatorOf(cls);
    }

    public static <T extends Serializable> ObjectInstantiator<T> getSerializableObjectInstantiatorOf(Class<T> cls) {
        return OBJENESIS_SERIALIZER.getInstantiatorOf(cls);
    }
}
