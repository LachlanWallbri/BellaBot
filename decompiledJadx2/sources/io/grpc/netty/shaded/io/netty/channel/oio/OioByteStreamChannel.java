package io.grpc.netty.shaded.io.netty.channel.oio;

import com.iflytek.aiui.constant.InternalConstant;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.FileRegion;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.WritableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public abstract class OioByteStreamChannel extends AbstractOioByteChannel {
    private static final InputStream CLOSED_IN = new InputStream() { // from class: io.grpc.netty.shaded.io.netty.channel.oio.OioByteStreamChannel.1
        @Override // java.io.InputStream
        public int read() {
            return -1;
        }
    };
    private static final OutputStream CLOSED_OUT = new OutputStream() { // from class: io.grpc.netty.shaded.io.netty.channel.oio.OioByteStreamChannel.2
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            throw new ClosedChannelException();
        }
    };

    /* renamed from: is */
    private InputStream f8340is;

    /* renamed from: os */
    private OutputStream f8341os;
    private WritableByteChannel outChannel;

    /* JADX INFO: Access modifiers changed from: protected */
    public OioByteStreamChannel(Channel channel) {
        super(channel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void activate(InputStream inputStream, OutputStream outputStream) {
        if (this.f8340is != null) {
            throw new IllegalStateException("input was set already");
        }
        if (this.f8341os != null) {
            throw new IllegalStateException("output was set already");
        }
        this.f8340is = (InputStream) ObjectUtil.checkNotNull(inputStream, "is");
        this.f8341os = (OutputStream) ObjectUtil.checkNotNull(outputStream, InternalConstant.KEY_OS);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        OutputStream outputStream;
        InputStream inputStream = this.f8340is;
        return (inputStream == null || inputStream == CLOSED_IN || (outputStream = this.f8341os) == null || outputStream == CLOSED_OUT) ? false : true;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioByteChannel
    protected int available() {
        try {
            return this.f8340is.available();
        } catch (IOException unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioByteChannel
    public int doReadBytes(ByteBuf byteBuf) throws Exception {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.attemptedBytesRead(Math.max(1, Math.min(available(), byteBuf.maxWritableBytes())));
        return byteBuf.writeBytes(this.f8340is, recvBufAllocHandle.attemptedBytesRead());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioByteChannel
    protected void doWriteBytes(ByteBuf byteBuf) throws Exception {
        OutputStream outputStream = this.f8341os;
        if (outputStream == null) {
            throw new NotYetConnectedException();
        }
        byteBuf.readBytes(outputStream, byteBuf.readableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioByteChannel
    protected void doWriteFileRegion(FileRegion fileRegion) throws Exception {
        OutputStream outputStream = this.f8341os;
        if (outputStream == null) {
            throw new NotYetConnectedException();
        }
        if (this.outChannel == null) {
            this.outChannel = Channels.newChannel(outputStream);
        }
        long j = 0;
        do {
            long transferTo = fileRegion.transferTo(this.outChannel, j);
            if (transferTo == -1) {
                checkEOF(fileRegion);
                return;
            }
            j += transferTo;
        } while (j < fileRegion.count());
    }

    private static void checkEOF(FileRegion fileRegion) throws IOException {
        if (fileRegion.transferred() >= fileRegion.count()) {
            return;
        }
        throw new EOFException("Expected to be able to write " + fileRegion.count() + " bytes, but only wrote " + fileRegion.transferred());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        InputStream inputStream = this.f8340is;
        OutputStream outputStream = this.f8341os;
        this.f8340is = CLOSED_IN;
        this.f8341os = CLOSED_OUT;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }
}
