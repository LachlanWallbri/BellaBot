package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.alts.internal.HandshakerServiceGrpc;
import io.grpc.alts.internal.TsiPeer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsTsiHandshaker implements TsiHandshaker {
    public static final String TSI_SERVICE_ACCOUNT_PEER_PROPERTY = "service_account";
    private static final Logger logger = Logger.getLogger(AltsTsiHandshaker.class.getName());
    private final AltsHandshakerClient handshaker;
    private final boolean isClient;
    private ByteBuffer outputFrame;

    private AltsTsiHandshaker(boolean z, HandshakerServiceGrpc.HandshakerServiceStub handshakerServiceStub, AltsHandshakerOptions altsHandshakerOptions) {
        this.isClient = z;
        this.handshaker = new AltsHandshakerClient(handshakerServiceStub, altsHandshakerOptions);
    }

    AltsTsiHandshaker(boolean z, AltsHandshakerClient altsHandshakerClient) {
        this.isClient = z;
        this.handshaker = altsHandshakerClient;
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public boolean processBytesFromPeer(ByteBuffer byteBuffer) throws GeneralSecurityException {
        if (this.outputFrame == null && this.isClient) {
            return true;
        }
        ByteBuffer byteBuffer2 = this.outputFrame;
        if (byteBuffer2 != null && byteBuffer2.hasRemaining()) {
            return true;
        }
        int remaining = byteBuffer.remaining();
        if (this.outputFrame == null) {
            Preconditions.checkState(!this.isClient, "Client handshaker should not process any frame at the beginning.");
            this.outputFrame = this.handshaker.startServerHandshake(byteBuffer);
        } else {
            this.outputFrame = this.handshaker.next(byteBuffer);
        }
        if (this.handshaker.isFinished() || this.outputFrame.hasRemaining()) {
            return true;
        }
        if (!byteBuffer.hasRemaining()) {
            return false;
        }
        Preconditions.checkState(byteBuffer.remaining() < remaining, "Handshaker did not consume any bytes.");
        return processBytesFromPeer(byteBuffer);
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public TsiPeer extractPeer() throws GeneralSecurityException {
        Preconditions.checkState(!isInProgress(), "Handshake is not complete.");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TsiPeer.StringProperty(TSI_SERVICE_ACCOUNT_PEER_PROPERTY, this.handshaker.getResult().getPeerIdentity().getServiceAccount()));
        return new TsiPeer(arrayList);
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public Object extractPeerObject() throws GeneralSecurityException {
        Preconditions.checkState(!isInProgress(), "Handshake is not complete.");
        return new AltsInternalContext(this.handshaker.getResult());
    }

    public static TsiHandshaker newClient(HandshakerServiceGrpc.HandshakerServiceStub handshakerServiceStub, AltsHandshakerOptions altsHandshakerOptions) {
        return new AltsTsiHandshaker(true, handshakerServiceStub, altsHandshakerOptions);
    }

    public static TsiHandshaker newServer(HandshakerServiceGrpc.HandshakerServiceStub handshakerServiceStub, AltsHandshakerOptions altsHandshakerOptions) {
        return new AltsTsiHandshaker(false, handshakerServiceStub, altsHandshakerOptions);
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public void getBytesToSendToPeer(ByteBuffer byteBuffer) throws GeneralSecurityException {
        if (this.outputFrame == null) {
            if (!this.isClient) {
                return;
            } else {
                this.outputFrame = this.handshaker.startClientHandshake();
            }
        }
        ByteBuffer byteBuffer2 = this.outputFrame;
        if (byteBuffer2.remaining() > byteBuffer.remaining()) {
            byteBuffer2 = this.outputFrame.duplicate();
            byteBuffer2.limit(byteBuffer2.position() + byteBuffer.remaining());
        }
        byteBuffer.put(byteBuffer2);
        this.outputFrame.position(byteBuffer2.position());
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public boolean isInProgress() {
        return !this.handshaker.isFinished() || this.outputFrame.hasRemaining();
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public TsiFrameProtector createFrameProtector(int i, ByteBufAllocator byteBufAllocator) {
        Preconditions.checkState(!isInProgress(), "Handshake is not complete.");
        byte[] key = this.handshaker.getKey();
        Preconditions.checkState(key.length == AltsChannelCrypter.getKeyLength(), "Bad key length.");
        int maxFrameSize = this.handshaker.getResult().getMaxFrameSize();
        if (maxFrameSize != 0) {
            i = Math.max(AltsTsiFrameProtector.getMinFrameSize(), Math.min(maxFrameSize, AltsTsiFrameProtector.getMaxFrameSize()));
        }
        logger.log(Level.FINE, "Maximum frame size value is {0}.", Integer.valueOf(i));
        return new AltsTsiFrameProtector(i, new AltsChannelCrypter(key, this.isClient), byteBufAllocator);
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public TsiFrameProtector createFrameProtector(ByteBufAllocator byteBufAllocator) {
        return createFrameProtector(AltsTsiFrameProtector.getMinFrameSize(), byteBufAllocator);
    }

    @Override // io.grpc.alts.internal.TsiHandshaker
    public void close() {
        this.handshaker.close();
    }
}
