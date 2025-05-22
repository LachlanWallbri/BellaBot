package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import org.conscrypt.AllocatedBuffer;
import org.conscrypt.BufferAllocator;
import org.conscrypt.HandshakeListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ConscryptAlpnSslEngine extends JdkSslEngine {
    private static final boolean USE_BUFFER_ALLOCATOR = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.handler.ssl.conscrypt.useBufferAllocator", true);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConscryptAlpnSslEngine newClientEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
        return new ClientEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConscryptAlpnSslEngine newServerEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
        return new ServerEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
    }

    private ConscryptAlpnSslEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, List<String> list) {
        super(sSLEngine);
        if (USE_BUFFER_ALLOCATOR) {
            org.conscrypt.Conscrypt.setBufferAllocator(sSLEngine, new BufferAllocatorAdapter(byteBufAllocator));
        }
        org.conscrypt.Conscrypt.setApplicationProtocols(sSLEngine, (String[]) list.toArray(new String[0]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int calculateOutNetBufSize(int i, int i2) {
        return (int) Math.min(2147483647L, i + (org.conscrypt.Conscrypt.maxSealOverhead(getWrappedEngine()) * i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        return org.conscrypt.Conscrypt.unwrap(getWrappedEngine(), byteBufferArr, byteBufferArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ClientEngine extends ConscryptAlpnSslEngine {
        private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener protocolListener;

        ClientEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
            super(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator.protocols());
            org.conscrypt.Conscrypt.setHandshakeListener(sSLEngine, new HandshakeListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ConscryptAlpnSslEngine.ClientEngine.1
                @Override // org.conscrypt.HandshakeListener
                public void onHandshakeFinished() throws SSLException {
                    ClientEngine.this.selectProtocol();
                }
            });
            this.protocolListener = (JdkApplicationProtocolNegotiator.ProtocolSelectionListener) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols()), "protocolListener");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void selectProtocol() throws SSLException {
            try {
                this.protocolListener.selected(org.conscrypt.Conscrypt.getApplicationProtocol(getWrappedEngine()));
            } catch (Throwable th) {
                throw SslUtils.toSSLHandshakeException(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ServerEngine extends ConscryptAlpnSslEngine {
        private final JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector;

        ServerEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
            super(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator.protocols());
            org.conscrypt.Conscrypt.setHandshakeListener(sSLEngine, new HandshakeListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ConscryptAlpnSslEngine.ServerEngine.1
                @Override // org.conscrypt.HandshakeListener
                public void onHandshakeFinished() throws SSLException {
                    ServerEngine.this.selectProtocol();
                }
            });
            this.protocolSelector = (JdkApplicationProtocolNegotiator.ProtocolSelector) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())), "protocolSelector");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void selectProtocol() throws SSLException {
            try {
                String applicationProtocol = org.conscrypt.Conscrypt.getApplicationProtocol(getWrappedEngine());
                this.protocolSelector.select(applicationProtocol != null ? Collections.singletonList(applicationProtocol) : Collections.emptyList());
            } catch (Throwable th) {
                throw SslUtils.toSSLHandshakeException(th);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class BufferAllocatorAdapter extends BufferAllocator {
        private final ByteBufAllocator alloc;

        BufferAllocatorAdapter(ByteBufAllocator byteBufAllocator) {
            this.alloc = byteBufAllocator;
        }

        @Override // org.conscrypt.BufferAllocator
        public AllocatedBuffer allocateDirectBuffer(int i) {
            return new BufferAdapter(this.alloc.directBuffer(i));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class BufferAdapter extends AllocatedBuffer {
        private final ByteBuffer buffer;
        private final ByteBuf nettyBuffer;

        BufferAdapter(ByteBuf byteBuf) {
            this.nettyBuffer = byteBuf;
            this.buffer = byteBuf.nioBuffer(0, byteBuf.capacity());
        }

        @Override // org.conscrypt.AllocatedBuffer
        public ByteBuffer nioBuffer() {
            return this.buffer;
        }

        @Override // org.conscrypt.AllocatedBuffer
        public AllocatedBuffer retain() {
            this.nettyBuffer.retain();
            return this;
        }

        @Override // org.conscrypt.AllocatedBuffer
        public AllocatedBuffer release() {
            this.nettyBuffer.release();
            return this;
        }
    }
}
