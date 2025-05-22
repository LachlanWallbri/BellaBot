package org.objenesis.strategy;

import java.io.Serializable;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.Android10Instantiator;
import org.objenesis.instantiator.android.Android17Instantiator;
import org.objenesis.instantiator.android.Android18Instantiator;
import org.objenesis.instantiator.basic.AccessibleInstantiator;
import org.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.objenesis.instantiator.gcj.GCJInstantiator;
import org.objenesis.instantiator.perc.PercInstantiator;
import org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.objenesis.instantiator.sun.UnsafeFactoryInstantiator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class StdInstantiatorStrategy extends BaseInstantiatorStrategy {
    @Override // org.objenesis.strategy.InstantiatorStrategy
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        if (PlatformDescription.isThisJVM("Java HotSpot") || PlatformDescription.isThisJVM(PlatformDescription.OPENJDK)) {
            if (PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7")) {
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new ObjectInputStreamInstantiator(cls);
                }
                return new AccessibleInstantiator(cls);
            }
            return new SunReflectionFactoryInstantiator(cls);
        }
        if (PlatformDescription.isThisJVM(PlatformDescription.DALVIK)) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new UnsafeFactoryInstantiator(cls);
            }
            if (PlatformDescription.ANDROID_VERSION <= 10) {
                return new Android10Instantiator(cls);
            }
            if (PlatformDescription.ANDROID_VERSION <= 17) {
                return new Android17Instantiator(cls);
            }
            return new Android18Instantiator(cls);
        }
        if (PlatformDescription.isThisJVM(PlatformDescription.GNU)) {
            return new GCJInstantiator(cls);
        }
        if (PlatformDescription.isThisJVM(PlatformDescription.PERC)) {
            return new PercInstantiator(cls);
        }
        return new UnsafeFactoryInstantiator(cls);
    }
}
