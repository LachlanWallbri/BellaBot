package org.conscrypt;

import java.lang.reflect.Method;
import javax.net.ssl.SSLParameters;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class Java9PlatformUtil {
    private static final Method SSL_PARAMETERS_GET_APPLICATION_PROTOCOLS_METHOD;
    private static final Method SSL_PARAMETERS_SET_APPLICATION_PROTOCOLS_METHOD;

    static {
        Method method;
        Method method2 = null;
        try {
            Method method3 = SSLParameters.class.getMethod("getApplicationProtocols", new Class[0]);
            method = SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
            method2 = method3;
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        SSL_PARAMETERS_GET_APPLICATION_PROTOCOLS_METHOD = method2;
        SSL_PARAMETERS_SET_APPLICATION_PROTOCOLS_METHOD = method;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        Java8PlatformUtil.setSSLParameters(sSLParameters, sSLParametersImpl, abstractConscryptSocket);
        sSLParametersImpl.setApplicationProtocols(getApplicationProtocols(sSLParameters));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        Java8PlatformUtil.getSSLParameters(sSLParameters, sSLParametersImpl, abstractConscryptSocket);
        setApplicationProtocols(sSLParameters, sSLParametersImpl.getApplicationProtocols());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        Java8PlatformUtil.setSSLParameters(sSLParameters, sSLParametersImpl, conscryptEngine);
        sSLParametersImpl.setApplicationProtocols(getApplicationProtocols(sSLParameters));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        Java8PlatformUtil.getSSLParameters(sSLParameters, sSLParametersImpl, conscryptEngine);
        setApplicationProtocols(sSLParameters, sSLParametersImpl.getApplicationProtocols());
    }

    private static String[] getApplicationProtocols(SSLParameters sSLParameters) {
        Method method = SSL_PARAMETERS_GET_APPLICATION_PROTOCOLS_METHOD;
        if (method != null) {
            try {
                return (String[]) method.invoke(sSLParameters, new Object[0]);
            } catch (ReflectiveOperationException unused) {
            }
        }
        return EmptyArray.STRING;
    }

    private static void setApplicationProtocols(SSLParameters sSLParameters, String[] strArr) {
        Method method = SSL_PARAMETERS_SET_APPLICATION_PROTOCOLS_METHOD;
        if (method != null) {
            try {
                method.invoke(sSLParameters, strArr);
            } catch (ReflectiveOperationException unused) {
            }
        }
    }

    private Java9PlatformUtil() {
    }
}
