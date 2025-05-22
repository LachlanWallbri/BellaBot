package io.netty.util.internal.shaded.org.jctools.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class UnsafeAccess {
    public static final boolean SUPPORTS_GET_AND_SET;
    public static final Unsafe UNSAFE;

    static {
        Unsafe unsafe;
        boolean z = true;
        try {
            try {
                Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                unsafe = (Unsafe) declaredField.get(null);
            } catch (Exception unused) {
                Constructor declaredConstructor = Unsafe.class.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                unsafe = (Unsafe) declaredConstructor.newInstance(new Object[0]);
            }
            try {
                Unsafe.class.getMethod("getAndSetObject", Object.class, Long.TYPE, Object.class);
            } catch (Exception unused2) {
                z = false;
            }
            UNSAFE = unsafe;
            SUPPORTS_GET_AND_SET = z;
        } catch (Exception e) {
            SUPPORTS_GET_AND_SET = false;
            throw new RuntimeException(e);
        }
    }
}
