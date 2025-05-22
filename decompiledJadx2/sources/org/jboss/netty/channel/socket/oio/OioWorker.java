package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.SocketException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.regex.Pattern;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DefaultFileRegion;
import org.jboss.netty.channel.FileRegion;

/* loaded from: classes7.dex */
class OioWorker extends AbstractOioWorker<OioSocketChannel> {
    private static final Pattern SOCKET_CLOSED_MESSAGE = Pattern.compile("^.*(?:Socket.*closed).*$", 2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioWorker(OioSocketChannel oioSocketChannel) {
        super(oioSocketChannel);
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioWorker, java.lang.Runnable
    public void run() {
        if ((this.channel instanceof OioAcceptedSocketChannel) && ((OioSocketChannel) this.channel).isOpen()) {
            Channels.fireChannelConnected(this.channel, ((OioSocketChannel) this.channel).getRemoteAddress());
        }
        super.run();
    }

    @Override // org.jboss.netty.channel.socket.oio.AbstractOioWorker
    boolean process() throws IOException {
        PushbackInputStream inputStream = ((OioSocketChannel) this.channel).getInputStream();
        int available = inputStream.available();
        if (available > 0) {
            byte[] bArr = new byte[available];
            Channels.fireMessageReceived(this.channel, ((OioSocketChannel) this.channel).getConfig().getBufferFactory().getBuffer(bArr, 0, inputStream.read(bArr)));
            return true;
        }
        int read = inputStream.read();
        if (read < 0) {
            return false;
        }
        inputStream.unread(read);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079 A[Catch: all -> 0x0086, TryCatch #3 {all -> 0x0086, blocks: (B:12:0x001d, B:14:0x0021, B:25:0x003f, B:27:0x0043, B:29:0x004c, B:30:0x0074, B:32:0x0079, B:34:0x007e, B:44:0x0054, B:46:0x0058, B:48:0x0061, B:49:0x0064, B:50:0x0065, B:51:0x006b, B:58:0x0085, B:53:0x006c, B:54:0x0073, B:16:0x0023, B:41:0x0052, B:18:0x0024, B:19:0x0028, B:21:0x0033, B:24:0x003e), top: B:11:0x001d, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007e A[Catch: all -> 0x0086, TRY_LEAVE, TryCatch #3 {all -> 0x0086, blocks: (B:12:0x001d, B:14:0x0021, B:25:0x003f, B:27:0x0043, B:29:0x004c, B:30:0x0074, B:32:0x0079, B:34:0x007e, B:44:0x0054, B:46:0x0058, B:48:0x0061, B:49:0x0064, B:50:0x0065, B:51:0x006b, B:58:0x0085, B:53:0x006c, B:54:0x0073, B:16:0x0023, B:41:0x0052, B:18:0x0024, B:19:0x0028, B:21:0x0033, B:24:0x003e), top: B:11:0x001d, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void write(OioSocketChannel oioSocketChannel, ChannelFuture channelFuture, Object obj) {
        boolean isIoThread = isIoThread(oioSocketChannel);
        OutputStream outputStream = oioSocketChannel.getOutputStream();
        if (outputStream == null) {
            ClosedChannelException closedChannelException = new ClosedChannelException();
            channelFuture.setFailure(closedChannelException);
            if (isIoThread) {
                Channels.fireExceptionCaught(oioSocketChannel, closedChannelException);
                return;
            } else {
                Channels.fireExceptionCaughtLater(oioSocketChannel, closedChannelException);
                return;
            }
        }
        int i = 0;
        try {
            if (obj instanceof FileRegion) {
                FileRegion fileRegion = (FileRegion) obj;
                try {
                    synchronized (outputStream) {
                        WritableByteChannel newChannel = java.nio.channels.Channels.newChannel(outputStream);
                        do {
                            long j = i;
                            long transferTo = fileRegion.transferTo(newChannel, j);
                            if (transferTo <= 0) {
                                break;
                            } else {
                                i = (int) (j + transferTo);
                            }
                        } while (i < fileRegion.getCount());
                    }
                    channelFuture.setSuccess();
                    if (!isIoThread) {
                        Channels.fireWriteComplete(oioSocketChannel, i);
                        return;
                    } else {
                        Channels.fireWriteCompleteLater(oioSocketChannel, i);
                        return;
                    }
                } finally {
                    if ((fileRegion instanceof DefaultFileRegion) && ((DefaultFileRegion) fileRegion).releaseAfterTransfer()) {
                        fileRegion.releaseExternalResources();
                    }
                }
            }
            ChannelBuffer channelBuffer = (ChannelBuffer) obj;
            i = channelBuffer.readableBytes();
            synchronized (outputStream) {
                channelBuffer.getBytes(channelBuffer.readerIndex(), outputStream, i);
            }
            channelFuture.setSuccess();
            if (!isIoThread) {
            }
        } catch (Throwable th) {
            th = th;
            if ((th instanceof SocketException) && SOCKET_CLOSED_MESSAGE.matcher(String.valueOf(th.getMessage())).matches()) {
                th = new ClosedChannelException();
            }
            channelFuture.setFailure(th);
            if (isIoThread) {
                Channels.fireExceptionCaught(oioSocketChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(oioSocketChannel, th);
            }
        }
    }
}
