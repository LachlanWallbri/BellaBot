package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.internal.tcnative.Buffer;
import io.netty.internal.tcnative.SSL;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.Lock;
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
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ReferenceCountedOpenSslEngine extends SSLEngine implements ReferenceCounted, ApplicationProtocolAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DEFAULT_HOSTNAME_VALIDATION_FLAGS = 0;
    private static final String INVALID_CIPHER = "SSL_NULL_WITH_NULL_NULL";
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV2 = 0;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_SSLV3 = 1;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1 = 2;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_1 = 3;
    private static final int OPENSSL_OP_NO_PROTOCOL_INDEX_TLSv1_2 = 4;
    private Object algorithmConstraints;
    private final ByteBufAllocator alloc;
    private final OpenSslApplicationProtocolNegotiator apn;
    private volatile String applicationProtocol;
    private boolean certificateSet;
    private volatile ClientAuth clientAuth;
    private final boolean clientMode;
    private volatile int destroyed;
    private final boolean enableOcsp;
    private String endPointIdentificationAlgorithm;
    private final OpenSslEngineMap engineMap;
    SSLHandshakeException handshakeException;
    private HandshakeState handshakeState;
    private boolean isInboundDone;
    final boolean jdkCompatibilityMode;
    private final OpenSslKeyMaterialManager keyMaterialManager;
    private volatile long lastAccessed;
    private final ResourceLeakTracker<ReferenceCountedOpenSslEngine> leak;
    private final Certificate[] localCerts;
    private volatile Collection<?> matchers;
    private int maxWrapBufferSize;
    private int maxWrapOverhead;
    private long networkBIO;
    private boolean outboundClosed;
    private boolean receivedShutdown;
    private final AbstractReferenceCounted refCnt;
    private final OpenSslSession session;
    private final ByteBuffer[] singleDstBuffer;
    private final ByteBuffer[] singleSrcBuffer;
    private List<String> sniHostNames;
    private long ssl;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslEngine.class);
    private static final SSLException BEGIN_HANDSHAKE_ENGINE_CLOSED = (SSLException) ThrowableUtil.unknownStackTrace(new SSLException("engine closed"), ReferenceCountedOpenSslEngine.class, "beginHandshake()");
    private static final SSLException HANDSHAKE_ENGINE_CLOSED = (SSLException) ThrowableUtil.unknownStackTrace(new SSLException("engine closed"), ReferenceCountedOpenSslEngine.class, "handshake()");
    private static final SSLException RENEGOTIATION_UNSUPPORTED = (SSLException) ThrowableUtil.unknownStackTrace(new SSLException("renegotiation unsupported"), ReferenceCountedOpenSslEngine.class, "beginHandshake()");
    private static final ResourceLeakDetector<ReferenceCountedOpenSslEngine> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedOpenSslEngine.class);
    private static final int[] OPENSSL_OP_NO_PROTOCOLS = {SSL.SSL_OP_NO_SSLv2, SSL.SSL_OP_NO_SSLv3, SSL.SSL_OP_NO_TLSv1, SSL.SSL_OP_NO_TLSv1_1, SSL.SSL_OP_NO_TLSv1_2};
    static final int MAX_PLAINTEXT_LENGTH = SSL.SSL_MAX_PLAINTEXT_LENGTH;
    private static final int MAX_RECORD_SIZE = SSL.SSL_MAX_RECORD_LENGTH;
    private static final AtomicIntegerFieldUpdater<ReferenceCountedOpenSslEngine> DESTROYED_UPDATER = AtomicIntegerFieldUpdater.newUpdater(ReferenceCountedOpenSslEngine.class, "destroyed");
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum HandshakeState {
        NOT_STARTED,
        STARTED_IMPLICITLY,
        STARTED_EXPLICITLY,
        FINISHED
    }

    @Override // javax.net.ssl.SSLEngine
    public final Runnable getDelegatedTask() {
        return null;
    }

    @Override // javax.net.ssl.SSLEngine
    public final boolean getEnableSessionCreation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslEngine(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, ByteBufAllocator byteBufAllocator, String str, int i, boolean z, boolean z2) {
        super(str, i);
        this.handshakeState = HandshakeState.NOT_STARTED;
        this.refCnt = new AbstractReferenceCounted() { // from class: io.netty.handler.ssl.ReferenceCountedOpenSslEngine.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.netty.util.ReferenceCounted
            public ReferenceCounted touch(Object obj) {
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.record(obj);
                }
                return ReferenceCountedOpenSslEngine.this;
            }

            @Override // io.netty.util.AbstractReferenceCounted
            protected void deallocate() {
                ReferenceCountedOpenSslEngine.this.shutdown();
                if (ReferenceCountedOpenSslEngine.this.leak != null) {
                    ReferenceCountedOpenSslEngine.this.leak.close(ReferenceCountedOpenSslEngine.this);
                }
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
        this.session = new OpenSslSession(referenceCountedOpenSslContext.sessionContext());
        this.clientMode = referenceCountedOpenSslContext.isClient();
        this.engineMap = referenceCountedOpenSslContext.engineMap;
        this.localCerts = referenceCountedOpenSslContext.keyCertChain;
        this.keyMaterialManager = referenceCountedOpenSslContext.keyMaterialManager();
        this.enableOcsp = referenceCountedOpenSslContext.enableOcsp;
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
                    this.networkBIO = SSL.bioNewByteBuffer(newSSL, referenceCountedOpenSslContext.getBioNonApplicationBufferSize());
                    setClientAuth(this.clientMode ? ClientAuth.NONE : referenceCountedOpenSslContext.clientAuth);
                    if (referenceCountedOpenSslContext.protocols != null) {
                        setEnabledProtocols(referenceCountedOpenSslContext.protocols);
                    }
                    if (this.clientMode && str != null) {
                        SSL.setTlsExtHostName(this.ssl, str);
                    }
                    if (this.enableOcsp) {
                        SSL.enableOcsp(this.ssl);
                    }
                    if (!z) {
                        SSL.setMode(this.ssl, SSL.getMode(this.ssl) | SSL.SSL_MODE_ENABLE_PARTIAL_WRITE);
                    }
                    calculateMaxWrapOverhead();
                } catch (Throwable th) {
                    SSL.freeSSL(this.ssl);
                    PlatformDependent.throwException(th);
                }
            }
            this.leak = z2 ? leakDetector.track(this) : null;
        } finally {
            readLock.unlock();
        }
    }

    public void setOcspResponse(byte[] bArr) {
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (this.clientMode) {
            throw new IllegalStateException("Not a server SSLEngine");
        }
        synchronized (this) {
            SSL.setOcspResponse(this.ssl, bArr);
        }
    }

    public byte[] getOcspResponse() {
        byte[] ocspResponse;
        if (!this.enableOcsp) {
            throw new IllegalStateException("OCSP stapling is not enabled");
        }
        if (!this.clientMode) {
            throw new IllegalStateException("Not a client SSLEngine");
        }
        synchronized (this) {
            ocspResponse = SSL.getOcspResponse(this.ssl);
        }
        return ocspResponse;
    }

    @Override // io.netty.util.ReferenceCounted
    public final int refCnt() {
        return this.refCnt.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted retain() {
        this.refCnt.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted retain(int i) {
        this.refCnt.retain(i);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted touch() {
        this.refCnt.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted touch(Object obj) {
        this.refCnt.touch(obj);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final boolean release() {
        return this.refCnt.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public final boolean release(int i) {
        return this.refCnt.release(i);
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLSession getHandshakeSession() {
        int i = C73352.f8556xea902ccf[this.handshakeState.ordinal()];
        if (i == 1 || i == 2) {
            return null;
        }
        return this.session;
    }

    public final synchronized long sslPointer() {
        return this.ssl;
    }

    public final synchronized void shutdown() {
        if (DESTROYED_UPDATER.compareAndSet(this, 0, 1)) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int maxEncryptedPacketLength0() {
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

    /* JADX WARN: Removed duplicated region for block: B:234:0x040e A[Catch: all -> 0x0426, TryCatch #3 {, blocks: (B:11:0x0013, B:13:0x0019, B:15:0x001f, B:18:0x0026, B:19:0x002b, B:22:0x0029, B:34:0x007a, B:36:0x0081, B:37:0x0098, B:39:0x008a, B:43:0x00a6, B:45:0x00ad, B:46:0x00c4, B:48:0x00b6, B:52:0x00d3, B:54:0x00da, B:55:0x00f1, B:57:0x00e3, B:232:0x0407, B:234:0x040e, B:235:0x0425, B:236:0x041d, B:241:0x0120, B:243:0x0127, B:244:0x013e, B:246:0x0130, B:72:0x016c, B:74:0x0173, B:75:0x018a, B:77:0x017c, B:85:0x019b, B:87:0x01a2, B:88:0x01b9, B:90:0x01ab, B:96:0x01c9, B:98:0x01d0, B:99:0x01e7, B:101:0x01d9, B:128:0x0245, B:130:0x024c, B:131:0x0263, B:133:0x0255, B:156:0x02e2, B:158:0x02e9, B:159:0x0300, B:161:0x02f2, B:176:0x033e, B:178:0x0345, B:179:0x035c, B:181:0x034e, B:185:0x0364, B:187:0x036b, B:188:0x0382, B:190:0x0374, B:194:0x038e, B:196:0x0395, B:197:0x03ac, B:199:0x039e, B:203:0x03b8, B:205:0x03bf, B:206:0x03d6, B:208:0x03c8, B:216:0x029b, B:218:0x02a2, B:219:0x02b9, B:221:0x02ab, B:224:0x03e3, B:226:0x03ea, B:227:0x0401, B:229:0x03f3), top: B:10:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x041d A[Catch: all -> 0x0426, TryCatch #3 {, blocks: (B:11:0x0013, B:13:0x0019, B:15:0x001f, B:18:0x0026, B:19:0x002b, B:22:0x0029, B:34:0x007a, B:36:0x0081, B:37:0x0098, B:39:0x008a, B:43:0x00a6, B:45:0x00ad, B:46:0x00c4, B:48:0x00b6, B:52:0x00d3, B:54:0x00da, B:55:0x00f1, B:57:0x00e3, B:232:0x0407, B:234:0x040e, B:235:0x0425, B:236:0x041d, B:241:0x0120, B:243:0x0127, B:244:0x013e, B:246:0x0130, B:72:0x016c, B:74:0x0173, B:75:0x018a, B:77:0x017c, B:85:0x019b, B:87:0x01a2, B:88:0x01b9, B:90:0x01ab, B:96:0x01c9, B:98:0x01d0, B:99:0x01e7, B:101:0x01d9, B:128:0x0245, B:130:0x024c, B:131:0x0263, B:133:0x0255, B:156:0x02e2, B:158:0x02e9, B:159:0x0300, B:161:0x02f2, B:176:0x033e, B:178:0x0345, B:179:0x035c, B:181:0x034e, B:185:0x0364, B:187:0x036b, B:188:0x0382, B:190:0x0374, B:194:0x038e, B:196:0x0395, B:197:0x03ac, B:199:0x039e, B:203:0x03b8, B:205:0x03bf, B:206:0x03d6, B:208:0x03c8, B:216:0x029b, B:218:0x02a2, B:219:0x02b9, B:221:0x02ab, B:224:0x03e3, B:226:0x03ea, B:227:0x0401, B:229:0x03f3), top: B:10:0x0013 }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer byteBuffer) throws SSLException {
        int i3;
        int i4;
        int writePlaintextData;
        SSLEngineResult sSLEngineResult;
        if (byteBufferArr == null) {
            throw new IllegalArgumentException("srcs is null");
        }
        if (byteBuffer == null) {
            throw new IllegalArgumentException("dst is null");
        }
        if (i >= byteBufferArr.length || (i3 = i + i2) > byteBufferArr.length) {
            throw new IndexOutOfBoundsException("offset: " + i + ", length: " + i2 + " (expected: offset <= offset + length <= srcs.length (" + byteBufferArr.length + "))");
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
            ByteBuf byteBuf = null;
            int i5 = 0;
            try {
                if (byteBuffer.isDirect()) {
                    SSL.bioSetByteBuffer(this.networkBIO, bufferAddress(byteBuffer) + byteBuffer.position(), byteBuffer.remaining(), true);
                } else {
                    byteBuf = this.alloc.directBuffer(byteBuffer.remaining());
                    SSL.bioSetByteBuffer(this.networkBIO, OpenSsl.memoryAddress(byteBuf), byteBuf.writableBytes(), true);
                }
                int bioLengthByteBuffer = SSL.bioLengthByteBuffer(this.networkBIO);
                try {
                    try {
                        if (this.outboundClosed) {
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
                        SSLEngineResult.HandshakeStatus handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                        if (this.handshakeState != HandshakeState.FINISHED) {
                            if (this.handshakeState != HandshakeState.STARTED_EXPLICITLY) {
                                this.handshakeState = HandshakeState.STARTED_IMPLICITLY;
                            }
                            int bioFlushByteBuffer2 = SSL.bioFlushByteBuffer(this.networkBIO);
                            if (bioFlushByteBuffer2 > 0) {
                                try {
                                    if (this.handshakeException != null) {
                                        SSLEngineResult newResult = newResult(SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, bioFlushByteBuffer2);
                                        SSL.bioClearByteBuffer(this.networkBIO);
                                        if (byteBuf == null) {
                                            byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer2);
                                        } else {
                                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer2));
                                            byteBuf.release();
                                        }
                                        return newResult;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    i5 = bioFlushByteBuffer2;
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                    }
                                    throw th;
                                }
                            }
                            SSLEngineResult.HandshakeStatus handshake = handshake();
                            int bioLengthByteBuffer3 = bioLengthByteBuffer - SSL.bioLengthByteBuffer(this.networkBIO);
                            if (bioLengthByteBuffer3 > 0) {
                                SSLEngineResult newResult2 = newResult(mayFinishHandshake(handshake != SSLEngineResult.HandshakeStatus.FINISHED ? bioLengthByteBuffer3 == bioLengthByteBuffer ? SSLEngineResult.HandshakeStatus.NEED_WRAP : getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO)) : SSLEngineResult.HandshakeStatus.FINISHED), 0, bioLengthByteBuffer3);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + bioLengthByteBuffer3);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioLengthByteBuffer3));
                                    byteBuf.release();
                                }
                                return newResult2;
                            }
                            if (handshake == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                                SSLEngineResult sSLEngineResult2 = isOutboundDone() ? NEED_UNWRAP_CLOSED : NEED_UNWRAP_OK;
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + bioLengthByteBuffer3);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioLengthByteBuffer3));
                                    byteBuf.release();
                                }
                                return sSLEngineResult2;
                            }
                            if (this.outboundClosed) {
                                int bioFlushByteBuffer3 = SSL.bioFlushByteBuffer(this.networkBIO);
                                SSLEngineResult newResultMayFinishHandshake4 = newResultMayFinishHandshake(handshake, 0, bioFlushByteBuffer3);
                                SSL.bioClearByteBuffer(this.networkBIO);
                                if (byteBuf == null) {
                                    byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer3);
                                } else {
                                    byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer3));
                                    byteBuf.release();
                                }
                                return newResultMayFinishHandshake4;
                            }
                            i4 = bioLengthByteBuffer3;
                            handshakeStatus = handshake;
                        } else {
                            i4 = 0;
                        }
                        try {
                            if (this.jdkCompatibilityMode) {
                                int i6 = 0;
                                for (int i7 = i; i7 < i3; i7++) {
                                    ByteBuffer byteBuffer2 = byteBufferArr[i7];
                                    if (byteBuffer2 == null) {
                                        throw new IllegalArgumentException("srcs[" + i7 + "] is null");
                                    }
                                    if (i6 != MAX_PLAINTEXT_LENGTH && ((i6 = i6 + byteBuffer2.remaining()) > MAX_PLAINTEXT_LENGTH || i6 < 0)) {
                                        i6 = MAX_PLAINTEXT_LENGTH;
                                    }
                                }
                                if (!isBytesAvailableEnoughForWrap(byteBuffer.remaining(), i6, 1)) {
                                    SSLEngineResult sSLEngineResult3 = new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatus(), 0, 0);
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                        byteBuffer.position(byteBuffer.position() + i4);
                                    } else {
                                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i4));
                                        byteBuf.release();
                                    }
                                    return sSLEngineResult3;
                                }
                            }
                            int bioFlushByteBuffer4 = SSL.bioFlushByteBuffer(this.networkBIO);
                            while (i < i3) {
                                ByteBuffer byteBuffer3 = byteBufferArr[i];
                                int remaining = byteBuffer3.remaining();
                                if (remaining != 0) {
                                    if (this.jdkCompatibilityMode) {
                                        writePlaintextData = writePlaintextData(byteBuffer3, Math.min(remaining, MAX_PLAINTEXT_LENGTH - i5));
                                    } else {
                                        int remaining2 = (byteBuffer.remaining() - bioFlushByteBuffer4) - this.maxWrapOverhead;
                                        if (remaining2 <= 0) {
                                            SSLEngineResult sSLEngineResult4 = new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatus(), i5, bioFlushByteBuffer4);
                                            SSL.bioClearByteBuffer(this.networkBIO);
                                            if (byteBuf == null) {
                                                byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer4);
                                            } else {
                                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer4));
                                                byteBuf.release();
                                            }
                                            return sSLEngineResult4;
                                        }
                                        writePlaintextData = writePlaintextData(byteBuffer3, Math.min(remaining, remaining2));
                                    }
                                    if (writePlaintextData > 0) {
                                        i5 += writePlaintextData;
                                        int bioLengthByteBuffer4 = SSL.bioLengthByteBuffer(this.networkBIO);
                                        int i8 = (bioLengthByteBuffer - bioLengthByteBuffer4) + bioFlushByteBuffer4;
                                        if (!this.jdkCompatibilityMode && i8 != byteBuffer.remaining()) {
                                            bioFlushByteBuffer4 = i8;
                                            bioLengthByteBuffer = bioLengthByteBuffer4;
                                        }
                                        SSLEngineResult newResultMayFinishHandshake5 = newResultMayFinishHandshake(handshakeStatus, i5, i8);
                                        SSL.bioClearByteBuffer(this.networkBIO);
                                        if (byteBuf == null) {
                                            byteBuffer.position(byteBuffer.position() + i8);
                                        } else {
                                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i8));
                                            byteBuf.release();
                                        }
                                        return newResultMayFinishHandshake5;
                                    }
                                    int error = SSL.getError(this.ssl, writePlaintextData);
                                    if (error == SSL.SSL_ERROR_ZERO_RETURN) {
                                        if (this.receivedShutdown) {
                                            SSLEngineResult newResult3 = newResult(SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, i5, bioFlushByteBuffer4);
                                            SSL.bioClearByteBuffer(this.networkBIO);
                                            if (byteBuf == null) {
                                                byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer4);
                                            } else {
                                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer4));
                                                byteBuf.release();
                                            }
                                            return newResult3;
                                        }
                                        closeAll();
                                        int bioLengthByteBuffer5 = bioFlushByteBuffer4 + (bioLengthByteBuffer - SSL.bioLengthByteBuffer(this.networkBIO));
                                        SSLEngineResult newResult4 = newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? bioLengthByteBuffer5 == byteBuffer.remaining() ? SSLEngineResult.HandshakeStatus.NEED_WRAP : getHandshakeStatus(SSL.bioLengthNonApplication(this.networkBIO)) : SSLEngineResult.HandshakeStatus.FINISHED), i5, bioLengthByteBuffer5);
                                        SSL.bioClearByteBuffer(this.networkBIO);
                                        if (byteBuf == null) {
                                            byteBuffer.position(byteBuffer.position() + bioLengthByteBuffer5);
                                        } else {
                                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioLengthByteBuffer5));
                                            byteBuf.release();
                                        }
                                        return newResult4;
                                    }
                                    if (error == SSL.SSL_ERROR_WANT_READ) {
                                        SSLEngineResult newResult5 = newResult(SSLEngineResult.HandshakeStatus.NEED_UNWRAP, i5, bioFlushByteBuffer4);
                                        SSL.bioClearByteBuffer(this.networkBIO);
                                        if (byteBuf == null) {
                                            byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer4);
                                        } else {
                                            byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer4));
                                            byteBuf.release();
                                        }
                                        return newResult5;
                                    }
                                    if (error != SSL.SSL_ERROR_WANT_WRITE) {
                                        throw shutdownWithError("SSL_write");
                                    }
                                    SSLEngineResult newResult6 = newResult(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, i5, bioFlushByteBuffer4);
                                    SSL.bioClearByteBuffer(this.networkBIO);
                                    if (byteBuf == null) {
                                        byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer4);
                                    } else {
                                        byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer4));
                                        byteBuf.release();
                                    }
                                    return newResult6;
                                }
                                i++;
                            }
                            SSLEngineResult newResultMayFinishHandshake6 = newResultMayFinishHandshake(handshakeStatus, i5, bioFlushByteBuffer4);
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.position(byteBuffer.position() + bioFlushByteBuffer4);
                            } else {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), bioFlushByteBuffer4));
                                byteBuf.release();
                            }
                            return newResultMayFinishHandshake6;
                        } catch (Throwable th2) {
                            th = th2;
                            i5 = i4;
                            SSL.bioClearByteBuffer(this.networkBIO);
                            if (byteBuf == null) {
                                byteBuffer.put(byteBuf.internalNioBuffer(byteBuf.readerIndex(), i5));
                                byteBuf.release();
                            } else {
                                byteBuffer.position(byteBuffer.position() + i5);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        i5 = byteBufferArr;
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    i5 = bioLengthByteBuffer;
                }
            } catch (Throwable th5) {
                th = th5;
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
        return new SSLEngineResult(status, handshakeStatus, i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLEngineResult newResultMayFinishHandshake(SSLEngineResult.Status status, SSLEngineResult.HandshakeStatus handshakeStatus, int i, int i2) throws SSLException {
        return newResult(status, mayFinishHandshake(handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED ? getHandshakeStatus() : SSLEngineResult.HandshakeStatus.FINISHED), i, i2);
    }

    private SSLException shutdownWithError(String str) {
        return shutdownWithError(str, SSL.getLastError());
    }

    private SSLException shutdownWithError(String str, String str2) {
        if (logger.isDebugEnabled()) {
            logger.debug("{} failed: OpenSSL error: {}", str, str2);
        }
        shutdown();
        if (this.handshakeState == HandshakeState.FINISHED) {
            return new SSLException(str2);
        }
        return new SSLHandshakeException(str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:131:0x020c, code lost:
    
        if (r13 == null) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x019d, code lost:
    
        r13.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x019b, code lost:
    
        if (r13 != null) goto L111;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer[] byteBufferArr2, int i3, int i4) throws SSLException {
        int i5;
        int i6;
        int i7;
        int min;
        int min2;
        ByteBuf writeEncryptedData;
        SSLEngineResult sSLEngineResult;
        int i8 = i;
        int i9 = i3;
        if (byteBufferArr == null) {
            throw new NullPointerException("srcs");
        }
        if (i8 >= byteBufferArr.length || (i5 = i8 + i2) > byteBufferArr.length) {
            throw new IndexOutOfBoundsException("offset: " + i8 + ", length: " + i2 + " (expected: offset <= offset + length <= srcs.length (" + byteBufferArr.length + "))");
        }
        if (byteBufferArr2 == null) {
            throw new IllegalArgumentException("dsts is null");
        }
        if (i9 >= byteBufferArr2.length || (i6 = i9 + i4) > byteBufferArr2.length) {
            throw new IndexOutOfBoundsException("offset: " + i9 + ", length: " + i4 + " (expected: offset <= offset + length <= dsts.length (" + byteBufferArr2.length + "))");
        }
        long j = 0;
        for (int i10 = i9; i10 < i6; i10++) {
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
        for (int i11 = i8; i11 < i5; i11++) {
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
                if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
                    return NEED_WRAP_OK;
                }
                if (this.isInboundDone) {
                    return NEED_WRAP_CLOSED;
                }
            }
            int sslPending0 = sslPending0();
            if (this.jdkCompatibilityMode) {
                if (j2 < 5) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBufferArr, i);
                if (encryptedPacketLength == -2) {
                    throw new NotSslRecordException("not an SSL/TLS record");
                }
                int i12 = encryptedPacketLength - 5;
                if (i12 > j) {
                    if (i12 <= MAX_RECORD_SIZE) {
                        this.session.tryExpandApplicationBufferSize(i12);
                        return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, 0, 0);
                    }
                    throw new SSLException("Illegal packet length: " + i12 + " > " + this.session.getApplicationBufferSize());
                }
                if (j2 < encryptedPacketLength) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                min = encryptedPacketLength;
                i7 = 0;
            } else {
                if (j2 == 0 && sslPending0 <= 0) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_UNDERFLOW, handshakeStatus, 0, 0);
                }
                if (j == 0) {
                    return newResultMayFinishHandshake(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus, 0, 0);
                }
                i7 = 0;
                min = (int) Math.min(2147483647L, j2);
            }
            int i13 = min;
            int i14 = i7;
            loop2: while (true) {
                try {
                    ByteBuffer byteBuffer2 = byteBufferArr[i8];
                    int remaining = byteBuffer2.remaining();
                    if (remaining != 0) {
                        min2 = Math.min(i13, remaining);
                        writeEncryptedData = writeEncryptedData(byteBuffer2, min2);
                    } else if (sslPending0 <= 0) {
                        i8++;
                        if (i8 >= i5) {
                            break;
                        }
                    } else {
                        min2 = SSL.bioLengthByteBuffer(this.networkBIO);
                        writeEncryptedData = null;
                    }
                    while (true) {
                        try {
                            ByteBuffer byteBuffer3 = byteBufferArr2[i9];
                            if (byteBuffer3.hasRemaining()) {
                                int i15 = sslPending0;
                                int readPlaintextData = readPlaintextData(byteBuffer3);
                                SSLEngineResult.HandshakeStatus handshakeStatus2 = handshakeStatus;
                                int i16 = i5;
                                int bioLengthByteBuffer = min2 - SSL.bioLengthByteBuffer(this.networkBIO);
                                i14 += bioLengthByteBuffer;
                                i13 -= bioLengthByteBuffer;
                                min2 -= bioLengthByteBuffer;
                                byteBuffer2.position(byteBuffer2.position() + bioLengthByteBuffer);
                                if (readPlaintextData > 0) {
                                    i7 += readPlaintextData;
                                    if (byteBuffer3.hasRemaining()) {
                                        handshakeStatus = handshakeStatus2;
                                        if (i13 == 0 || this.jdkCompatibilityMode) {
                                            break loop2;
                                        }
                                        sslPending0 = i15;
                                    } else {
                                        sslPending0 = sslPending0();
                                        i9++;
                                        if (i9 >= i6) {
                                            return sslPending0 > 0 ? newResult(SSLEngineResult.Status.BUFFER_OVERFLOW, handshakeStatus2, i14, i7) : newResultMayFinishHandshake(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, handshakeStatus2, i14, i7);
                                        }
                                        handshakeStatus = handshakeStatus2;
                                    }
                                    i5 = i16;
                                } else {
                                    handshakeStatus = handshakeStatus2;
                                    int error = SSL.getError(this.ssl, readPlaintextData);
                                    if (error != SSL.SSL_ERROR_WANT_READ && error != SSL.SSL_ERROR_WANT_WRITE) {
                                        if (error != SSL.SSL_ERROR_ZERO_RETURN) {
                                            SSLEngineResult sslReadErrorResult = sslReadErrorResult(SSL.getLastErrorNumber(), i14, i7);
                                            if (writeEncryptedData != null) {
                                                writeEncryptedData.release();
                                            }
                                            return sslReadErrorResult;
                                        }
                                        if (!this.receivedShutdown) {
                                            closeAll();
                                        }
                                        SSLEngineResult newResultMayFinishHandshake = newResultMayFinishHandshake(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, handshakeStatus, i14, i7);
                                        if (writeEncryptedData != null) {
                                            writeEncryptedData.release();
                                        }
                                        return newResultMayFinishHandshake;
                                    }
                                    i8++;
                                    i5 = i16;
                                    if (i8 < i5) {
                                        if (writeEncryptedData != null) {
                                            writeEncryptedData.release();
                                        }
                                        sslPending0 = i15;
                                    } else if (writeEncryptedData != null) {
                                    }
                                }
                            } else {
                                i9++;
                                if (i9 >= i6) {
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
            SSL.bioClearByteBuffer(this.networkBIO);
            rejectRemoteInitiatedRenegotiation();
            if (!this.receivedShutdown && (SSL.getShutdown(this.ssl) & SSL.SSL_RECEIVED_SHUTDOWN) == SSL.SSL_RECEIVED_SHUTDOWN) {
                closeAll();
            }
            return newResultMayFinishHandshake(isInboundDone() ? SSLEngineResult.Status.CLOSED : SSLEngineResult.Status.OK, handshakeStatus, i14, i7);
        }
    }

    private SSLEngineResult sslReadErrorResult(int i, int i2, int i3) throws SSLException {
        String errorString = SSL.getErrorString(i);
        if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
            if (this.handshakeException == null && this.handshakeState != HandshakeState.FINISHED) {
                this.handshakeException = new SSLHandshakeException(errorString);
            }
            return new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, i2, i3);
        }
        throw shutdownWithError("SSL_read", errorString);
    }

    private void closeAll() throws SSLException {
        this.receivedShutdown = true;
        closeOutbound();
        closeInbound();
    }

    private void rejectRemoteInitiatedRenegotiation() throws SSLHandshakeException {
        if (isDestroyed() || SSL.getHandshakeCount(this.ssl) <= 1) {
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
                logger.debug("SSL_shutdown failed: OpenSSL error: {}", SSL.getLastError());
            }
            shutdown();
            return false;
        }
        SSL.clearError();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
    
        if (io.netty.internal.tcnative.SSL.bioLengthNonApplication(r4.networkBIO) == 0) goto L9;
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
        return (String[]) OpenSsl.AVAILABLE_CIPHER_SUITES.toArray(new String[OpenSsl.AVAILABLE_CIPHER_SUITES.size()]);
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getEnabledCipherSuites() {
        synchronized (this) {
            if (!isDestroyed()) {
                String[] ciphers = SSL.getCiphers(this.ssl);
                if (ciphers == null) {
                    return EmptyArrays.EMPTY_STRINGS;
                }
                synchronized (this) {
                    for (int i = 0; i < ciphers.length; i++) {
                        String javaCipherSuite = toJavaCipherSuite(ciphers[i]);
                        if (javaCipherSuite != null) {
                            ciphers[i] = javaCipherSuite;
                        }
                    }
                }
                return ciphers;
            }
            return EmptyArrays.EMPTY_STRINGS;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final void setEnabledCipherSuites(String[] strArr) {
        ObjectUtil.checkNotNull(strArr, "cipherSuites");
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            if (str == null) {
                break;
            }
            String openSsl = CipherSuiteConverter.toOpenSsl(str);
            if (openSsl == null) {
                openSsl = str;
            }
            if (!OpenSsl.isCipherSuiteAvailable(openSsl)) {
                throw new IllegalArgumentException("unsupported cipher suite: " + str + '(' + openSsl + ')');
            }
            sb.append(openSsl);
            sb.append(':');
        }
        if (sb.length() == 0) {
            throw new IllegalArgumentException("empty cipher suites");
        }
        sb.setLength(sb.length() - 1);
        String sb2 = sb.toString();
        synchronized (this) {
            if (!isDestroyed()) {
                try {
                    SSL.setCipherSuites(this.ssl, sb2);
                } catch (Exception e) {
                    throw new IllegalStateException("failed to enable cipher suites: " + sb2, e);
                }
            } else {
                throw new IllegalStateException("failed to enable cipher suites: " + sb2);
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public final String[] getSupportedProtocols() {
        return (String[]) OpenSsl.SUPPORTED_PROTOCOLS_SET.toArray(new String[OpenSsl.SUPPORTED_PROTOCOLS_SET.size()]);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslEngine$3 */
    /* loaded from: classes8.dex */
    class RunnableC73363 implements Runnable {
        final /* synthetic */ Runnable val$task;

        RunnableC73363(Runnable runnable) {
            this.val$task = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ReferenceCountedOpenSslEngine.this.lastAccessed) {
                return;
            }
            try {
                this.val$task.run();
            } finally {
                ReferenceCountedOpenSslEngine.access$602(ReferenceCountedOpenSslEngine.this, false);
            }
        }
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
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv2, "SSLv2")) {
                    arrayList.add("SSLv2");
                }
                if (isProtocolEnabled(options, SSL.SSL_OP_NO_SSLv3, "SSLv3")) {
                    arrayList.add("SSLv3");
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            return (String[]) arrayList.toArray(new String[1]);
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
            }
        }
        synchronized (this) {
            if (!isDestroyed()) {
                SSL.clearOptions(this.ssl, SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_NO_TLSv1 | SSL.SSL_OP_NO_TLSv1_1 | SSL.SSL_OP_NO_TLSv1_2);
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
        int i = C73352.f8556xea902ccf[this.handshakeState.ordinal()];
        if (i == 1) {
            this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
            handshake();
            calculateMaxWrapOverhead();
        } else {
            if (i == 2) {
                throw RENEGOTIATION_UNSUPPORTED;
            }
            if (i == 3) {
                checkEngineClosed(BEGIN_HANDSHAKE_ENGINE_CLOSED);
                this.handshakeState = HandshakeState.STARTED_EXPLICITLY;
                calculateMaxWrapOverhead();
            } else if (i != 4) {
                throw new Error();
            }
        }
    }

    private void checkEngineClosed(SSLException sSLException) throws SSLException {
        if (isDestroyed()) {
            throw sSLException;
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

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        OpenSslKeyMaterialManager openSslKeyMaterialManager;
        if (this.handshakeState == HandshakeState.FINISHED) {
            return SSLEngineResult.HandshakeStatus.FINISHED;
        }
        checkEngineClosed(HANDSHAKE_ENGINE_CLOSED);
        SSLHandshakeException sSLHandshakeException = this.handshakeException;
        if (sSLHandshakeException != null) {
            if (SSL.bioLengthNonApplication(this.networkBIO) > 0) {
                return SSLEngineResult.HandshakeStatus.NEED_WRAP;
            }
            this.handshakeException = null;
            shutdown();
            throw sSLHandshakeException;
        }
        this.engineMap.add(this);
        if (this.lastAccessed == -1) {
            this.lastAccessed = System.currentTimeMillis();
        }
        if (!this.certificateSet && (openSslKeyMaterialManager = this.keyMaterialManager) != null) {
            this.certificateSet = true;
            openSslKeyMaterialManager.setKeyMaterial(this);
        }
        int doHandshake = SSL.doHandshake(this.ssl);
        if (doHandshake <= 0) {
            SSLHandshakeException sSLHandshakeException2 = this.handshakeException;
            if (sSLHandshakeException2 != null) {
                this.handshakeException = null;
                shutdown();
                throw sSLHandshakeException2;
            }
            int error = SSL.getError(this.ssl, doHandshake);
            if (error == SSL.SSL_ERROR_WANT_READ || error == SSL.SSL_ERROR_WANT_WRITE) {
                return pendingStatus(SSL.bioLengthNonApplication(this.networkBIO));
            }
            throw shutdownWithError("SSL_do_handshake");
        }
        this.session.handshakeFinished();
        this.engineMap.remove(this.ssl);
        return SSLEngineResult.HandshakeStatus.FINISHED;
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        return (handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING || this.handshakeState == HandshakeState.FINISHED) ? handshakeStatus : handshake();
    }

    @Override // javax.net.ssl.SSLEngine
    public final synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        return needPendingStatus() ? pendingStatus(SSL.bioLengthNonApplication(this.networkBIO)) : SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        return needPendingStatus() ? pendingStatus(i) : SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
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
        SSL.setVerify(this.ssl, i, i2);
    }

    private void setClientAuth(ClientAuth clientAuth) {
        if (this.clientMode) {
            return;
        }
        synchronized (this) {
            if (this.clientAuth == clientAuth) {
                return;
            }
            int i = C73352.$SwitchMap$io$netty$handler$ssl$ClientAuth[clientAuth.ordinal()];
            if (i == 1) {
                SSL.setVerify(this.ssl, 0, 10);
            } else if (i == 2) {
                SSL.setVerify(this.ssl, 2, 10);
            } else if (i == 3) {
                SSL.setVerify(this.ssl, 1, 10);
            } else {
                throw new Error(clientAuth.toString());
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
            if (javaVersion >= 8) {
                if (!isDestroyed()) {
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
            boolean z = (endpointIdentificationAlgorithm == null || endpointIdentificationAlgorithm.isEmpty()) ? false : true;
            SSL.setHostNameValidation(this.ssl, 0, z ? getPeerHost() : null);
            if (this.clientMode && z) {
                SSL.setVerify(this.ssl, 2, -1);
            }
            this.endPointIdentificationAlgorithm = endpointIdentificationAlgorithm;
            this.algorithmConstraints = sSLParameters.getAlgorithmConstraints();
        }
        super.setSSLParameters(sSLParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroyed() {
        return this.destroyed != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean checkSniHostnameMatch(String str) {
        return Java8SslUtils.checkSniHostnameMatch(this.matchers, str);
    }

    @Override // io.netty.handler.ssl.ApplicationProtocolAccessor
    public String getNegotiatedApplicationProtocol() {
        return this.applicationProtocol;
    }

    private static long bufferAddress(ByteBuffer byteBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.directBufferAddress(byteBuffer);
        }
        return Buffer.address(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OpenSslSession implements SSLSession {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile int applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        private String cipher;
        private long creationTime;

        /* renamed from: id */
        private byte[] f8560id;
        private Certificate[] peerCerts;
        private String protocol;
        private final OpenSslSessionContext sessionContext;
        private Map<String, Object> values;
        private X509Certificate[] x509PeerCerts;

        OpenSslSession(OpenSslSessionContext openSslSessionContext) {
            this.sessionContext = openSslSessionContext;
        }

        @Override // javax.net.ssl.SSLSession
        public byte[] getId() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.f8560id == null) {
                    return EmptyArrays.EMPTY_BYTES;
                }
                return (byte[]) this.f8560id.clone();
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
            if (str == null) {
                throw new NullPointerException("name");
            }
            if (obj == null) {
                throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
            }
            Map map = this.values;
            if (map == null) {
                map = new HashMap(2);
                this.values = map;
            }
            Object put = map.put(str, obj);
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueBound(new SSLSessionBindingEvent(this, str));
            }
            notifyUnbound(put, str);
        }

        @Override // javax.net.ssl.SSLSession
        public Object getValue(String str) {
            if (str == null) {
                throw new NullPointerException("name");
            }
            Map<String, Object> map = this.values;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        @Override // javax.net.ssl.SSLSession
        public void removeValue(String str) {
            if (str == null) {
                throw new NullPointerException("name");
            }
            Map<String, Object> map = this.values;
            if (map == null) {
                return;
            }
            notifyUnbound(map.remove(str), str);
        }

        @Override // javax.net.ssl.SSLSession
        public String[] getValueNames() {
            Map<String, Object> map = this.values;
            if (map == null || map.isEmpty()) {
                return EmptyArrays.EMPTY_STRINGS;
            }
            return (String[]) map.keySet().toArray(new String[map.size()]);
        }

        private void notifyUnbound(Object obj, String str) {
            if (obj instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) obj).valueUnbound(new SSLSessionBindingEvent(this, str));
            }
        }

        void handshakeFinished() throws SSLException {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                    this.f8560id = SSL.getSessionId(ReferenceCountedOpenSslEngine.this.ssl);
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
                Certificate[] certificateArr = new Certificate[peerCertChain.length + 1];
                this.peerCerts = certificateArr;
                this.x509PeerCerts = new X509Certificate[peerCertChain.length + 1];
                certificateArr[0] = new OpenSslX509Certificate(peerCertificate);
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
            int i = C73352.f8555xc16482e4[ReferenceCountedOpenSslEngine.this.apn.protocol().ordinal()];
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
            if (ReferenceCountedOpenSslEngine.this.localCerts == null) {
                return null;
            }
            return (Certificate[]) ReferenceCountedOpenSslEngine.this.localCerts.clone();
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
            Certificate[] certificateArr = ReferenceCountedOpenSslEngine.this.localCerts;
            if (certificateArr == null || certificateArr.length == 0) {
                return null;
            }
            return ((java.security.cert.X509Certificate) certificateArr[0]).getIssuerX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public String getCipherSuite() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.cipher == null) {
                    return ReferenceCountedOpenSslEngine.INVALID_CIPHER;
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

        void tryExpandApplicationBufferSize(int i) {
            if (i <= ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH || this.applicationBufferSize == ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE) {
                return;
            }
            this.applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_RECORD_SIZE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslEngine$2 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C73352 {

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol */
        static final /* synthetic */ int[] f8555xc16482e4;
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ClientAuth;

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState */
        static final /* synthetic */ int[] f8556xea902ccf;

        static {
            int[] iArr = new int[ApplicationProtocolConfig.Protocol.values().length];
            f8555xc16482e4 = iArr;
            try {
                iArr[ApplicationProtocolConfig.Protocol.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8555xc16482e4[ApplicationProtocolConfig.Protocol.ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8555xc16482e4[ApplicationProtocolConfig.Protocol.NPN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8555xc16482e4[ApplicationProtocolConfig.Protocol.NPN_AND_ALPN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ClientAuth.values().length];
            $SwitchMap$io$netty$handler$ssl$ClientAuth = iArr2;
            try {
                iArr2[ClientAuth.NONE.ordinal()] = 1;
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
            int[] iArr3 = new int[HandshakeState.values().length];
            f8556xea902ccf = iArr3;
            try {
                iArr3[HandshakeState.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8556xea902ccf[HandshakeState.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8556xea902ccf[HandshakeState.STARTED_IMPLICITLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8556xea902ccf[HandshakeState.STARTED_EXPLICITLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private final class DefaultOpenSslSession implements io.netty.handler.ssl.OpenSslSession {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile int applicationBufferSize = ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH;
        private String cipher;
        private long creationTime;

        /* renamed from: id */
        private byte[] f8559id;
        private Certificate[] peerCerts;
        private String protocol;
        private final OpenSslSessionContext sessionContext;
        private Map<String, Object> values;
        private X509Certificate[] x509PeerCerts;

        DefaultOpenSslSession(OpenSslSessionContext openSslSessionContext) {
            this.sessionContext = openSslSessionContext;
        }

        private SSLSessionBindingEvent newSSLSessionBindingEvent(String str) {
            return new SSLSessionBindingEvent(ReferenceCountedOpenSslEngine.this.clientMode, str);
        }

        @Override // javax.net.ssl.SSLSession
        public byte[] getId() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.f8559id == null) {
                    return EmptyArrays.EMPTY_BYTES;
                }
                return (byte[]) this.f8559id.clone();
            }
        }

        @Override // javax.net.ssl.SSLSession
        public SSLSessionContext getSessionContext() {
            return this.sessionContext;
        }

        @Override // javax.net.ssl.SSLSession
        public long getCreationTime() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.creationTime == 0 && !ReferenceCountedOpenSslEngine.this.lastAccessed) {
                    this.creationTime = SSL.getTime(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this)) * 1000;
                }
            }
            return this.creationTime;
        }

        @Override // javax.net.ssl.SSLSession
        public long getLastAccessedTime() {
            long access$800 = ReferenceCountedOpenSslEngine.access$800(ReferenceCountedOpenSslEngine.this);
            return access$800 == -1 ? getCreationTime() : access$800;
        }

        @Override // javax.net.ssl.SSLSession
        public void invalidate() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.lastAccessed) {
                    SSL.setTimeout(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this), 0L);
                }
            }
        }

        @Override // javax.net.ssl.SSLSession
        public boolean isValid() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.this.lastAccessed) {
                    return false;
                }
                return System.currentTimeMillis() - (SSL.getTimeout(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this)) * 1000) < SSL.getTime(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this)) * 1000;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void putValue(String str, Object obj) {
            Object put;
            if (str == null) {
                throw new NullPointerException("name");
            }
            if (obj == null) {
                throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
            }
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
            if (str == null) {
                throw new NullPointerException("name");
            }
            synchronized (this) {
                if (this.values == null) {
                    return null;
                }
                return this.values.get(str);
            }
        }

        @Override // javax.net.ssl.SSLSession
        public void removeValue(String str) {
            if (str == null) {
                throw new NullPointerException("name");
            }
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

        @Override // io.netty.handler.ssl.OpenSslSession
        public void handshakeFinished() throws SSLException {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (!ReferenceCountedOpenSslEngine.this.lastAccessed) {
                    this.f8559id = SSL.getSessionId(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    this.cipher = ReferenceCountedOpenSslEngine.access$900(ReferenceCountedOpenSslEngine.this, SSL.getCipherForSSL(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this)));
                    this.protocol = SSL.getVersion(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    initPeerCerts();
                    selectApplicationProtocol();
                    OpenSslApplicationProtocolNegotiator unused = ReferenceCountedOpenSslEngine.this.apn;
                    ReferenceCountedOpenSslEngine.access$1102(ReferenceCountedOpenSslEngine.this, HandshakeState.FINISHED);
                } else {
                    throw new SSLException("Already closed");
                }
            }
        }

        private void initPeerCerts() {
            byte[][] peerCertChain = SSL.getPeerCertChain(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
            if (ReferenceCountedOpenSslEngine.this.isDestroyed()) {
                if (ReferenceCountedOpenSslEngine.access$1200(peerCertChain)) {
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
            byte[] peerCertificate = SSL.getPeerCertificate(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
            if (ReferenceCountedOpenSslEngine.access$1300(peerCertificate)) {
                this.peerCerts = EmptyArrays.EMPTY_CERTIFICATES;
                this.x509PeerCerts = EmptyArrays.EMPTY_JAVAX_X509_CERTIFICATES;
            } else {
                if (ReferenceCountedOpenSslEngine.access$1200(peerCertChain)) {
                    this.peerCerts = new Certificate[]{new OpenSslX509Certificate(peerCertificate)};
                    this.x509PeerCerts = new X509Certificate[]{new OpenSslJavaxX509Certificate(peerCertificate)};
                    return;
                }
                this.peerCerts = new Certificate[peerCertChain.length + 1];
                this.x509PeerCerts = new X509Certificate[peerCertChain.length + 1];
                this.peerCerts[0] = new OpenSslX509Certificate(peerCertificate);
                this.x509PeerCerts[0] = new OpenSslJavaxX509Certificate(peerCertificate);
                initCerts(peerCertChain, 1);
            }
        }

        private void initCerts(byte[][] bArr, int i) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                int i3 = i + i2;
                this.peerCerts[i3] = new OpenSslX509Certificate(bArr[i2]);
                this.x509PeerCerts[i3] = new OpenSslJavaxX509Certificate(bArr[i2]);
            }
        }

        private void selectApplicationProtocol() throws SSLException {
            ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior = ReferenceCountedOpenSslEngine.access$1400(ReferenceCountedOpenSslEngine.this).selectedListenerFailureBehavior();
            List<String> protocols = ReferenceCountedOpenSslEngine.access$1400(ReferenceCountedOpenSslEngine.this).protocols();
            int i = C73374.f8557xc16482e4[ReferenceCountedOpenSslEngine.access$1400(ReferenceCountedOpenSslEngine.this).protocol().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    String alpnSelected = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    if (alpnSelected != null) {
                        ReferenceCountedOpenSslEngine.access$1502(ReferenceCountedOpenSslEngine.this, selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected));
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    String nextProtoNegotiated = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    if (nextProtoNegotiated != null) {
                        ReferenceCountedOpenSslEngine.access$1502(ReferenceCountedOpenSslEngine.this, selectApplicationProtocol(protocols, selectedListenerFailureBehavior, nextProtoNegotiated));
                        return;
                    }
                    return;
                }
                if (i == 4) {
                    String alpnSelected2 = SSL.getAlpnSelected(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    if (alpnSelected2 == null) {
                        alpnSelected2 = SSL.getNextProtoNegotiated(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this));
                    }
                    if (alpnSelected2 != null) {
                        ReferenceCountedOpenSslEngine.access$1502(ReferenceCountedOpenSslEngine.this, selectApplicationProtocol(protocols, selectedListenerFailureBehavior, alpnSelected2));
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
                if (ReferenceCountedOpenSslEngine.access$1200(this.peerCerts)) {
                    throw new SSLPeerUnverifiedException("peer not verified");
                }
                certificateArr = (Certificate[]) this.peerCerts.clone();
            }
            return certificateArr;
        }

        @Override // javax.net.ssl.SSLSession
        public Certificate[] getLocalCertificates() {
            Certificate[] access$1600 = ReferenceCountedOpenSslEngine.access$1600(ReferenceCountedOpenSslEngine.this);
            if (access$1600 == null) {
                return null;
            }
            return (Certificate[]) access$1600.clone();
        }

        @Override // javax.net.ssl.SSLSession
        public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
            X509Certificate[] x509CertificateArr;
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (ReferenceCountedOpenSslEngine.access$1200(this.x509PeerCerts)) {
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
            Certificate[] access$1600 = ReferenceCountedOpenSslEngine.access$1600(ReferenceCountedOpenSslEngine.this);
            if (access$1600 == null || access$1600.length == 0) {
                return null;
            }
            return ((java.security.cert.X509Certificate) access$1600[0]).getIssuerX500Principal();
        }

        @Override // javax.net.ssl.SSLSession
        public String getCipherSuite() {
            synchronized (ReferenceCountedOpenSslEngine.this) {
                if (this.cipher == null) {
                    return ReferenceCountedOpenSslEngine.INVALID_CIPHER;
                }
                return this.cipher;
            }
        }

        @Override // javax.net.ssl.SSLSession
        public String getProtocol() {
            String str = this.protocol;
            if (str == null) {
                synchronized (ReferenceCountedOpenSslEngine.this) {
                    str = !ReferenceCountedOpenSslEngine.this.lastAccessed ? SSL.getVersion(ReferenceCountedOpenSslEngine.access$400(ReferenceCountedOpenSslEngine.this)) : "";
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

        @Override // io.netty.handler.ssl.OpenSslSession
        public void tryExpandApplicationBufferSize(int i) {
            if (i <= ReferenceCountedOpenSslEngine.MAX_PLAINTEXT_LENGTH || this.applicationBufferSize == ReferenceCountedOpenSslEngine.access$1700()) {
                return;
            }
            this.applicationBufferSize = ReferenceCountedOpenSslEngine.access$1700();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslEngine$4 */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class C73374 {

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol */
        static final /* synthetic */ int[] f8557xc16482e4 = new int[ApplicationProtocolConfig.Protocol.values().length];
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$ClientAuth;

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ReferenceCountedOpenSslEngine$HandshakeState */
        static final /* synthetic */ int[] f8558xea902ccf;

        static {
            try {
                f8557xc16482e4[ApplicationProtocolConfig.Protocol.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8557xc16482e4[ApplicationProtocolConfig.Protocol.ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8557xc16482e4[ApplicationProtocolConfig.Protocol.NPN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8557xc16482e4[ApplicationProtocolConfig.Protocol.NPN_AND_ALPN.ordinal()] = 4;
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
            f8558xea902ccf = new int[HandshakeState.values().length];
            try {
                f8558xea902ccf[HandshakeState.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8558xea902ccf[HandshakeState.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8558xea902ccf[HandshakeState.STARTED_IMPLICITLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8558xea902ccf[HandshakeState.STARTED_EXPLICITLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
