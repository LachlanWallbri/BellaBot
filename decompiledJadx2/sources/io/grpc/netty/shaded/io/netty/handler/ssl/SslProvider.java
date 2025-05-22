package io.grpc.netty.shaded.io.netty.handler.ssl;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SslProvider {
    JDK,
    OPENSSL,
    OPENSSL_REFCNT;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.ssl.SslProvider$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C66671 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$SslProvider = new int[SslProvider.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.JDK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.OPENSSL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.OPENSSL_REFCNT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static boolean isAlpnSupported(SslProvider sslProvider) {
        int i = C66671.$SwitchMap$io$netty$handler$ssl$SslProvider[sslProvider.ordinal()];
        if (i == 1) {
            return JdkAlpnApplicationProtocolNegotiator.isAlpnSupported();
        }
        if (i == 2 || i == 3) {
            return OpenSsl.isAlpnSupported();
        }
        throw new Error("Unknown SslProvider: " + sslProvider);
    }

    public static boolean isTlsv13Supported(SslProvider sslProvider) {
        int i = C66671.$SwitchMap$io$netty$handler$ssl$SslProvider[sslProvider.ordinal()];
        if (i == 1) {
            return SslUtils.isTLSv13SupportedByJDK();
        }
        if (i == 2 || i == 3) {
            return OpenSsl.isTlsv13Supported();
        }
        throw new Error("Unknown SslProvider: " + sslProvider);
    }

    static boolean isTlsv13EnabledByDefault(SslProvider sslProvider) {
        int i = C66671.$SwitchMap$io$netty$handler$ssl$SslProvider[sslProvider.ordinal()];
        if (i == 1) {
            return SslUtils.isTLSv13EnabledByJDK();
        }
        if (i == 2 || i == 3) {
            return OpenSsl.isTlsv13Supported();
        }
        throw new Error("Unknown SslProvider: " + sslProvider);
    }
}
