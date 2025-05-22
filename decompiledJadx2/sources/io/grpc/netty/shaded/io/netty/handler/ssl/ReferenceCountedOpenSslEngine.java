package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig;
import io.grpc.netty.shaded.io.netty.internal.tcnative.Buffer;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetectorFactory;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ReferenceCountedOpenSslEngine extends SSLEngine implements ReferenceCounted, ApplicationProtocolAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV2 = 0;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV3 = 1;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1 = 2;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_1 = 3;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_2 = 4;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_3 = 5;
    private Object algorithmConstraints;
    final ByteBufAllocator alloc;
    private final OpenSslApplicationProtocolNegotiator apn;
    private volatile String applicationProtocol;
    private volatile ClientAuth clientAuth;
    private final boolean clientMode;
    private volatile boolean destroyed;
    private final boolean enableOcsp;
    private String endPointIdentificationAlgorithm;
    private final OpenSslEngineMap engineMap;
    private HandshakeState handshakeState;
    private boolean isInboundDone;
    final boolean jdkCompatibilityMode;
    private volatile long lastAccessed;
    private final ResourceLeakTracker<ReferenceCountedOpenSslEngine> leak;
    private volatile Certificate[] localCertificateChain;
    private volatile Collection<?> matchers;
    private int maxWrapBufferSize;
    private int maxWrapOverhead;
    private volatile boolean needTask;
    private long networkBIO;
    private boolean outboundClosed;
    private final ReferenceCountedOpenSslContext parentContext;
    private Throwable pendingException;
    private boolean receivedShutdown;
    private final AbstractReferenceCounted refCnt;
    private final OpenSslSession session;
    private final ByteBuffer[] singleDstBuffer;
    private final ByteBuffer[] singleSrcBuffer;
    private List<String> sniHostNames;
    private long ssl;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslEngine.class);
    private static final ResourceLeakDetector<ReferenceCountedOpenSslEngine> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedOpenSslEngine.class);
    private static final int[] OPENSSL_OP_NO_PROTOCOLS = {SSL.SSL_OP_NO_SSLv2, SSL.SSL_OP_NO_SSLv3, SSL.SSL_OP_NO_TLSv1, SSL.SSL_OP_NO_TLSv1_1, SSL.SSL_OP_NO_TLSv1_2, SSL.SSL_OP_NO_TLSv1_3};
    static final int MAX_PLAINTEXT_LENGTH = SSL.SSL_MAX_PLAINTEXT_LENGTH;
    private static final int MAX_RECORD_SIZE = SSL.SSL_MAX_RECORD_LENGTH;
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum HandshakeState {
        NOT_STARTED,
        STARTED_IMPLICITLY,
        STARTED_EXPLICITLY,
        FINISHED
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getEnableSessionCreation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslEngine(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, ByteBufAllocator byteBufAllocator, String str, int i, boolean z, boolean z2) {
        super(str, i);
        this.handshakeState = HandshakeState.NOT_STARTED;
        this.refCnt = new AbstractReferenceCounted() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
            public ReferenceCounted touch(Object obj) {
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.record(obj);
                }
                return ReferenceCountedOpenSslEngine.this;
            }

            @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
            protected void deallocate() {
                ReferenceCountedOpenSslEngine.this.shutdown();
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.close(ReferenceCountedOpenSslEngine.this);
                }
                ReferenceCountedOpenSslEngine.this.parentContext.release();
            }
        };
        this.clientAuth = ClientAuth.NONE;
        this.lastAccessed = -1L;
        boolean z3 = true;
        this.singleSrcBuffer = new ByteBuffer[1];
        this.singleDstBuffer = new ByteBuffer[1];
        OpenSsl.ensureAvailability();
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        this.apn = (OpenSslApplicationProtocolNegotiator) referenceCountedOpenSslContext.applicationProtocolNegotiator();
        this.clientMode = referenceCountedOpenSslContext.isClient();
        if (PlatformDependent.javaVersion() >= 7) {
            this.session = new ExtendedOpenSslSession(new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext())) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.2
                private String[] peerSupportedSignatureAlgorithms;
                private List requestedServerNames;

                @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession, javax.net.ssl.ExtendedSSLSession
                public List getRequestedServerNames() {
                    List list;
                    if (ReferenceCountedOpenSslEngine.this.clientMode) {
                        return Java8SslUtils.getSniHostNames((List<String>) ReferenceCountedOpenSslEngine.this.sniHostNames);
                    }
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.requestedServerNames == null) {
                            if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                                if (SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl) == null) {
                                    this.requestedServerNames = Collections.emptyList();
                                } else {
                                    this.requestedServerNames = Java8SslUtils.getSniHostName(SSL.getSniHostname(ReferenceCountedOpenSslEngine.this.ssl).getBytes(CharsetUtil.UTF_8));
                                }
                            } else {
                                this.requestedServerNames = Collections.emptyList();
                            }
                        }
                        list = this.requestedServerNames;
                    }
                    return list;
                }

                @Override // javax.net.ssl.ExtendedSSLSession
                public String[] getPeerSupportedSignatureAlgorithms() {
                    String[] strArr;
                    synchronized (ReferenceCountedOpenSslEngine.this) {
                        if (this.peerSupportedSignatureAlgorithms == null) {
                            if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                                String[] sigAlgs = SSL.getSigAlgs(ReferenceCountedOpenSslEngine.this.ssl);
                                if (sigAlgs == null) {
                                    this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                                } else {
                                    LinkedHashSet linkedHashSet = new LinkedHashSet(sigAlgs.length);
                                    for (String str2 : sigAlgs) {
                                        String javaName = SignatureAlgorithmConverter.toJavaName(str2);
                                        if (javaName != null) {
                                            linkedHashSet.add(javaName);
                                        }
                                    }
                                    this.peerSupportedSignatureAlgorithms = (String[]) linkedHashSet.toArray(new String[0]);
                                }
                            } else {
                                this.peerSupportedSignatureAlgorithms = EmptyArrays.EMPTY_STRINGS;
                            }
                        }
                        strArr = (String[]) this.peerSupportedSignatureAlgorithms.clone();
                    }
                    return strArr;
                }

                @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession
                public List<byte[]> getStatusResponses() {
                    if (ReferenceCountedOpenSslEngine.this.enableOcsp && ReferenceCountedOpenSslEngine.this.clientMode) {
                        synchronized (ReferenceCountedOpenSslEngine.this) {
                            r1 = ReferenceCountedOpenSslEngine.this.isDestroyed() ? null : SSL.getOcspResponse(ReferenceCountedOpenSslEngine.this.ssl);
                        }
                    }
                    return r1 == null ? Collections.emptyList() : Collections.singletonList(r1);
                }
            };
        } else {
            this.session = new DefaultOpenSslSession(referenceCountedOpenSslContext.sessionContext());
        }
        this.engineMap = referenceCountedOpenSslContext.engineMap;
        this.enableOcsp = referenceCountedOpenSslContext.enableOcsp;
        if (!referenceCountedOpenSslContext.sessionContext().useKeyManager()) {
            this.localCertificateChain = referenceCountedOpenSslContext.keyCertChain;
        }
        this.jdkCompatibilityMode = z;
        Lock readLock = referenceCountedOpenSslContext.ctxLock.readLock();
        readLock.lock();
        try {
            long j = referenceCountedOpenSslContext.ctx;
            if (referenceCountedOpenSslContext.isClient()) {
                z3 = false;
            }
            long newSSL = SSL.newSSL(j, z3);
            synchronized (this) {
                this.ssl = newSSL;
                try {
                    this.networkBIO = SSL.bioNewByteBuffer(this.ssl, referenceCountedOpenSslContext.getBioNonApplicationBufferSize());
                    setClientAuth(this.clientMode ? ClientAuth.NONE : referenceCountedOpenSslContext.clientAuth);
                    if (referenceCountedOpenSslContext.protocols != null) {
                        setEnabledProtocols(referenceCountedOpenSslContext.protocols);
                    }
                    if (this.clientMode && SslUtils.isValidHostNameForSNI(str)) {
                        SSL.setTlsExtHostName(this.ssl, str);
                        this.sniHostNames = Collections.singletonList(str);
                    }
                    if (this.enableOcsp) {
                        SSL.enableOcsp(this.ssl);
                    }
                    if (!z) {
                        SSL.setMode(this.ssl, SSL.getMode(this.ssl) | SSL.SSL_MODE_ENABLE_PARTIAL_WRITE | SSL.SSL_MODE_ENABLE_FALSE_START);
                    }
                    calculateMaxWrapOverhead();
                } catch (Throwable th) {
                    shutdown();
                    PlatformDependent.throwException(th);
                }
            }
            this.parentContext = referenceCountedOpenSslContext;
            this.parentContext.retain();
            this.leak = z2 ? leakDetector.track(this) : null;
        } finally {
            readLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized String[] authMethods() {
        if (isDestroyed()) {
            return EmptyArrays.EMPTY_STRINGS;
        }
        return SSL.authenticationMethods(this.ssl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean setKeyMaterial(OpenSslKeyMaterial openSslKeyMaterial) throws Exception {
        synchronized (this) {
            if (isDestroyed()) {
                return false;
            }
            SSL.setKeyMaterial(this.ssl, openSslKeyMaterial.certificateChainAddress(), openSslKeyMaterial.privateKeyAddress());
            this.localCertificateChain = openSslKeyMaterial.certificateChain();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized SecretKeySpec masterKey() {
        if (isDestroyed()) {
            return null;
        }
        return new SecretKeySpec(SSL.getMasterKey(this.ssl), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
    }

    public void setOcspResponse(byte[] bArr) {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (this.clientMode) {
            throw new IllegalStateException("Not a server SSLEngine");
        }
        synchronized (this) {
            if (!isDestroyed()) {
                SSL.setOcspResponse(this.ssl, bArr);
            }
        }
    }

    public byte[] getOcspResponse() {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (!this.clientMode) {
            throw new IllegalStateException("Not a client SSLEngine");
        }
        synchronized (this) {
            if (isDestroyed()) {
                return EmptyArrays.EMPTY_BYTES;
            }
            return SSL.getOcspResponse(this.ssl);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final int refCnt() {
        return this.refCnt.refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted retain() {
        this.refCnt.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted retain(int i) {
        this.refCnt.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted touch() {
        this.refCnt.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ReferenceCounted touch(Object obj) {
        this.refCnt.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release() {
        return this.refCnt.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release(int i) {
        return this.refCnt.release(i);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLSession getHandshakeSession() {
        int i = C66484.f8420xea902ccf[this.handshakeState.ordinal()];
        if (i == 1 || i == 2) {
            return null;
        }
        return this.session;
    }

    public final synchronized long sslPointer() {
        return this.ssl;
    }

    public final synchronized void shutdown() {
        if (!this.destroyed) {
            this.destroyed = true;
            this.engineMap.remove(this.ssl);
            SSL.freeSSL(this.ssl);
            this.networkBIO = 0L;
            this.ssl = 0L;
            this.outboundClosed = true;
            this.isInboundDone = true;
        }
        SSL.clearError();
    }

    private int writePlaintextData(ByteBuffer byteBuffer, int i) {
        int writeToSSL;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        if (byteBuffer.isDirect()) {
            writeToSSL = SSL.writeToSSL(this.ssl, bufferAddress(byteBuffer) + position, i);
            if (writeToSSL > 0) {
                byteBuffer.position(position + writeToSSL);
            }
        } else {
            ByteBuf directBuffer = this.alloc.directBuffer(i);
            try {
                byteBuffer.limit(position + i);
                directBuffer.setBytes(0, byteBuffer);
                byteBuffer.limit(limit);
                writeToSSL = SSL.writeToSSL(this.ssl, OpenSsl.memoryAddress(directBuffer), i);
                if (writeToSSL > 0) {
                    byteBuffer.position(position + writeToSSL);
                } else {
                    byteBuffer.position(position);
                }
            } finally {
                directBuffer.release();
            }
        }
        return writeToSSL;
    }

    private ByteBuf writeEncryptedData(ByteBuffer byteBuffer, int i) {
        int position = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(byteBuffer) + position, i, false);
            return null;
        }
        ByteBuf directBuffer = this.alloc.directBuffer(i);
        try {
            int limit = byteBuffer.limit();
            byteBuffer.limit(position + i);
            directBuffer.writeBytes(byteBuffer);
            byteBuffer.position(position);
            byteBuffer.limit(limit);
            SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(directBuffer), i, false);
            return directBuffer;
        } catch (Throwable th) {
            directBuffer.release();
            PlatformDependent.throwException(th);
            return null;
        }
    }

    private int readPlaintextData(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        if (byteBuffer.isDirect()) {
            int readFromSSL = SSL.readFromSSL(this.ssl, bufferAddress(byteBuffer) + position, byteBuffer.limit() - position);
            if (readFromSSL <= 0) {
                return readFromSSL;
            }
            byteBuffer.position(position + readFromSSL);
            return readFromSSL;
        }
        int limit = byteBuffer.limit();
        int min = Math.min(maxEncryptedPacketLength0(), limit - position);
        ByteBuf directBuffer = this.alloc.directBuffer(min);
        try {
            int readFromSSL2 = SSL.readFromSSL(this.ssl, OpenSsl.memoryAddress(directBuffer), min);
            if (readFromSSL2 > 0) {
                byteBuffer.limit(position + readFromSSL2);
                directBuffer.getBytes(directBuffer.readerIndex(), byteBuffer);
                byteBuffer.limit(limit);
            }
            return readFromSSL2;
        } finally {
            directBuffer.release();
        }
    }

    final synchronized int maxWrapOverhead() {
        return this.maxWrapOverhead;
    }

    final synchronized int maxEncryptedPacketLength() {
        return maxEncryptedPacketLength0();
    }

    final int maxEncryptedPacketLength0() {
        return this.maxWrapOverhead + MAX_PLAINTEXT_LENGTH;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int calculateMaxLengthForWrap(int i, int i2) {
        return (int) Math.min(this.maxWrapBufferSize, i + (this.maxWrapOverhead * i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized int sslPending() {
        return sslPending0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateMaxWrapOverhead() {
        this.maxWrapOverhead = SSL.getMaxWrapOverhead(this.ssl);
        this.maxWrapBufferSize = this.jdkCompatibilityMode ? maxEncryptedPacketLength0() : maxEncryptedPacketLength0() << 4;
    }

    private int sslPending0() {
        if (this.handshakeState != HandshakeState.FINISHED) {
            return 0;
        }
        return SSL.sslPending(this.ssl);
    }

    private boolean isBytesAvailableEnoughForWrap(int i, int i2, int i3) {
        return ((long) i) - (((long) this.maxWrapOverhead) * ((long) i3)) >= ((long) i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x052c A[Catch: all -> 0x0544, TryCatch #2 {, blocks: (B:12:0x001d, B:14:0x0023, B:16:0x0029, B:19:0x0030, B:20:0x0035, B:23:0x0033, B:35:0x008e, B:37:0x0095, B:38:0x00ac, B:40:0x009e, B:44:0x00bc, B:46:0x00c3, B:47:0x00da, B:49:0x00cc, B:53:0x00e8, B:55:0x00ef, B:56:0x0106, B:58:0x00f8, B:60:0x0116, B:62:0x011d, B:63:0x0134, B:65:0x0126, B:184:0x0525, B:186:0x052c, B:187:0x0543, B:188:0x053b, B:77:0x015e, B:79:0x0165, B:80:0x017c, B:82:0x016e, B:84:0x0186, B:86:0x018d, B:87:0x01a4, B:89:0x0196, B:93:0x01ba, B:95:0x01c1, B:96:0x01d8, B:98:0x01ca, B:105:0x01fa, B:107:0x0201, B:108:0x0218, B:110:0x020a, B:118:0x0229, B:120:0x0230, B:121:0x0247, B:123:0x0239, B:129:0x0257, B:131:0x025e, B:132:0x0275, B:134:0x0267, B:159:0x02cb, B:161:0x02d2, B:162:0x02e9, B:164:0x02db, B:168:0x02f7, B:170:0x02fe, B:171:0x0315, B:173:0x0307, B:204:0x0392, B:206:0x0399, B:207:0x03b0, B:209:0x03a2, B:224:0x03ee, B:226:0x03f5, B:227:0x040c, B:229:0x03fe, B:233:0x0414, B:235:0x041b, B:236:0x0432, B:238:0x0424, B:242:0x043e, B:244:0x0445, B:245:0x045c, B:247:0x044e, B:252:0x046a, B:254:0x0471, B:255:0x0488, B:257:0x047a, B:259:0x0490, B:261:0x0497, B:262:0x04ae, B:264:0x04a0, B:275:0x04ca, B:277:0x04d1, B:278:0x04e8, B:280:0x04da, B:286:0x034b, B:288:0x0352, B:289:0x0369, B:291:0x035b, B:294:0x04f1, B:296:0x04f8, B:297:0x050f, B:299:0x0501), top: B:11:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x053b A[Catch: all -> 0x0544, TryCatch #2 {, blocks: (B:12:0x001d, B:14:0x0023, B:16:0x0029, B:19:0x0030, B:20:0x0035, B:23:0x0033, B:35:0x008e, B:37:0x0095, B:38:0x00ac, B:40:0x009e, B:44:0x00bc, B:46:0x00c3, B:47:0x00da, B:49:0x00cc, B:53:0x00e8, B:55:0x00ef, B:56:0x0106, B:58:0x00f8, B:60:0x0116, B:62:0x011d, B:63:0x0134, B:65:0x0126, B:184:0x0525, B:186:0x052c, B:187:0x0543, B:188:0x053b, B:77:0x015e, B:79:0x0165, B:80:0x017c, B:82:0x016e, B:84:0x0186, B:86:0x018d, B:87:0x01a4, B:89:0x0196, B:93:0x01ba, B:95:0x01c1, B:96:0x01d8, B:98:0x01ca, B:105:0x01fa, B:107:0x0201, B:108:0x0218, B:110:0x020a, B:118:0x0229, B:120:0x0230, B:121:0x0247, B:123:0x0239, B:129:0x0257, B:131:0x025e, B:132:0x0275, B:134:0x0267, B:159:0x02cb, B:161:0x02d2, B:162:0x02e9, B:164:0x02db, B:168:0x02f7, B:170:0x02fe, B:171:0x0315, B:173:0x0307, B:204:0x0392, B:206:0x0399, B:207:0x03b0, B:209:0x03a2, B:224:0x03ee, B:226:0x03f5, B:227:0x040c, B:229:0x03fe, B:233:0x0414, B:235:0x041b, B:236:0x0432, B:238:0x0424, B:242:0x043e, B:244:0x0445, B:245:0x045c, B:247:0x044e, B:252:0x046a, B:254:0x0471, B:255:0x0488, B:257:0x047a, B:259:0x0490, B:261:0x0497, B:262:0x04ae, B:264:0x04a0, B:275:0x04ca, B:277:0x04d1, B:278:0x04e8, B:280:0x04da, B:286:0x034b, B:288:0x0352, B:289:0x0369, B:291:0x035b, B:294:0x04f1, B:296:0x04f8, B:297:0x050f, B:299:0x0501), top: B:11:0x001d }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer byteBuffer) throws SSLException {
        int i3;
        ByteBuf byteBuf;
        int i4;
        SSLEngineResult.HandshakeStatus handshakeStatus;
        int i5;
        int writePlaintextData;
        SSLEngineResult sSLEngineResult;
        int i6 = i;
        if (byteBufferArr == null) {
            throw new IllegalArgumentException("srcs is null");
        }
        if (byteBuffer == null) {
            throw new IllegalArgumentException("dst is null");
        }
        if (i6 >= byteBufferArr.length || (i3 = i6 + i2) > byteBufferArr.length) {
            throw new IndexOutOfBoundsException("offset: " + i6 + ", length: " + i2 + " (expected: offset <= offset + length <= srcs.length (" + byteBufferArr.length + "))");
        }
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        synchronized (this) {
            if (isOutboundDone()) {
                if (!isInboundDone() && !isDestroyed()) {
                    sSLEngineResult = NEED_UNWRAP_CLOSED;
                    return sSLEngineResult;
                }
                sSLEngineResult = CLOSED_NOT_HANDSHAKING;
                return sSLEngineResult;
            }
            int i7 = 0;
            try {
                if (byteBuffer.isDirect()) {
                    SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(byteBuffer) + byteBuffer.position(), byteBuffer.remaining(), true);
                    byteBuf = null;
                } else {
                    byteBuf = this.alloc.directBuffer(byteBuffer.remaining());
                    try {
                        SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(byteBuf), byteBuf.writableBytes(), true);
                    } catch (Throwable th) {
                        th = th;
                        i4 = 0;
                        SSL.bioClearByteBuffer(this.networkBIO);
                        if (byteBuf != null) {
                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i4));
                            byteBuf.release();
                        } else {
                            byteBuffer.position(byteBuffer.position() + i4);
                        }
                        throw th;
                    }
                }
                int bioLengthByteBuffer = SSL.bioLengthByteBuffer(this.networkBIO);
                try {
                    if (this.outboundClosed) {
                        if (!isBytesAvailableEnoughForWrap(byteBuffer.remaining(), 2, 1)) {
                            SSLEngineResult sSLEngineResult2 = new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatus(), 0, 0);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + 0);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), 0));
                                byteBuf.release();
                            }
                            return sSLEngineResult2;
                        }
                        int bioFlushByteBuffer = SSL.bioFlushByteBuffer(this.networkBIO);
                        if (bioFlushByteBuffer <= 0) {
                            SSLEngineResult newResultMayFinishHandshake = newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer));
                                byteBuf.release();
                            }
                            return newResultMayFinishHandshake;
                        }
                        if (!doSSLShutdown()) {
                            SSLEngineResult newResultMayFinishHandshake2 = newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, bioFlushByteBuffer);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer));
                                byteBuf.release();
                            }
                            return newResultMayFinishHandshake2;
                        }
                        int bioLengthByteBuffer2 = bioLengthByteBuffer - SSL.bioLengthByteBuffer(this.networkBIO);
                        SSLEngineResult newResultMayFinishHandshake3 = newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, bioLengthByteBuffer2);
                        SSL.bioClearByteBuffer(this.networkBIO);
                        if (byteBuf == null) {
                            byteBuffer.position(byteBuffer.position() + bioLengthByteBuffer2);
                        } else {
                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioLengthByteBuffer2));
                            byteBuf.release();
                        }
                        return newResultMayFinishHandshake3;
                    }
                    SSLEngineResult.HandshakeStatus handshakeStatus2 = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                    if (this.handshakeState != HandshakeState.FINISHED) {
                        if (this.handshakeState != HandshakeState.STARTED_EXPLICITLY) {
                            this.handshakeState = HandshakeState.STARTED_IMPLICITLY;
                        }
                        i4 = SSL.bioFlushByteBuffer(this.networkBIO);
                        try {
                            if (this.pendingException != null) {
                                if (i4 > 0) {
                                    SSLEngineResult newResult = newResult(SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, i4);
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                        byteBuffer.position(byteBuffer.position() + i4);
                                    } else {
                                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i4));
                                        byteBuf.release();
                                    }
                                    return newResult;
                                }
                                SSLEngineResult newResult2 = newResult(handshakeException(), 0, 0);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i4);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i4));
                                    byteBuf.release();
                                }
                                return newResult2;
                            }
                            handshakeStatus = handshake();
                            i5 = bioLengthByteBuffer - SSL.bioLengthByteBuffer(this.networkBIO);
                            if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                                SSLEngineResult newResult3 = newResult(handshakeStatus, 0, i5);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i5);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i5));
                                    byteBuf.release();
                                }
                                return newResult3;
                            }
                            if (i5 > 0) {
                                SSLEngineResult newResult4 = newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? i5 == bioLengthByteBuffer ? SSLEngineResult.HandshakeStatus.NEED_WRAP : getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO)) : SSLEngineResult.HandshakeStatus.FINISHED), 0, i5);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i5);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i5));
                                    byteBuf.release();
                                }
                                return newResult4;
                            }
                            if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                                SSLEngineResult sSLEngineResult3 = isOutboundDone() ? NEED_UNWRAP_CLOSED : NEED_UNWRAP_OK;
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i5);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i5));
                                    byteBuf.release();
                                }
                                return sSLEngineResult3;
                            }
                            if (this.outboundClosed) {
                                int bioFlushByteBuffer2 = SSL.bioFlushByteBuffer(this.networkBIO);
                                SSLEngineResult newResultMayFinishHandshake4 = newResultMayFinishHandshake(handshakeStatus, 0, bioFlushByteBuffer2);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer2);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer2));
                                    byteBuf.release();
                                }
                                return newResultMayFinishHandshake4;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf != null) {
                            }
                            throw th;
                        }
                    } else {
                        handshakeStatus = handshakeStatus2;
                        i5 = 0;
                    }
                    if (this.jdkCompatibilityMode) {
                        int i8 = 0;
                        for (int i9 = i6; i9 < i3; i9++) {
                            ByteBuffer byteBuffer2 = byteBufferArr[i9];
                            if (byteBuffer2 == null) {
                                throw new IllegalArgumentException("srcs[" + i9 + "] is null");
                            }
                            if (i8 != MAX_PLAINTEXT_LENGTH && ((i8 = i8 + byteBuffer2.remaining()) > MAX_PLAINTEXT_LENGTH || i8 < 0)) {
                                i8 = MAX_PLAINTEXT_LENGTH;
                            }
                        }
                        if (!isBytesAvailableEnoughForWrap(byteBuffer.remaining(), i8, 1)) {
                            SSLEngineResult sSLEngineResult4 = new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatus(), 0, 0);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + i5);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i5));
                                byteBuf.release();
                            }
                            return sSLEngineResult4;
                        }
                    }
                    int bioFlushByteBuffer3 = SSL.bioFlushByteBuffer(this.networkBIO);
                    if (bioFlushByteBuffer3 > 0) {
                        SSLEngineResult newResultMayFinishHandshake5 = newResultMayFinishHandshake(handshakeStatus, 0, bioFlushByteBuffer3);
                        SSL.bioClearByteBuffer(this.networkBIO);
                        if (byteBuf == null) {
                            byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer3);
                        } else {
                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer3));
                            byteBuf.release();
                        }
                        return newResultMayFinishHandshake5;
                    }
                    if (this.pendingException != null) {
                        Throwable th3 = this.pendingException;
                        this.pendingException = null;
                        shutdown();
                        throw new SSLException(th3);
                    }
                    while (i6 < i3) {
                        ByteBuffer byteBuffer3 = byteBufferArr[i6];
                        int remaining = byteBuffer3.remaining();
                        if (remaining != 0) {
                            if (this.jdkCompatibilityMode) {
                                writePlaintextData = writePlaintextData(byteBuffer3, Math.min(remaining, MAX_PLAINTEXT_LENGTH - i7));
                            } else {
                                int remaining2 = (byteBuffer.remaining() - bioFlushByteBuffer3) - this.maxWrapOverhead;
                                if (remaining2 <= 0) {
                                    SSLEngineResult sSLEngineResult5 = new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatus(), i7, bioFlushByteBuffer3);
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                        byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer3);
                                    } else {
                                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer3));
                                        byteBuf.release();
                                    }
                                    return sSLEngineResult5;
                                }
                                writePlaintextData = writePlaintextData(byteBuffer3, Math.min(remaining, remaining2));
                            }
                            int bioLengthByteBuffer3 = SSL.bioLengthByteBuffer(this.networkBIO);
                            int i10 = (bioLengthByteBuffer - bioLengthByteBuffer3) + bioFlushByteBuffer3;
                            if (writePlaintextData > 0) {
                                i7 += writePlaintextData;
                                if (!this.jdkCompatibilityMode && i10 != byteBuffer.remaining()) {
                                    bioFlushByteBuffer3 = i10;
                                    bioLengthByteBuffer = bioLengthByteBuffer3;
                                }
                                SSLEngineResult newResultMayFinishHandshake6 = newResultMayFinishHandshake(handshakeStatus, i7, i10);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i10);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                    byteBuf.release();
                                }
                                return newResultMayFinishHandshake6;
                            }
                            int error = SSL.getError(this.ssl, writePlaintextData);
                            if (error == SSL.SSL_ERROR_ZERO_RETURN) {
                                if (this.receivedShutdown) {
                                    SSLEngineResult newResult5 = newResult(SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, i7, i10);
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                        byteBuffer.position(byteBuffer.position() + i10);
                                    } else {
                                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                        byteBuf.release();
                                    }
                                    return newResult5;
                                }
                                closeAll();
                                int bioLengthByteBuffer4 = i10 + (bioLengthByteBuffer3 - SSL.bioLengthByteBuffer(this.networkBIO));
                                SSLEngineResult newResult6 = newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? bioLengthByteBuffer4 == byteBuffer.remaining() ? SSLEngineResult.HandshakeStatus.NEED_WRAP : getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO)) : SSLEngineResult.HandshakeStatus.FINISHED), i7, bioLengthByteBuffer4);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + bioLengthByteBuffer4);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioLengthByteBuffer4));
                                    byteBuf.release();
                                }
                                return newResult6;
                            }
                            if (error == SSL.SSL_ERROR_WANT_READ) {
                                SSLEngineResult newResult7 = newResult(SSLEngineResult.HandshakeStatus.NEED_UNWRAP, i7, i10);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i10);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                    byteBuf.release();
                                }
                                return newResult7;
                            }
                            if (error != SSL.SSL_ERROR_WANT_WRITE) {
                                if (error != SSL.SSL_ERROR_WANT_X509_LOOKUP && error != SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY && error != SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                                    throw shutdownWithError("SSL_write", error);
                                }
                                SSLEngineResult newResult8 = newResult(SSLEngineResult.HandshakeStatus.NEED_TASK, i7, i10);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i10);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                    byteBuf.release();
                                }
                                return newResult8;
                            }
                            if (i10 > 0) {
                                SSLEngineResult newResult9 = newResult(SSLEngineResult.HandshakeStatus.NEED_WRAP, i7, i10);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + i10);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                    byteBuf.release();
                                }
                                return newResult9;
                            }
                            SSLEngineResult newResult10 = newResult(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, i7, i10);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + i10);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i10));
                                byteBuf.release();
                            }
                            return newResult10;
                        }
                        try {
                            i6++;
                        } catch (Throwable th4) {
                            th = th4;
                            i4 = bioLengthByteBuffer;
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf != null) {
                            }
                            throw th;
                        }
                    }
                    SSLEngineResult newResultMayFinishHandshake7 = newResultMayFinishHandshake(handshakeStatus, i7, bioFlushByteBuffer3);
                    SSL.bioClearByteBuffer(this.networkBIO);
                    if (byteBuf == null) {
                        byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer3);
                    } else {
                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer3));
                        byteBuf.release();
                    }
                    return newResultMayFinishHandshake7;
                } catch (Throwable th5) {
                    th = th5;
                    i4 = i6;
                }
            } catch (Throwable th6) {
                th = th6;
                byteBuf = null;
            }
        }
    }

    private SSLEngineResult newResult(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        return newResult(SSLEngineResult.Status.OK, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResult(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) {
        if (isOutboundDone()) {
            if (isInboundDone()) {
                handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                shutdown();
            }
            return new SSLEngineResult(SSLEngineResult.Status.CLOSED, handshakeStatus, i, i2);
        }
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_TASK) {
            this.needTask = true;
        }
        return new SSLEngineResult(status, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(status, mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLException shutdownWithError(String str, int i) {
        return shutdownWithError(str, i, SSL.getLastErrorNumber());
    }

    private SSLException shutdownWithError(String str, int i, int i2) {
        String errorString = SSL.getErrorString(i2);
        if (logger.isDebugEnabled()) {
            logger.debug("{} failed with {}: OpenSSL error: {} {}", str, Integer.valueOf(i), Integer.valueOf(i2), errorString);
        }
        shutdown();
        if (this.handshakeState == HandshakeState.FINISHED) {
            return new SSLException(errorString);
        }
        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException(errorString);
        Throwable th = this.pendingException;
        if (th != null) {
            sSLHandshakeException.initCause(th);
            this.pendingException = null;
        }
        return sSLHandshakeException;
    }

    /* JADX WARN: Code restructure failed: missing block: B:134:0x021a, code lost:
    
        if (r13 == null) goto L192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01ab, code lost:
    
        r13.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02a6, code lost:
    
        io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.bioClearByteBuffer(r18.networkBIO);
        rejectRemoteInitiatedRenegotiation();
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02b0, code lost:
    
        if (r18.receivedShutdown != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02bd, code lost:
    
        if ((io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.getShutdown(r18.ssl) & io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN) != io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.SSL_RECEIVED_SHUTDOWN) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02bf, code lost:
    
        closeAll();
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02c6, code lost:
    
        if (isInboundDone() == false) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02c8, code lost:
    
        r0 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02cd, code lost:
    
        r0 = newResultMayFinishHandshake(r0, r6, r5, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02d2, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02cb, code lost:
    
        r0 = javax.net.ssl.SSLEngineResult.Status.OK;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x01a9, code lost:
    
        if (r13 != null) goto L114;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer[] byteBufferArr2, int i3, int i4) throws SSLException {
        int i5;
        int i6;
        int i7;
        int i8;
        int min;
        int min2;
        ByteBuf writeEncryptedData;
        SSLEngineResult sSLEngineResult;
        int i9 = i;
        ObjectUtil.checkNotNull(byteBufferArr, "srcs");
        if (i9 >= byteBufferArr.length || (i5 = i9 + i2) > byteBufferArr.length) {
            throw new IndexOutOfBoundsException("offset: " + i9 + ", length: " + i2 + " (expected: offset <= offset + length <= srcs.length (" + byteBufferArr.length + "))");
        }
        if (byteBufferArr2 == null) {
            throw new IllegalArgumentException("dsts is null");
        }
        if (i3 >= byteBufferArr2.length || (i6 = i3 + i4) > byteBufferArr2.length) {
            throw new IndexOutOfBoundsException("offset: " + i3 + ", length: " + i4 + " (expected: offset <= offset + length <= dsts.length (" + byteBufferArr2.length + "))");
        }
        long j = 0;
        for (int i10 = i3; i10 < i6; i10++) {
            ByteBuffer byteBuffer = byteBufferArr2[i10];
            if (byteBuffer == null) {
                throw new IllegalArgumentException("dsts[" + i10 + "] is null");
            }
            if (byteBuffer.isReadOnly()) {
                throw new ReadOnlyBufferException();
            }
            j += byteBuffer.remaining();
        }
        long j2 = 0;
        for (int i11 = i9; i11 < i5; i11++) {
            if (byteBufferArr[i11] == null) {
                throw new IllegalArgumentException("srcs[" + i11 + "] is null");
            }
            j2 += r14.remaining();
        }
        synchronized (this) {
            if (isInboundDone()) {
                if (!isOutboundDone() && !isDestroyed()) {
                    sSLEngineResult = NEED_WRAP_CLOSED;
                    return sSLEngineResult;
                }
                sSLEngineResult = CLOSED_NOT_HANDSHAKING;
                return sSLEngineResult;
            }
            SSLEngineResult.HandshakeStatus handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            if (this.handshakeState != HandshakeState.FINISHED) {
                if (this.handshakeState != HandshakeState.STARTED_EXPLICITLY) {
                    this.handshakeState = HandshakeState.STARTED_IMPLICITLY;
                }
                handshakeStatus = handshake();
                if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                    return newResult(handshakeStatus, 0, 0);
                }
                if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
                    return NEED_WRAP_OK;
                }
                if (this.isInboundDone) {
                    return NEED_WRAP_CLOSED;
                }
            }
            int sslPending0 = sslPending0();
            if (!this.jdkCompatibilityMode) {
                i7 = sslPending0;
                if (j2 == 0 && i7 <= 0) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                if (j == 0) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, 0, 0);
                }
                i8 = 0;
                min = (int) Math.min(2147483647L, j2);
            } else {
                if (j2 < 5) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                min = SslUtils.getEncryptedPacketLength(byteBufferArr, i);
                if (min == -2) {
                    throw new NotSslRecordException("not an SSL/TLS record");
                }
                int i12 = min - 5;
                i7 = sslPending0;
                if (i12 > j) {
                    if (i12 <= MAX_RECORD_SIZE) {
                        this.session.tryExpandApplicationBufferSize(i12);
                        return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, 0, 0);
                    }
                    throw new SSLException("Illegal packet length: " + i12 + " > " + this.session.getApplicationBufferSize());
                }
                if (j2 < min) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                i8 = 0;
            }
            int i13 = i7;
            int i14 = i3;
            int i15 = i8;
            loop2: while (true) {
                try {
                    ByteBuffer byteBuffer2 = byteBufferArr[i9];
                    int remaining = byteBuffer2.remaining();
                    if (remaining != 0) {
                        min2 = Math.min(min, remaining);
                        writeEncryptedData = writeEncryptedData(byteBuffer2, min2);
                    } else if (i13 <= 0) {
                        i9++;
                        if (i9 >= i5) {
                            break;
                        }
                    } else {
                        min2 = SSL.bioLengthByteBuffer(this.networkBIO);
                        writeEncryptedData = null;
                    }
                    while (true) {
                        try {
                            ByteBuffer byteBuffer3 = byteBufferArr2[i14];
                            if (byteBuffer3.hasRemaining()) {
                                int i16 = i13;
                                int readPlaintextData = readPlaintextData(byteBuffer3);
                                SSLEngineResult.HandshakeStatus handshakeStatus2 = handshakeStatus;
                                int i17 = i5;
                                int bioLengthByteBuffer = min2 - SSL.bioLengthByteBuffer(this.networkBIO);
                                i15 += bioLengthByteBuffer;
                                min -= bioLengthByteBuffer;
                                min2 -= bioLengthByteBuffer;
                                byteBuffer2.position(byteBuffer2.position() + bioLengthByteBuffer);
                                if (readPlaintextData > 0) {
                                    i8 += readPlaintextData;
                                    if (byteBuffer3.hasRemaining()) {
                                        handshakeStatus = handshakeStatus2;
                                        if (min == 0 || this.jdkCompatibilityMode) {
                                            break loop2;
                                        }
                                        i13 = i16;
                                    } else {
                                        i13 = sslPending0();
                                        i14++;
                                        if (i14 >= i6) {
                                            return i13 > 0 ? newResult(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus2, i15, i8) : newResultMayFinishHandshake(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, handshakeStatus2, i15, i8);
                                        }
                                        handshakeStatus = handshakeStatus2;
                                    }
                                    i5 = i17;
                                } else {
                                    handshakeStatus = handshakeStatus2;
                                    int error = SSL.getError(this.ssl, readPlaintextData);
                                    if (error != SSL.SSL_ERROR_WANT_READ && error != SSL.SSL_ERROR_WANT_WRITE) {
                                        if (error == SSL.SSL_ERROR_ZERO_RETURN) {
                                            if (!this.receivedShutdown) {
                                                closeAll();
                                            }
                                            SSLEngineResult newResultMayFinishHandshake = newResultMayFinishHandshake(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, handshakeStatus, i15, i8);
                                            if (writeEncryptedData != null) {
                                                writeEncryptedData.release();
                                            }
                                            return newResultMayFinishHandshake;
                                        }
                                        if (error != SSL.SSL_ERROR_WANT_X509_LOOKUP && error != SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY && error != SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                                            SSLEngineResult sslReadErrorResult = sslReadErrorResult(error, SSL.getLastErrorNumber(), i15, i8);
                                            if (writeEncryptedData != null) {
                                                writeEncryptedData.release();
                                            }
                                            return sslReadErrorResult;
                                        }
                                        SSLEngineResult newResult = newResult(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_TASK, i15, i8);
                                        if (writeEncryptedData != null) {
                                            writeEncryptedData.release();
                                        }
                                        return newResult;
                                    }
                                    i9++;
                                    i5 = i17;
                                    if (i9 < i5) {
                                        if (writeEncryptedData != null) {
                                            writeEncryptedData.release();
                                        }
                                        i13 = i16;
                                    } else if (writeEncryptedData != null) {
                                    }
                                }
                            } else {
                                i14++;
                                if (i14 >= i6) {
                                }
                            }
                        } finally {
                            if (writeEncryptedData != null) {
                                writeEncryptedData.release();
                            }
                        }
                    }
                } finally {
                    SSL.bioClearByteBuffer(this.networkBIO);
                    rejectRemoteInitiatedRenegotiation();
                }
            }
        }
    }

    private SSLEngineResult sslReadErrorResult(int i, int i2, int i3, int i4) throws SSLException {
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            String errorString = SSL.getErrorString(i2);
            Throwable sSLException = this.handshakeState == HandshakeState.FINISHED ? new SSLException(errorString) : new SSLHandshakeException(errorString);
            Throwable th = this.pendingException;
            if (th == null) {
                this.pendingException = sSLException;
            } else {
                ThrowableUtil.addSuppressed(th, sSLException);
            }
            SSL.clearError();
            return new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, i3, i4);
        }
        throw shutdownWithError("SSL_read", i, i2);
    }

    private void closeAll() throws SSLException {
        this.receivedShutdown = true;
        closeOutbound();
        closeInbound();
    }

    private void rejectRemoteInitiatedRenegotiation() throws SSLHandshakeException {
        if (isDestroyed() || SSL.getHandshakeCount(this.ssl) <= 1 || "TLSv1.3".equals(this.session.getProtocol()) || this.handshakeState != HandshakeState.FINISHED) {
            return;
        }
        shutdown();
        throw new SSLHandshakeException("remote-initiated renegotiation not allowed");
    }

    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        return unwrap(byteBufferArr, 0, byteBufferArr.length, byteBufferArr2, 0, byteBufferArr2.length);
    }

    private ByteBuffer[] singleSrcBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleSrcBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleSrcBuffer() {
        this.singleSrcBuffer[0] = null;
    }

    private ByteBuffer[] singleDstBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleDstBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleDstBuffer() {
        this.singleDstBuffer[0] = null;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), 0, 1, byteBufferArr, i, i2);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return wrap(singleSrcBuffer(byteBuffer), byteBuffer2);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
            resetSingleDstBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), singleDstBuffer(byteBuffer2));
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        try {
        } finally {
            resetSingleSrcBuffer();
        }
        return unwrap(singleSrcBuffer(byteBuffer), byteBufferArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized Runnable getDelegatedTask() {
        if (isDestroyed()) {
            return null;
        }
        final Runnable task = SSL.getTask(this.ssl);
        if (task == null) {
            return null;
        }
        return new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine.3
            @Override // java.lang.Runnable
            public void run() {
                if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    return;
                }
                try {
                    task.run();
                } finally {
                    ReferenceCountedOpenSslEngine.this.needTask = false;
                }
            }
        };
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void closeInbound() throws SSLException {
        if (this.isInboundDone) {
            return;
        }
        this.isInboundDone = true;
        if (isOutboundDone()) {
            shutdown();
        }
        if (this.handshakeState != HandshakeState.NOT_STARTED && !this.receivedShutdown) {
            throw new SSLException("Inbound closed before receiving peer's close_notify: possible truncation attack?");
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized boolean isInboundDone() {
        return this.isInboundDone;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void closeOutbound() {
        if (this.outboundClosed) {
            return;
        }
        this.outboundClosed = true;
        if (this.handshakeState != HandshakeState.NOT_STARTED && !isDestroyed()) {
            if ((SSL.getShutdown(this.ssl) & SSL.SSL_SENT_SHUTDOWN) != SSL.SSL_SENT_SHUTDOWN) {
                doSSLShutdown();
            }
        } else {
            shutdown();
        }
    }

    private boolean doSSLShutdown() {
        if (SSL.isInInit(this.ssl) != 0) {
            return false;
        }
        int shutdownSSL = SSL.shutdownSSL(this.ssl);
        if (shutdownSSL >= 0) {
            return true;
        }
        int error = SSL.getError(this.ssl, shutdownSSL);
        if (error == SSL.SSL_ERROR_SYSCALL || error == SSL.SSL_ERROR_SSL) {
            if (logger.isDebugEnabled()) {
                int lastErrorNumber = SSL.getLastErrorNumber();
                logger.debug("SSL_shutdown failed: OpenSSL error: {} {}", Integer.valueOf(lastErrorNumber), SSL.getErrorString(lastErrorNumber));
            }
            shutdown();
            return false;
        }
        SSL.clearError();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
    
        if (io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.bioLengthNonApplication(r4.networkBIO) == 0) goto L9;
     */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized boolean isOutboundDone() {
        boolean z;
        if (this.outboundClosed) {
            if (this.networkBIO != 0) {
            }
            z = true;
        }
        z = false;
        return z;
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getSupportedCipherSuites() {
        return (String[]) OpenSsl.AVAILABLE_CIPHER_SUITES.toArray(new String[0]);
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getEnabledCipherSuites() {
        String[] strArr;
        synchronized (this) {
            if (!isDestroyed()) {
                String[] ciphers = SSL.getCiphers(this.ssl);
                if (isProtocolEnabled(SSL.getOptions(this.ssl), SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
                    strArr = OpenSsl.EXTRA_SUPPORTED_TLS_1_3_CIPHERS;
                } else {
                    strArr = EmptyArrays.EMPTY_STRINGS;
                }
                if (ciphers == null) {
                    return EmptyArrays.EMPTY_STRINGS;
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet(ciphers.length + strArr.length);
                synchronized (this) {
                    for (int i = 0; i < ciphers.length; i++) {
                        String javaCipherSuite = toJavaCipherSuite(ciphers[i]);
                        if (javaCipherSuite == null) {
                            javaCipherSuite = ciphers[i];
                        }
                        if (OpenSsl.isTlsv13Supported() || !SslUtils.isTLSv13Cipher(javaCipherSuite)) {
                            linkedHashSet.add(javaCipherSuite);
                        }
                    }
                    Collections.addAll(linkedHashSet, strArr);
                }
                return (String[]) linkedHashSet.toArray(new String[0]);
            }
            return EmptyArrays.EMPTY_STRINGS;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnabledCipherSuites(String[] strArr) {
        ObjectUtil.checkNotNull(strArr, "cipherSuites");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        CipherSuiteConverter.convertToCipherStrings(Arrays.asList(strArr), sb, sb2, OpenSsl.isBoringSSL());
        String sb3 = sb.toString();
        String sb4 = sb2.toString();
        if (!OpenSsl.isTlsv13Supported() && !sb4.isEmpty()) {
            throw new IllegalArgumentException("TLSv1.3 is not supported by this java version.");
        }
        synchronized (this) {
            if (!isDestroyed()) {
                try {
                    SSL.setCipherSuites(this.ssl, sb3, false);
                    if (OpenSsl.isTlsv13Supported()) {
                        SSL.setCipherSuites(this.ssl, sb4, true);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("failed to enable cipher suites: " + sb3, e);
                }
            } else {
                throw new IllegalStateException("failed to enable cipher suites: " + sb3);
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getSupportedProtocols() {
        return (String[]) OpenSsl.SUPPORTED_PROTOCOLS_SET.toArray(new String[0]);
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getEnabledProtocols() {
        ArrayList arrayList = new ArrayList(6);
        arrayList.add("SSLv2Hello");
        synchronized (this) {
            if (!isDestroyed()) {
                int options = SSL.getOptions(this.ssl);
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1, "TLSv1")) {
                    arrayList.add("TLSv1");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_1, "TLSv1.1")) {
                    arrayList.add("TLSv1.1");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_2, "TLSv1.2")) {
                    arrayList.add("TLSv1.2");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_TLSv1_3, "TLSv1.3")) {
                    arrayList.add("TLSv1.3");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv2, "SSLv2")) {
                    arrayList.add("SSLv2");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv3, "SSLv3")) {
                    arrayList.add("SSLv3");
                }
                return (String[]) arrayList.toArray(new String[0]);
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
    }

    private static boolean isProtocolEnabled(int i, int i2, String str) {
        return (i & i2) == 0 && OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str);
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnabledProtocols(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException();
        }
        int length = OPENSSL_OP_NO_PROTOCOLS.length;
        int i = 0;
        for (String str : strArr) {
            if (!OpenSsl.SUPPORTED_PROTOCOLS_SET.contains(str)) {
                throw new IllegalArgumentException("Protocol " + str + " is not supported.");
            }
            if (str.equals("SSLv2")) {
                if (length > 0) {
                    length = 0;
                }
                if (i < 0) {
                    i = 0;
                }
            } else if (str.equals("SSLv3")) {
                if (length > 1) {
                    length = 1;
                }
                if (i < 1) {
                    i = 1;
                }
            } else if (str.equals("TLSv1")) {
                if (length > 2) {
                    length = 2;
                }
                if (i < 2) {
                    i = 2;
                }
            } else if (str.equals("TLSv1.1")) {
                if (length > 3) {
                    length = 3;
                }
                if (i < 3) {
                    i = 3;
                }
            } else if (str.equals("TLSv1.2")) {
                if (length > 4) {
                    length = 4;
                }
                if (i < 4) {
                    i = 4;
                }
            } else if (str.equals("TLSv1.3")) {
                if (length > 5) {
                    length = 5;
                }
                if (i < 5) {
                    i = 5;
                }
            }
        }
        synchronized (this) {
            if (!isDestroyed()) {
                SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_NO_TLSv1 | SSL.SSL_OP_NO_TLSv1_1 | SSL.SSL_OP_NO_TLSv1_2 | SSL.SSL_OP_NO_TLSv1_3);
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    i2 |= OPENSSL_OP_NO_PROTOCOLS[i3];
                }
                for (int i4 = i + 1; i4 < OPENSSL_OP_NO_PROTOCOLS.length; i4++) {
                    i2 |= OPENSSL_OP_NO_PROTOCOLS[i4];
                }
                SSL.setOptions(this.ssl, i2);
            } else {
                throw new IllegalStateException("failed to enable protocols: " + Arrays.asList(strArr));
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final SSLSession getSession() {
        return this.session;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void beginHandshake() throws SSLException {
        int i = C66484.f8420xea902ccf[this.handshakeState.ordinal()];
        if (i == 1) {
            this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
            if (handshake() == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                this.needTask = true;
            }
            calculateMaxWrapOverhead();
        } else {
            if (i == 2) {
                throw new SSLException("renegotiation unsupported");
            }
            if (i == 3) {
                checkEngineClosed();
                this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
                calculateMaxWrapOverhead();
            } else if (i != 4) {
                throw new Error();
            }
        }
    }

    private void checkEngineClosed() throws SSLException {
        if (isDestroyed()) {
            throw new SSLException("engine closed");
        }
    }

    private static SSLEngineResult.HandshakeStatus pendingStatus(int i) {
        return i > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    private SSLEngineResult.HandshakeStatus handshakeException() throws SSLException {
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        Throwable th = this.pendingException;
        this.pendingException = null;
        shutdown();
        if (th instanceof SSLHandshakeException) {
            throw ((SSLHandshakeException) th);
        }
        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("General OpenSslEngine problem");
        sSLHandshakeException.initCause(th);
        throw sSLHandshakeException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void initHandshakeException(Throwable th) {
        Throwable th2 = this.pendingException;
        if (th2 == null) {
            this.pendingException = th;
        } else {
            ThrowableUtil.addSuppressed(th2, th);
        }
    }

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        if (this.needTask) {
            return SSLEngineResult.HandshakeStatus.NEED_TASK;
        }
        if (this.handshakeState == HandshakeState.FINISHED) {
            return SSLEngineResult.HandshakeStatus.FINISHED;
        }
        checkEngineClosed();
        if (this.pendingException != null) {
            if (SSL.doHandshake(this.ssl) <= 0) {
                SSL.clearError();
            }
            return handshakeException();
        }
        this.engineMap.add(this);
        if (this.lastAccessed == -1) {
            this.lastAccessed = System.currentTimeMillis();
        }
        int doHandshake = SSL.doHandshake(this.ssl);
        if (doHandshake <= 0) {
            int error = SSL.getError(this.ssl, doHandshake);
            if (error == SSL.SSL_ERROR_WANT_READ || error == SSL.SSL_ERROR_WANT_WRITE) {
                return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
            }
            if (error == SSL.SSL_ERROR_WANT_X509_LOOKUP || error == SSL.SSL_ERROR_WANT_CERTIFICATE_VERIFY || error == SSL.SSL_ERROR_WANT_PRIVATE_KEY_OPERATION) {
                return SSLEngineResult.HandshakeStatus.NEED_TASK;
            }
            if (this.pendingException != null) {
                return handshakeException();
            }
            throw shutdownWithError("SSL_do_handshake", error);
        }
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            return SSLEngineResult.HandshakeStatus.NEED_WRAP;
        }
        this.session.handshakeFinished();
        return SSLEngineResult.HandshakeStatus.FINISHED;
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        return (handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING || this.handshakeState == HandshakeState.FINISHED) ? handshakeStatus : handshake();
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        if (needPendingStatus()) {
            if (this.needTask) {
                return SSLEngineResult.HandshakeStatus.NEED_TASK;
            }
            return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
        }
        return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        if (needPendingStatus()) {
            if (this.needTask) {
                return SSLEngineResult.HandshakeStatus.NEED_TASK;
            }
            return pendingStatus(i);
        }
        return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    private boolean needPendingStatus() {
        return (this.handshakeState == HandshakeState.NOT_STARTED || isDestroyed() || (this.handshakeState == HandshakeState.FINISHED && !isInboundDone() && !isOutboundDone())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toJavaCipherSuite(String str) {
        if (str == null) {
            return null;
        }
        return CipherSuiteConverter.toJava(str, toJavaCipherSuitePrefix(SSL.getVersion(this.ssl)));
    }

    private static String toJavaCipherSuitePrefix(String str) {
        char c = 0;
        if (str != null && !str.isEmpty()) {
            c = str.charAt(0);
        }
        return c != 'S' ? c != 'T' ? "UNKNOWN" : "TLS" : "SSL";
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setUseClientMode(boolean z) {
        if (z != this.clientMode) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getUseClientMode() {
        return this.clientMode;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setNeedClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.REQUIRE : ClientAuth.NONE);
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getNeedClientAuth() {
        return this.clientAuth == ClientAuth.REQUIRE;
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setWantClientAuth(boolean z) {
        setClientAuth(z ? ClientAuth.OPTIONAL : ClientAuth.NONE);
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getWantClientAuth() {
        return this.clientAuth == ClientAuth.OPTIONAL;
    }

    public final synchronized void setVerify(int i, int i2) {
        if (!isDestroyed()) {
            SSL.setVerify(this.ssl, i, i2);
        }
    }

    private void setClientAuth(ClientAuth clientAuth) {
        if (this.clientMode) {
            return;
        }
        synchronized (this) {
            if (this.clientAuth == clientAuth) {
                return;
            }
            if (!isDestroyed()) {
                int i = C66484.$SwitchMap$io$netty$handler$ssl$ClientAuth[clientAuth.ordinal()];
                if (i == 1) {
                    SSL.setVerify(this.ssl, 0, 10);
                } else if (i == 2) {
                    SSL.setVerify(this.ssl, 2, 10);
                } else if (i == 3) {
                    SSL.setVerify(this.ssl, 1, 10);
                } else {
                    throw new Error(clientAuth.toString());
                }
            }
            this.clientAuth = clientAuth;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnableSessionCreation(boolean z) {
        if (z) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLParameters getSSLParameters() {
        SSLParameters sSLParameters;
        sSLParameters = super.getSSLParameters();
        int javaVersion = PlatformDependent.javaVersion();
        if (javaVersion >= 7) {
            sSLParameters.setEndpointIdentificationAlgorithm(this.endPointIdentificationAlgorithm);
            Java7SslParametersUtils.setAlgorithmConstraints(sSLParameters, this.algorithmConstraints);
            if (javaVersion >= 8) {
                if (this.sniHostNames != null) {
                    Java8SslUtils.setSniHostNames(sSLParameters, this.sniHostNames);
                }
                if (!isDestroyed()) {
                    Java8SslUtils.setUseCipherSuitesOrder(sSLParameters, (SSL.getOptions(this.ssl) & SSL.SSL_OP_CIPHER_SERVER_PREFERENCE) != 0);
                }
                Java8SslUtils.setSNIMatchers(sSLParameters, this.matchers);
            }
        }
        return sSLParameters;
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized void setSSLParameters(SSLParameters sSLParameters) {
        int javaVersion = PlatformDependent.javaVersion();
        if (javaVersion >= 7) {
            if (sSLParameters.getAlgorithmConstraints() != null) {
                throw new IllegalArgumentException("AlgorithmConstraints are not supported.");
            }
            boolean isDestroyed = isDestroyed();
            if (javaVersion >= 8) {
                if (!isDestroyed) {
                    if (this.clientMode) {
                        List<String> sniHostNames = Java8SslUtils.getSniHostNames(sSLParameters);
                        Iterator<String> it = sniHostNames.iterator();
                        while (it.hasNext()) {
                            SSL.setTlsExtHostName(this.ssl, it.next());
                        }
                        this.sniHostNames = sniHostNames;
                    }
                    if (Java8SslUtils.getUseCipherSuitesOrder(sSLParameters)) {
                        SSL.setOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                    } else {
                        SSL.clearOptions(this.ssl, SSL.SSL_OP_CIPHER_SERVER_PREFERENCE);
                    }
                }
                this.matchers = sSLParameters.getSNIMatchers();
            }
            String endpointIdentificationAlgorithm = sSLParameters.getEndpointIdentificationAlgorithm();
            if (!isDestroyed && this.clientMode && isEndPointVerificationEnabled(endpointIdentificationAlgorithm)) {
                SSL.setVerify(this.ssl, 2, -1);
            }
            this.endPointIdentificationAlgorithm = endpointIdentificationAlgorithm;
            this.algorithmConstraints = sSLParameters.getAlgorithmConstraints();
        }
        super.setSSLParameters(sSLParameters);
    }

    private static boolean isEndPointVerificationEnabled(String str) {
        return (str == null || str.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean checkSniHostnameMatch(byte[] bArr) {
        return Java8SslUtils.checkSniHostnameMatch(this.matchers, bArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolAccessor
    public String getNegotiatedApplicationProtocol() {
        return this.applicationProtocol;
    }

    private static long bufferAddress(ByteBuffer byteBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.directBufferAddress(byteBuffer);
        }
        return Buffer.address(byteBuffer);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class DefaultOpenSslSession implements OpenSslSession {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile int applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        private String cipher;
        private long creationTime;

        /* renamed from: id */
        private byte[] f8421id;
        private Certificate[] peerCerts;
        private String protocol;
        private final OpenSslSessionContext sessionContext;
        private Map<String, Object> values;
        private X509Certificate[] x509PeerCerts;

        DefaultOpenSslSession(OpenSslSessionContext openSslSessionContext) {
            this.sessionContext = openSslSessionContext;
        }

        private SSLSessionBindingEvent newSSLSessionBindingEvent(String str) {
            return new SSLSessionBindingEvent(ReferenceCountedOpenSslEngine.this.session, str);
        }

        @Override // javax.net.ssl.SSLSession
        public byte[] getId() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.f8421id == null) {
                    return EmptyArrays.EMPTY_BYTES;
                }
                return (byte[]) this.f8421id.clone();
            }
        }

        @Override // javax.net.ssl.SSLSession
        public SSLSessionContext getSessionContext() {
            return this.sessionContext;
        }

        @Override // javax.net.ssl.SSLSession
        public long getCreationTime() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.creationTime == 0 && !ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.creationTime = SSL.getTime(ReferenceCountedOpenSslEngine.this.ssl) * 1000;
                }
            }
            return this.creationTime;
        }

        @Override // javax.net.ssl.SSLSession
        public long getLastAccessedTime() {
            long j = ReferenceCountedOpenSslEngine.this.lastAccessed;
            return j == -1 ? getCreationTime() : j;
        }

        @Override // javax.net.ssl.SSLSession
        public void invalidate() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    SSL.setTimeout(ReferenceCountedOpenSslEngine.this.ssl, 0L);
                }
            }
        }

        @Override // javax.net.ssl.SSLSession
        public boolean isValid() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    return false;
                }
                return System.currentTimeMillis() - (SSL.getTimeout(ReferenceCountedOpenSslEngine.this.ssl) * 1000) < SSL.getTime(ReferenceCountedOpenSslEngine.this.ssl) * 1000;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void putValue(String str, Object obj) {
            Object put;
            ObjectUtil.checkNotNull(str, "name");
            ObjectUtil.checkNotNull(obj, ES6Iterator.VALUE_PROPERTY);
            synchronized (this) {
                Map map = this.values;
                if (map == null) {
                    map = new HashMap(2);
                    this.values = map;
                }
                put = map.put(str, obj);
            }
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueBound(newSSLSessionBindingEvent(str));
            }
            notifyUnbound(put, str);
        }

        @Override // javax.net.ssl.SSLSession
        public Object getValue(String str) {
            ObjectUtil.checkNotNull(str, "name");
            synchronized (this) {
                if (this.values == null) {
                    return null;
                }
                return this.values.get(str);
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void removeValue(String str) {
            ObjectUtil.checkNotNull(str, "name");
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map == null) {
                    return;
                }
                notifyUnbound(map.remove(str), str);
            }
        }

        @Override // javax.net.ssl.SSLSession
        public String[] getValueNames() {
            synchronized (this) {
                Map<String, Object> map = this.values;
                if (map != null && !map.isEmpty()) {
                    return (String[]) map.keySet().toArray(new String[0]);
                }
                return EmptyArrays.EMPTY_STRINGS;
            }
        }

        private void notifyUnbound(Object obj, String str) {
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueUnbound(newSSLSessionBindingEvent(str));
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSession
        public void handshakeFinished() throws SSLException {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.f8421id = SSL.getSessionId(ReferenceCountedOpenSslEngine.this.ssl);
                    this.cipher = ReferenceCountedOpenSslEngine.this.toJavaCipherSuite(SSL.getCipherForSSL(ReferenceCountedOpenSslEngine.this.ssl));
                    this.protocol = SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl);
                    initPeerCerts();
                    selectApplicationProtocol();
                    ReferenceCountedOpenSslEngine.this.calculateMaxWrapOverhead();
                    ReferenceCountedOpenSslEngine.this.handshakeState = HandshakeState.FINISHED;
                } else {
                    throw new SSLException("Already closed");
                }
            }
        }

        private void initPeerCerts() {
            byte[][] peerCertChain = SSL.getPeerCertChain(ReferenceCountedOpenSslEngine.this.ssl);
            if (ReferenceCountedOpenSslEngine.this.clientMode) {
                if (ReferenceCountedOpenSslEngine.isEmpty(peerCertChain)) {
                    this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                    this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
                    return;
                } else {
                    this.peerCerts = new Certificate[peerCertChain.length];
                    this.x509PeerCerts = new X509Certificate[peerCertChain.length];
                    initCerts(peerCertChain, 0);
                    return;
                }
            }
            byte[] peerCertificate = SSL.getPeerCertificate(ReferenceCountedOpenSslEngine.this.ssl);
            if (!ReferenceCountedOpenSslEngine.isEmpty(peerCertificate)) {
                if (ReferenceCountedOpenSslEngine.isEmpty(peerCertChain)) {
                    this.peerCerts = new Certificate[]{new OpenSslX509Certificate(peerCertificate)};
                    this.x509PeerCerts = new X509Certificate[]{new OpenSslJavaxX509Certificate(peerCertificate)};
                    return;
                }
                this.peerCerts = new Certificate[peerCertChain.length + 1];
                this.x509PeerCerts = new X509Certificate[peerCertChain.length + 1];
                this.peerCerts[0] = new OpenSslX509Certificate(peerCertificate);
                this.x509PeerCerts[0] = new OpenSslJavaxX509Certificate(peerCertificate);
                initCerts(peerCertChain, 1);
                return;
            }
            this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
            this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
        }

        private void initCerts(byte[][] bArr, int i) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                int i3 = i + i2;
                this.peerCerts[i3] = new OpenSslX509Certificate(bArr[i2]);
                this.x509PeerCerts[i3] = new OpenSslJavaxX509Certificate(bArr[i2]);
            }
        }

        private void selectApplicationProtocol() throws SSLException {
            ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior = ReferenceCountedOpenSslEngine.this.apn.selectedListenerFailureBehavior();
            List<String> protocols = ReferenceCountedOpenSslEngine.this.apn.protocols();
            int i = C66484.f8419xc16482e4[ReferenceCountedOpenSslEngine.this.apn.protocol().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    String alpnSelected = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.this.ssl);
                    if (alpnSelected != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected);
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    String nextProtoNegotiated = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.this.ssl);
                    if (nextProtoNegotiated != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, nextProtoNegotiated);
                        return;
                    }
                    return;
                }
                if (i == 4) {
                    String alpnSelected2 = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.this.ssl);
                    if (alpnSelected2 == null) {
                        alpnSelected2 = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.this.ssl);
                    }
                    if (alpnSelected2 != null) {
                        ReferenceCountedOpenSslEngine.this.applicationProtocol = selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected2);
                        return;
                    }
                    return;
                }
                throw new Error();
            }
        }

        private String selectApplicationProtocol(List<String> list, ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior, String str) throws SSLException {
            if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT) {
                return str;
            }
            int size = list.size();
            if (list.contains(str)) {
                return str;
            }
            if (selectedListenerFailureBehavior == ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL) {
                return list.get(size - 1);
            }
            throw new SSLException("unknown protocol " + str);
        }

        @Override // javax.net.ssl.SSLSession
        public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
            Certificate[] certificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.isEmpty(this.peerCerts)) {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
                certificateArr = (Certificate[]) this.peerCerts.clone();
            }
            return certificateArr;
        }

        @Override // javax.net.ssl.SSLSession
        public Certificate[] getLocalCertificates() {
            Certificate[] certificateArr = ReferenceCountedOpenSslEngine.this.localCertificateChain;
            if (certificateArr == null) {
                return null;
            }
            return (Certificate[]) certificateArr.clone();
        }

        @Override // javax.net.ssl.SSLSession
        public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
            X509Certificate[] x509CertificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.isEmpty(this.x509PeerCerts)) {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
                x509CertificateArr = (X509Certificate[]) this.x509PeerCerts.clone();
            }
            return x509CertificateArr;
        }

        @Override // javax.net.ssl.SSLSession
        public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
            return ((java.security.cert.X509Certificate) getPeerCertificates()[0]).getSubjectX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public Principal getLocalPrincipal() {
            Certificate[] certificateArr = ReferenceCountedOpenSslEngine.this.localCertificateChain;
            if (certificateArr == null || certificateArr.length == 0) {
                return null;
            }
            return ((java.security.cert.X509Certificate) certificateArr[0]).getIssuerX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public String getCipherSuite() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.cipher == null) {
                    return "SSL_NULL_WITH_NULL_NULL";
                }
                return this.cipher;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public String getProtocol() {
            String str = this.protocol;
            if (str == null) {
                synchronized (ReferenceCountedOpenSslEngine.this) {
                    str = !ReferenceCountedOpenSslEngine.this.isDestroyed() ? SSL.getVersion(ReferenceCountedOpenSslEngine.this.ssl) : "";
                }
            }
            return str;
        }

        @Override // javax.net.ssl.SSLSession
        public String getPeerHost() {
            return ReferenceCountedOpenSslEngine.this.getPeerHost();
        }

        @Override // javax.net.ssl.SSLSession
        public int getPeerPort() {
            return ReferenceCountedOpenSslEngine.this.getPeerPort();
        }

        @Override // javax.net.ssl.SSLSession
        public int getPacketBufferSize() {
            return ReferenceCountedOpenSslEngine.this.maxEncryptedPacketLength();
        }

        @Override // javax.net.ssl.SSLSession
        public int getApplicationBufferSize() {
            return this.applicationBufferSize;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSession
        public void tryExpandApplicationBufferSize(int i) {
            if (i <= ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH || this.applicationBufferSize == ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE) {
                return;
            }
            this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine$4 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C66484 {

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol */
        static final /* synthetic */ int[] f8419xc16482e4 = new int[ApplicationProtocolConfig.Protocol.values().length];
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ClientAuth;

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState */
        static final /* synthetic */ int[] f8420xea902ccf;

        static {
            try {
                f8419xc16482e4[ApplicationProtocolConfig.Protocol.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8419xc16482e4[ApplicationProtocolConfig.Protocol.ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8419xc16482e4[ApplicationProtocolConfig.Protocol.NPN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8419xc16482e4[ApplicationProtocolConfig.Protocol.NPN_AND_ALPN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$io$netty$handler$ssl$ClientAuth = new int[ClientAuth.values().length];
            try {
                $SwitchMap$io$netty$handler$ssl$ClientAuth[ClientAuth.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ClientAuth[ClientAuth.REQUIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$ClientAuth[ClientAuth.OPTIONAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            f8420xea902ccf = new int[HandshakeState.values().length];
            try {
                f8420xea902ccf[HandshakeState.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8420xea902ccf[HandshakeState.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8420xea902ccf[HandshakeState.STARTED_IMPLICITLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8420xea902ccf[HandshakeState.STARTED_EXPLICITLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
