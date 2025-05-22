package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class JdkAlpnSslEngine extends JdkSslEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AlpnSelector alpnSelector;
    private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener selectionListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine
    public void setNegotiatedApplicationProtocol(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class AlpnSelector implements BiFunction<SSLEngine, List<String>, String> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean called;
        private final JdkApplicationProtocolNegotiator.ProtocolSelector selector;

        AlpnSelector(JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector) {
            this.selector = protocolSelector;
        }

        @Override // java.util.function.BiFunction
        public String apply(SSLEngine sSLEngine, List<String> list) {
            this.called = true;
            try {
                String select = this.selector.select(list);
                return select == null ? "" : select;
            } catch (Exception unused) {
                return null;
            }
        }

        void checkUnsupported() {
            if (!this.called && JdkAlpnSslEngine.this.getApplicationProtocol().isEmpty()) {
                this.selector.unsupported();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkAlpnSslEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
        super(sSLEngine);
        if (z) {
            this.selectionListener = null;
            this.alpnSelector = new AlpnSelector(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())));
            JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(sSLEngine, this.alpnSelector);
        } else {
            this.selectionListener = jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols());
            this.alpnSelector = null;
            JdkAlpnSslUtils.setApplicationProtocols(sSLEngine, jdkApplicationProtocolNegotiator.protocols());
        }
    }

    private SSLEngineResult verifyProtocolSelection(SSLEngineResult sSLEngineResult) throws SSLException {
        if (sSLEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            AlpnSelector alpnSelector = this.alpnSelector;
            if (alpnSelector == null) {
                try {
                    String applicationProtocol = getApplicationProtocol();
                    if (applicationProtocol.isEmpty()) {
                        this.selectionListener.unsupported();
                    } else {
                        this.selectionListener.selected(applicationProtocol);
                    }
                } catch (Throwable th) {
                    throw SslUtils.toSSLHandshakeException(th);
                }
            } else {
                alpnSelector.checkUnsupported();
            }
        }
        return sSLEngineResult;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBuffer, byteBuffer2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer[] byteBufferArr, ByteBuffer byteBuffer) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBufferArr, byteBuffer));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer byteBuffer) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBufferArr, i, i2, byteBuffer));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBuffer2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBufferArr));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBufferArr, i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolAccessor
    public String getNegotiatedApplicationProtocol() {
        String applicationProtocol = getApplicationProtocol();
        if (applicationProtocol == null || applicationProtocol.isEmpty()) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // javax.net.ssl.SSLEngine
    public String getApplicationProtocol() {
        return JdkAlpnSslUtils.getApplicationProtocol(getWrappedEngine());
    }

    @Override // javax.net.ssl.SSLEngine
    public String getHandshakeApplicationProtocol() {
        return JdkAlpnSslUtils.getHandshakeApplicationProtocol(getWrappedEngine());
    }

    @Override // javax.net.ssl.SSLEngine
    public void setHandshakeApplicationProtocolSelector(BiFunction<SSLEngine, List<String>, String> biFunction) {
        JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(getWrappedEngine(), biFunction);
    }

    @Override // javax.net.ssl.SSLEngine
    public BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return JdkAlpnSslUtils.getHandshakeApplicationProtocolSelector(getWrappedEngine());
    }
}
