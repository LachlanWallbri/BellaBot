package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.internal.ConscryptLoader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SecurityUtil;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.handler.ssl.OpenSsl;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslProvider;
import io.grpc.netty.shaded.io.netty.handler.ssl.SupportedCipherSuiteFilter;
import java.io.File;
import java.io.InputStream;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class GrpcSslContexts {
    private static final String HTTP2_VERSION = "h2";
    private static final String IBM_PROVIDER_NAME = "IBMJSSE2";
    private static final String OPENJSSE_PROVIDER_NAME = "OpenJSSE";
    private static final String SUN_PROVIDER_NAME = "SunJSSE";
    private static final Logger logger = Logger.getLogger(GrpcSslContexts.class.getName());
    private static final List<String> NEXT_PROTOCOL_VERSIONS = Collections.unmodifiableList(Arrays.asList("h2"));
    private static final ApplicationProtocolConfig ALPN = new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, (Iterable<String>) NEXT_PROTOCOL_VERSIONS);
    private static final ApplicationProtocolConfig NPN = new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.NPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, (Iterable<String>) NEXT_PROTOCOL_VERSIONS);
    private static final ApplicationProtocolConfig NPN_AND_ALPN = new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.NPN_AND_ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, (Iterable<String>) NEXT_PROTOCOL_VERSIONS);

    private GrpcSslContexts() {
    }

    public static SslContextBuilder forClient() {
        return configure(SslContextBuilder.forClient());
    }

    public static SslContextBuilder forServer(File file, File file2) {
        return configure(SslContextBuilder.forServer(file, file2));
    }

    public static SslContextBuilder forServer(File file, File file2, String str) {
        return configure(SslContextBuilder.forServer(file, file2, str));
    }

    public static SslContextBuilder forServer(InputStream inputStream, InputStream inputStream2) {
        return configure(SslContextBuilder.forServer(inputStream, inputStream2));
    }

    public static SslContextBuilder forServer(InputStream inputStream, InputStream inputStream2, String str) {
        return configure(SslContextBuilder.forServer(inputStream, inputStream2, str));
    }

    public static SslContextBuilder configure(SslContextBuilder sslContextBuilder) {
        return configure(sslContextBuilder, defaultSslProvider());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C62011 {
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
        }
    }

    public static SslContextBuilder configure(SslContextBuilder sslContextBuilder, SslProvider sslProvider) {
        ApplicationProtocolConfig applicationProtocolConfig;
        int i = C62011.$SwitchMap$io$netty$handler$ssl$SslProvider[sslProvider.ordinal()];
        if (i == 1) {
            Provider findJdkProvider = findJdkProvider();
            if (findJdkProvider == null) {
                throw new IllegalArgumentException("Could not find Jetty NPN/ALPN or Conscrypt as installed JDK providers");
            }
            return configure(sslContextBuilder, findJdkProvider);
        }
        if (i == 2) {
            if (OpenSsl.isAlpnSupported()) {
                applicationProtocolConfig = NPN_AND_ALPN;
            } else {
                applicationProtocolConfig = NPN;
            }
            return sslContextBuilder.sslProvider(SslProvider.OPENSSL).ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE).applicationProtocolConfig(applicationProtocolConfig);
        }
        throw new IllegalArgumentException("Unsupported provider: " + sslProvider);
    }

    public static SslContextBuilder configure(SslContextBuilder sslContextBuilder, Provider provider) {
        ApplicationProtocolConfig applicationProtocolConfig;
        if (SUN_PROVIDER_NAME.equals(provider.getName())) {
            if (JettyTlsUtil.isJettyAlpnConfigured()) {
                applicationProtocolConfig = ALPN;
            } else if (JettyTlsUtil.isJettyNpnConfigured()) {
                applicationProtocolConfig = NPN;
            } else if (JettyTlsUtil.isJava9AlpnAvailable()) {
                applicationProtocolConfig = ALPN;
            } else {
                throw new IllegalArgumentException(provider.getName() + " selected, but Java 9+ and Jetty NPN/ALPN unavailable");
            }
        } else if (IBM_PROVIDER_NAME.equals(provider.getName()) || OPENJSSE_PROVIDER_NAME.equals(provider.getName())) {
            if (JettyTlsUtil.isJava9AlpnAvailable()) {
                applicationProtocolConfig = ALPN;
            } else {
                throw new IllegalArgumentException(provider.getName() + " selected, but Java 9+ ALPN unavailable");
            }
        } else if (ConscryptLoader.isConscrypt(provider)) {
            applicationProtocolConfig = ALPN;
            sslContextBuilder.protocols("TLSv1.2");
        } else {
            throw new IllegalArgumentException("Unknown provider; can't configure: " + provider);
        }
        return sslContextBuilder.sslProvider(SslProvider.JDK).ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE).applicationProtocolConfig(applicationProtocolConfig).sslContextProvider(provider);
    }

    private static SslProvider defaultSslProvider() {
        if (OpenSsl.isAvailable()) {
            logger.log(Level.FINE, "Selecting OPENSSL");
            return SslProvider.OPENSSL;
        }
        Provider findJdkProvider = findJdkProvider();
        if (findJdkProvider != null) {
            logger.log(Level.FINE, "Selecting JDK with provider {0}", findJdkProvider);
            return SslProvider.JDK;
        }
        logger.log(Level.INFO, "Java 9 ALPN API unavailable (this may be normal)");
        logger.log(Level.INFO, "netty-tcnative unavailable (this may be normal)", OpenSsl.unavailabilityCause());
        logger.log(Level.INFO, "Conscrypt not found (this may be normal)", ConscryptHolder.UNAVAILABILITY_CAUSE);
        logger.log(Level.INFO, "Jetty ALPN unavailable (this may be normal)", JettyTlsUtil.getJettyAlpnUnavailabilityCause());
        throw new IllegalStateException("Could not find TLS ALPN provider; no working netty-tcnative, Conscrypt, or Jetty NPN/ALPN available");
    }

    private static Provider findJdkProvider() {
        for (Provider provider : Security.getProviders("SSLContext.TLS")) {
            if (SUN_PROVIDER_NAME.equals(provider.getName())) {
                if (JettyTlsUtil.isJettyAlpnConfigured() || JettyTlsUtil.isJettyNpnConfigured() || JettyTlsUtil.isJava9AlpnAvailable()) {
                    return provider;
                }
            } else if (IBM_PROVIDER_NAME.equals(provider.getName()) || OPENJSSE_PROVIDER_NAME.equals(provider.getName())) {
                if (JettyTlsUtil.isJava9AlpnAvailable()) {
                    return provider;
                }
            } else if (ConscryptLoader.isConscrypt(provider)) {
                return provider;
            }
        }
        if (ConscryptHolder.PROVIDER != null) {
            return ConscryptHolder.PROVIDER;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void ensureAlpnAndH2Enabled(ApplicationProtocolNegotiator applicationProtocolNegotiator) {
        Preconditions.checkArgument(applicationProtocolNegotiator != null, "ALPN must be configured");
        Preconditions.checkArgument((applicationProtocolNegotiator.protocols() == null || applicationProtocolNegotiator.protocols().isEmpty()) ? false : true, "ALPN must be enabled and list HTTP/2 as a supported protocol.");
        Preconditions.checkArgument(applicationProtocolNegotiator.protocols().contains("h2"), "This ALPN config does not support HTTP/2. Expected %s, but got %s'.", "h2", applicationProtocolNegotiator.protocols());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class ConscryptHolder {
        static final Provider PROVIDER;
        static final Throwable UNAVAILABILITY_CAUSE;

        private ConscryptHolder() {
        }

        static {
            Provider provider = null;
            try {
                th = null;
                provider = ConscryptLoader.newProvider();
            } catch (Throwable th) {
                th = th;
            }
            PROVIDER = provider;
            UNAVAILABILITY_CAUSE = th;
        }
    }
}
