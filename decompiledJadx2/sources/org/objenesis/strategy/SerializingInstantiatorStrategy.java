package org.objenesis.strategy;

import java.io.NotSerializableException;
import java.io.Serializable;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.AndroidSerializationInstantiator;
import org.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import org.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import org.objenesis.instantiator.perc.PercSerializationInstantiator;
import org.objenesis.instantiator.sun.SunReflectionFactorySerializationInstantiator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class SerializingInstantiatorStrategy extends BaseInstantiatorStrategy {
    @Override // org.objenesis.strategy.InstantiatorStrategy
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        if (!Serializable.class.isAssignableFrom(cls)) {
            throw new ObjenesisException(new NotSerializableException(cls + " not serializable"));
        }
        if (PlatformDescription.JVM_NAME.startsWith("Java HotSpot") || PlatformDescription.isThisJVM(PlatformDescription.OPENJDK)) {
            if (PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7")) {
                return new ObjectInputStreamInstantiator(cls);
            }
            return new SunReflectionFactorySerializationInstantiator(cls);
        }
        if (PlatformDescription.JVM_NAME.startsWith(PlatformDescription.DALVIK)) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new ObjectStreamClassInstantiator(cls);
            }
            return new AndroidSerializationInstantiator(cls);
        }
        if (PlatformDescription.JVM_NAME.startsWith(PlatformDescription.GNU)) {
            return new GCJSerializationInstantiator(cls);
        }
        if (PlatformDescription.JVM_NAME.startsWith(PlatformDescription.PERC)) {
            return new PercSerializationInstantiator(cls);
        }
        return new SunReflectionFactorySerializationInstantiator(cls);
    }
}
