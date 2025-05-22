package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import java.util.List;
import javax.net.ssl.SSLEngine;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public final class JdkAlpnApplicationProtocolNegotiator extends JdkBaseApplicationProtocolNegotiator {
    private static final JdkApplicationProtocolNegotiator.SslEngineWrapperFactory ALPN_WRAPPER;
    private static final boolean AVAILABLE;

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator, io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolListenerFactory() {
        return super.protocolListenerFactory();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator, io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory() {
        return super.protocolSelectorFactory();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator, io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolNegotiator
    public /* bridge */ /* synthetic */ List protocols() {
        return super.protocols();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator, io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory() {
        return super.wrapperFactory();
    }

    static {
        AVAILABLE = Conscrypt.isAvailable() || JdkAlpnSslUtils.supportsAlpn() || JettyAlpnSslEngine.isAvailable();
        ALPN_WRAPPER = AVAILABLE ? new AlpnWrapper() : new FailureWrapper();
    }

    public JdkAlpnApplicationProtocolNegotiator(Iterable<String> iterable) {
        this(false, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(String... strArr) {
        this(false, strArr);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, Iterable<String> iterable) {
        this(z, z, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, String... strArr) {
        this(z, z, strArr);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, boolean z2, Iterable<String> iterable) {
        this(z2 ? FAIL_SELECTOR_FACTORY : NO_FAIL_SELECTOR_FACTORY, z ? FAIL_SELECTION_LISTENER_FACTORY : NO_FAIL_SELECTION_LISTENER_FACTORY, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, boolean z2, String... strArr) {
        this(z2 ? FAIL_SELECTOR_FACTORY : NO_FAIL_SELECTOR_FACTORY, z ? FAIL_SELECTION_LISTENER_FACTORY : NO_FAIL_SELECTION_LISTENER_FACTORY, strArr);
    }

    public JdkAlpnApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, Iterable<String> iterable) {
        super(ALPN_WRAPPER, protocolSelectorFactory, protocolSelectionListenerFactory, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, String... strArr) {
        super(ALPN_WRAPPER, protocolSelectorFactory, protocolSelectionListenerFactory, strArr);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class FailureWrapper extends JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory {
        private FailureWrapper() {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory
        public SSLEngine wrapSslEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
            throw new RuntimeException("ALPN unsupported. Is your classpath configured correctly? For Conscrypt, add the appropriate Conscrypt JAR to classpath and set the security provider. For Jetty-ALPN, see http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html#alpn-starting");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AlpnWrapper extends JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory {
        private AlpnWrapper() {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory
        public SSLEngine wrapSslEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
            if (Conscrypt.isEngineSupported(sSLEngine)) {
                if (z) {
                    return ConscryptAlpnSslEngine.newServerEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
                }
                return ConscryptAlpnSslEngine.newClientEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
            }
            if (JdkAlpnSslUtils.supportsAlpn()) {
                return new JdkAlpnSslEngine(sSLEngine, jdkApplicationProtocolNegotiator, z);
            }
            if (JettyAlpnSslEngine.isAvailable()) {
                if (z) {
                    return JettyAlpnSslEngine.newServerEngine(sSLEngine, jdkApplicationProtocolNegotiator);
                }
                return JettyAlpnSslEngine.newClientEngine(sSLEngine, jdkApplicationProtocolNegotiator);
            }
            throw new UnsupportedOperationException("ALPN not supported. Unable to wrap SSLEngine of type '" + sSLEngine.getClass().getName() + "')");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAlpnSupported() {
        return AVAILABLE;
    }
}
