package org.objenesis.instantiator.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.security.ProtectionDomain;
import org.objenesis.ObjenesisException;
import org.objenesis.strategy.PlatformDescription;
import sun.misc.Unsafe;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class DefineClassHelper {
    private static final Helper privileged;

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static abstract class Helper {
        abstract Class<?> defineClass(String str, byte[] bArr, int i, int i2, Class<?> cls, ClassLoader classLoader, ProtectionDomain protectionDomain);

        private Helper() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class Java8 extends Helper {
        private final MethodHandle defineClass;

        private Java8() {
            super();
            this.defineClass = defineClass();
        }

        private MethodHandle defineClass() {
            try {
                return MethodHandles.publicLookup().findVirtual(Unsafe.class, "defineClass", MethodType.methodType(Class.class, String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class)).bindTo(UnsafeUtils.getUnsafe());
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new ObjenesisException(e);
            }
        }

        @Override // org.objenesis.instantiator.util.DefineClassHelper.Helper
        Class<?> defineClass(String str, byte[] bArr, int i, int i2, Class<?> cls, ClassLoader classLoader, ProtectionDomain protectionDomain) {
            try {
                return (Class) this.defineClass.invokeExact(str, bArr, i, i2, classLoader, protectionDomain);
            } catch (Throwable th) {
                if (th instanceof Error) {
                    throw ((Error) th);
                }
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                }
                throw new ObjenesisException(th);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class Java11 extends Helper {
        private final MethodHandle addReads;
        private final MethodHandle defineClass;
        private final MethodHandle getModule;
        private final MethodHandles.Lookup lookup;
        private final Class<?> module;
        private final MethodHandle privateLookupIn;

        private Java11() {
            super();
            this.module = module();
            this.lookup = MethodHandles.lookup();
            this.getModule = getModule();
            this.addReads = addReads();
            this.privateLookupIn = privateLookupIn();
            this.defineClass = defineClass();
        }

        private Class<?> module() {
            try {
                return Class.forName("java.lang.Module");
            } catch (ClassNotFoundException e) {
                throw new ObjenesisException(e);
            }
        }

        private MethodHandle getModule() {
            try {
                return this.lookup.findVirtual(Class.class, "getModule", MethodType.methodType(this.module));
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new ObjenesisException(e);
            }
        }

        private MethodHandle addReads() {
            try {
                return this.lookup.findVirtual(this.module, "addReads", MethodType.methodType(this.module, this.module));
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new ObjenesisException(e);
            }
        }

        private MethodHandle privateLookupIn() {
            try {
                return this.lookup.findStatic(MethodHandles.class, "privateLookupIn", MethodType.methodType(MethodHandles.Lookup.class, Class.class, MethodHandles.Lookup.class));
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new ObjenesisException(e);
            }
        }

        private MethodHandle defineClass() {
            try {
                return this.lookup.findVirtual(MethodHandles.Lookup.class, "defineClass", MethodType.methodType((Class<?>) Class.class, (Class<?>) byte[].class));
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new ObjenesisException(e);
            }
        }

        @Override // org.objenesis.instantiator.util.DefineClassHelper.Helper
        Class<?> defineClass(String str, byte[] bArr, int i, int i2, Class<?> cls, ClassLoader classLoader, ProtectionDomain protectionDomain) {
            try {
                this.addReads.invokeWithArguments(this.getModule.invokeWithArguments(DefineClassHelper.class), this.getModule.invokeWithArguments(cls));
                return (Class) this.defineClass.invokeExact((MethodHandles.Lookup) this.privateLookupIn.invokeExact(cls, this.lookup), bArr);
            } catch (Throwable th) {
                throw new ObjenesisException(cls.getName() + " has no permission to define the class", th);
            }
        }
    }

    static {
        privileged = PlatformDescription.isAfterJava11() ? new Java11() : new Java8();
    }

    public static Class<?> defineClass(String str, byte[] bArr, int i, int i2, Class<?> cls, ClassLoader classLoader, ProtectionDomain protectionDomain) {
        return privileged.defineClass(str, bArr, i, i2, cls, classLoader, protectionDomain);
    }

    private DefineClassHelper() {
    }
}
