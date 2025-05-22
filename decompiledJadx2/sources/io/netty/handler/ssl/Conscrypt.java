package io.netty.handler.ssl;

import io.netty.util.internal.PlatformDependent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.net.ssl.SSLEngine;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class Conscrypt {
    private static final Method IS_CONSCRYPT_SSLENGINE = loadIsConscryptEngine();

    private static Method loadIsConscryptEngine() {
        try {
            return Class.forName("org.conscrypt.Conscrypt", true, ConscryptAlpnSslEngine.class.getClassLoader()).getMethod("isConscrypt", SSLEngine.class);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAvailable() {
        return IS_CONSCRYPT_SSLENGINE != null && PlatformDependent.javaVersion() >= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEngineSupported(SSLEngine sSLEngine) {
        return isAvailable() && isConscryptEngine(sSLEngine);
    }

    private static boolean isConscryptEngine(SSLEngine sSLEngine) {
        try {
            return ((Boolean) IS_CONSCRYPT_SSLENGINE.invoke(null, sSLEngine)).booleanValue();
        } catch (IllegalAccessException unused) {
            return false;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Conscrypt() {
    }
}
