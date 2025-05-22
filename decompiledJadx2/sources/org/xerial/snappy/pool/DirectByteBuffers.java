package org.xerial.snappy.pool;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class DirectByteBuffers {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final MethodHandle CLEAN_HANDLE;
    static final Class<? extends ByteBuffer> DIRECT_BUFFER_CLAZZ = lookupClassQuietly("java.nio.DirectByteBuffer");

    static boolean nonNull(Object obj) {
        return obj != null;
    }

    DirectByteBuffers() {
    }

    static {
        MethodHandle methodHandle = null;
        try {
            methodHandle = (MethodHandle) AccessController.doPrivileged(new PrivilegedExceptionAction<MethodHandle>() { // from class: org.xerial.snappy.pool.DirectByteBuffers.1
                @Override // java.security.PrivilegedExceptionAction
                public MethodHandle run() throws Exception {
                    if (DirectByteBuffers.DIRECT_BUFFER_CLAZZ == null) {
                        return null;
                    }
                    MethodHandles.Lookup lookup = MethodHandles.lookup();
                    try {
                        Class<?> cls = Class.forName("sun.misc.Unsafe");
                        MethodHandle findVirtual = lookup.findVirtual(cls, "invokeCleaner", MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                        Field declaredField = cls.getDeclaredField("theUnsafe");
                        declaredField.setAccessible(true);
                        return findVirtual.bindTo(declaredField.get(null));
                    } catch (Exception e) {
                        Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "unable to use java 9 Unsafe.invokeCleaner", (Throwable) e);
                        Method method = DirectByteBuffers.DIRECT_BUFFER_CLAZZ.getMethod("cleaner", new Class[0]);
                        method.setAccessible(true);
                        MethodHandle unreflect = lookup.unreflect(method);
                        Class<?> returnType = unreflect.type().returnType();
                        return MethodHandles.filterReturnValue(unreflect, MethodHandles.guardWithTest(lookup.findStatic(DirectByteBuffers.class, "nonNull", MethodType.methodType((Class<?>) Boolean.TYPE, (Class<?>) Object.class)).asType(MethodType.methodType((Class<?>) Boolean.TYPE, returnType)), lookup.findVirtual(returnType, "clean", MethodType.methodType(Void.TYPE)), MethodHandles.dropArguments(MethodHandles.constant(Void.class, null).asType(MethodType.methodType(Void.TYPE)), 0, (Class<?>[]) new Class[]{returnType}))).asType(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                    }
                }
            });
        } catch (Throwable th) {
            Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Exception occurred attempting to lookup Sun specific DirectByteBuffer cleaner classes.", th);
        }
        CLEAN_HANDLE = methodHandle;
    }

    private static Class<?> lookupClassQuietly(String str) {
        try {
            return DirectByteBuffers.class.getClassLoader().loadClass(str);
        } catch (Throwable th) {
            Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Did not find requested class: " + str, th);
            return null;
        }
    }

    public static void releaseDirectByteBuffer(final ByteBuffer byteBuffer) {
        if (CLEAN_HANDLE == null || !DIRECT_BUFFER_CLAZZ.isInstance(byteBuffer)) {
            return;
        }
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() { // from class: org.xerial.snappy.pool.DirectByteBuffers.2
                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws Exception {
                    try {
                        (void) DirectByteBuffers.CLEAN_HANDLE.invokeExact(byteBuffer);
                        return null;
                    } catch (Exception e) {
                        throw e;
                    } catch (Throwable th) {
                        throw new RuntimeException(th);
                    }
                }
            });
        } catch (Throwable th) {
            Logger.getLogger(DirectByteBuffers.class.getName()).log(Level.FINE, "Exception occurred attempting to clean up Sun specific DirectByteBuffer.", th);
        }
    }
}
