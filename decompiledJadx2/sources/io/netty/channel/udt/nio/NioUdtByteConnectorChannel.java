package io.netty.channel.udt.nio;

import com.barchart.udt.StatusUDT;
import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.udt.DefaultUdtChannelConfig;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.UdtChannelConfig;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public class NioUdtByteConnectorChannel extends AbstractNioByteChannel implements UdtChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioUdtByteConnectorChannel.class);
    private final UdtChannelConfig config;

    public NioUdtByteConnectorChannel() {
        this(TypeUDT.STREAM);
    }

    public NioUdtByteConnectorChannel(Channel channel, SocketChannelUDT socketChannelUDT) {
        super(channel, socketChannelUDT);
        try {
            socketChannelUDT.configureBlocking(false);
            int i = C70882.$SwitchMap$com$barchart$udt$StatusUDT[socketChannelUDT.socketUDT().status().ordinal()];
            if (i == 1 || i == 2) {
                this.config = new DefaultUdtChannelConfig(this, socketChannelUDT, true);
            } else {
                this.config = new DefaultUdtChannelConfig(this, socketChannelUDT, false);
            }
        } catch (Exception e) {
            try {
                socketChannelUDT.close();
            } catch (Exception e2) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to close channel.", (Throwable) e2);
                }
            }
            throw new ChannelException("Failed to configure channel.", e);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: io.netty.channel.udt.nio.NioUdtByteConnectorChannel$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C70882 {
        static final /* synthetic */ int[] $SwitchMap$com$barchart$udt$StatusUDT;

        static {
            int[] iArr = new int[StatusUDT.values().length];
            $SwitchMap$com$barchart$udt$StatusUDT = iArr;
            try {
                iArr[StatusUDT.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$barchart$udt$StatusUDT[StatusUDT.OPENED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public NioUdtByteConnectorChannel(SocketChannelUDT socketChannelUDT) {
        this(null, socketChannelUDT);
    }

    public NioUdtByteConnectorChannel(TypeUDT typeUDT) {
        this(NioUdtProvider.newConnectorChannelUDT(typeUDT));
    }

    @Override // io.netty.channel.Channel
    public UdtChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        privilegedBind(mo4506javaChannel(), socketAddress);
    }

    @Override // io.netty.channel.nio.AbstractNioChannel, io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        mo4506javaChannel().close();
    }

    @Override // io.netty.channel.nio.AbstractNioChannel
    protected boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 == null) {
            socketAddress2 = new InetSocketAddress(0);
        }
        doBind(socketAddress2);
        try {
            boolean connect = SocketUtils.connect(mo4506javaChannel(), socketAddress);
            if (!connect) {
                selectionKey().interestOps(selectionKey().interestOps() | 8);
            }
            return connect;
        } catch (Throwable th) {
            doClose();
            throw th;
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    @Override // io.netty.channel.nio.AbstractNioChannel
    protected void doFinishConnect() throws Exception {
        if (mo4506javaChannel().finishConnect()) {
            selectionKey().interestOps(selectionKey().interestOps() & (-9));
            return;
        }
        throw new Error("Provider error: failed to finish connect. Provider library should be upgraded.");
    }

    @Override // io.netty.channel.nio.AbstractNioByteChannel
    protected int doReadBytes(ByteBuf byteBuf) throws Exception {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.attemptedBytesRead(byteBuf.writableBytes());
        return byteBuf.writeBytes((ScatteringByteChannel) mo4506javaChannel(), recvBufAllocHandle.attemptedBytesRead());
    }

    @Override // io.netty.channel.nio.AbstractNioByteChannel
    protected int doWriteBytes(ByteBuf byteBuf) throws Exception {
        return byteBuf.readBytes((GatheringByteChannel) mo4506javaChannel(), byteBuf.readableBytes());
    }

    @Override // io.netty.channel.nio.AbstractNioByteChannel, io.netty.channel.socket.DuplexChannel
    protected ChannelFuture shutdownInput() {
        return newFailedFuture(new UnsupportedOperationException("shutdownInput"));
    }

    @Override // io.netty.channel.nio.AbstractNioByteChannel
    protected long doWriteFileRegion(FileRegion fileRegion) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.channel.Channel
    public boolean isActive() {
        SocketChannelUDT mo4506javaChannel = mo4506javaChannel();
        return mo4506javaChannel.isOpen() && mo4506javaChannel.isConnectFinished();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.nio.AbstractNioChannel
    /* renamed from: javaChannel, reason: merged with bridge method [inline-methods] */
    public SocketChannelUDT mo4506javaChannel() {
        return super.mo4506javaChannel();
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return mo4506javaChannel().socket().getLocalSocketAddress();
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return mo4506javaChannel().socket().getRemoteSocketAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    private static void privilegedBind(final SocketChannelUDT socketChannelUDT, final SocketAddress socketAddress) throws IOException {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() { // from class: io.netty.channel.udt.nio.NioUdtByteConnectorChannel.1
                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws IOException {
                    socketChannelUDT.bind(socketAddress);
                    return null;
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getCause());
        }
    }
}
