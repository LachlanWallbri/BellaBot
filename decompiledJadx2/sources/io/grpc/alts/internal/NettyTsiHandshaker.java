package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NettyTsiHandshaker {
    private final TsiHandshaker internalHandshaker;
    private BufUnwrapper unwrapper = new BufUnwrapper();

    public NettyTsiHandshaker(TsiHandshaker tsiHandshaker) {
        this.internalHandshaker = (TsiHandshaker) Preconditions.checkNotNull(tsiHandshaker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getBytesToSendToPeer(ByteBuf byteBuf) throws GeneralSecurityException {
        Preconditions.checkState(this.unwrapper != null, "protector already created");
        BufUnwrapper bufUnwrapper = this.unwrapper;
        try {
            int i = 0;
            for (ByteBuffer byteBuffer : bufUnwrapper.writableNioBuffers(byteBuf)) {
                if (byteBuffer.hasRemaining()) {
                    int position = byteBuffer.position();
                    this.internalHandshaker.getBytesToSendToPeer(byteBuffer);
                    i += byteBuffer.position() - position;
                    if (byteBuffer.position() == position) {
                        break;
                    }
                }
            }
            byteBuf.writerIndex(byteBuf.writerIndex() + i);
            if (bufUnwrapper != null) {
                $closeResource(null, bufUnwrapper);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bufUnwrapper != null) {
                    $closeResource(th, bufUnwrapper);
                }
                throw th2;
            }
        }
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th == null) {
            autoCloseable.close();
            return;
        }
        try {
            autoCloseable.close();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processBytesFromPeer(ByteBuf byteBuf) throws GeneralSecurityException {
        Preconditions.checkState(this.unwrapper != null, "protector already created");
        BufUnwrapper bufUnwrapper = this.unwrapper;
        try {
            int i = 0;
            boolean z = false;
            for (ByteBuffer byteBuffer : bufUnwrapper.readableNioBuffers(byteBuf)) {
                if (byteBuffer.hasRemaining()) {
                    int position = byteBuffer.position();
                    boolean processBytesFromPeer = this.internalHandshaker.processBytesFromPeer(byteBuffer);
                    i += byteBuffer.position() - position;
                    z = processBytesFromPeer;
                    if (processBytesFromPeer) {
                        break;
                    }
                }
            }
            byteBuf.readerIndex(byteBuf.readerIndex() + i);
            if (bufUnwrapper != null) {
                $closeResource(null, bufUnwrapper);
            }
            return z;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInProgress() {
        return this.internalHandshaker.isInProgress();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TsiPeer extractPeer() throws GeneralSecurityException {
        Preconditions.checkState(!this.internalHandshaker.isInProgress());
        return this.internalHandshaker.extractPeer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object extractPeerObject() throws GeneralSecurityException {
        Preconditions.checkState(!this.internalHandshaker.isInProgress());
        return this.internalHandshaker.extractPeerObject();
    }

    TsiFrameProtector createFrameProtector(int i, ByteBufAllocator byteBufAllocator) {
        this.unwrapper = null;
        return this.internalHandshaker.createFrameProtector(i, byteBufAllocator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TsiFrameProtector createFrameProtector(ByteBufAllocator byteBufAllocator) {
        this.unwrapper = null;
        return this.internalHandshaker.createFrameProtector(byteBufAllocator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        this.internalHandshaker.close();
    }
}
