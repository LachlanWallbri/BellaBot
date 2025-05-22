package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class SecureCaller {
    private static final byte[] secureCallerImplBytecode = loadBytecode();
    private static final Map<CodeSource, Map<ClassLoader, SoftReference<SecureCaller>>> callers = new WeakHashMap();

    public abstract Object call(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);

    static /* synthetic */ byte[] access$100() {
        return loadBytecodePrivileged();
    }

    static Object callSecurely(final CodeSource codeSource, Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Map<ClassLoader, SoftReference<SecureCaller>> map;
        SecureCaller secureCaller;
        final Thread currentThread = Thread.currentThread();
        final ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: org.mozilla.javascript.SecureCaller.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return currentThread.getContextClassLoader();
            }
        });
        synchronized (callers) {
            map = callers.get(codeSource);
            if (map == null) {
                map = new WeakHashMap<>();
                callers.put(codeSource, map);
            }
        }
        synchronized (map) {
            SoftReference<SecureCaller> softReference = map.get(classLoader);
            SecureCaller secureCaller2 = softReference != null ? softReference.get() : null;
            if (secureCaller2 == null) {
                try {
                    secureCaller2 = (SecureCaller) AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() { // from class: org.mozilla.javascript.SecureCaller.2
                        @Override // java.security.PrivilegedExceptionAction
                        public Object run() throws Exception {
                            ClassLoader classLoader2;
                            Class<?> cls = getClass();
                            if (classLoader.loadClass(cls.getName()) != cls) {
                                classLoader2 = cls.getClassLoader();
                            } else {
                                classLoader2 = classLoader;
                            }
                            return new SecureClassLoaderImpl(classLoader2).defineAndLinkClass(SecureCaller.class.getName() + "Impl", SecureCaller.secureCallerImplBytecode, codeSource).newInstance();
                        }
                    });
                    map.put(classLoader, new SoftReference<>(secureCaller2));
                } catch (PrivilegedActionException e) {
                    throw new UndeclaredThrowableException(e.getCause());
                }
            }
            secureCaller = secureCaller2;
        }
        return secureCaller.call(callable, context, scriptable, scriptable2, objArr);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    private static class SecureClassLoaderImpl extends SecureClassLoader {
        SecureClassLoaderImpl(ClassLoader classLoader) {
            super(classLoader);
        }

        Class<?> defineAndLinkClass(String str, byte[] bArr, CodeSource codeSource) {
            Class<?> defineClass = defineClass(str, bArr, 0, bArr.length, codeSource);
            resolveClass(defineClass);
            return defineClass;
        }
    }

    private static byte[] loadBytecode() {
        return (byte[]) AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: org.mozilla.javascript.SecureCaller.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return SecureCaller.access$100();
            }
        });
    }

    private static byte[] loadBytecodePrivileged() {
        try {
            InputStream openStream = SecureCaller.class.getResource("SecureCallerImpl.clazz").openStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = openStream.read();
                    if (read == -1) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byteArrayOutputStream.write(read);
                }
            } finally {
                openStream.close();
            }
        } catch (IOException e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
