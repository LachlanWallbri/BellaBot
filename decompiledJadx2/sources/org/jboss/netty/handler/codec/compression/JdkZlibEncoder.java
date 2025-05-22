package org.jboss.netty.handler.codec.compression;

import com.google.common.base.Ascii;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/* loaded from: classes7.dex */
public class JdkZlibEncoder extends OneToOneEncoder implements LifeCycleAwareChannelHandler {
    private static final byte[] gzipHeader = {Ascii.f1926US, -117, 8, 0, 0, 0, 0, 0, 0, 0};
    private final CRC32 crc;
    private volatile ChannelHandlerContext ctx;
    private final Deflater deflater;
    private final AtomicBoolean finished;
    private final boolean gzip;
    private final byte[] out;
    private boolean writeHeader;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public JdkZlibEncoder() {
        this(6);
    }

    public JdkZlibEncoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 6);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        this.out = new byte[8192];
        this.finished = new AtomicBoolean();
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        if (zlibWrapper == ZlibWrapper.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not allowed for compression.");
        }
        this.gzip = zlibWrapper == ZlibWrapper.GZIP;
        this.deflater = new Deflater(i, zlibWrapper != ZlibWrapper.ZLIB);
    }

    public JdkZlibEncoder(byte[] bArr) {
        this(6, bArr);
    }

    public JdkZlibEncoder(int i, byte[] bArr) {
        this.out = new byte[8192];
        this.finished = new AtomicBoolean();
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (bArr == null) {
            throw new NullPointerException("dictionary");
        }
        this.gzip = false;
        this.deflater = new Deflater(i);
        this.deflater.setDictionary(bArr);
    }

    public ChannelFuture close() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException("not added to a pipeline");
        }
        return finishEncode(channelHandlerContext, null);
    }

    public boolean isClosed() {
        return this.finished.get();
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (!(obj instanceof ChannelBuffer) || this.finished.get()) {
            return obj;
        }
        ChannelBuffer channelBuffer = (ChannelBuffer) obj;
        byte[] bArr = new byte[channelBuffer.readableBytes()];
        channelBuffer.readBytes(bArr);
        ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer(((int) Math.ceil(bArr.length * 1.001d)) + 12, channel.getConfig().getBufferFactory());
        synchronized (this.deflater) {
            if (this.gzip) {
                this.crc.update(bArr);
                if (this.writeHeader) {
                    dynamicBuffer.writeBytes(gzipHeader);
                    this.writeHeader = false;
                }
            }
            this.deflater.setInput(bArr);
            while (!this.deflater.needsInput()) {
                dynamicBuffer.writeBytes(this.out, 0, this.deflater.deflate(this.out, 0, this.out.length, 2));
            }
        }
        return dynamicBuffer;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder, org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87032.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if ((i == 1 || i == 2 || i == 3) && (Boolean.FALSE.equals(channelStateEvent.getValue()) || channelStateEvent.getValue() == null)) {
                finishEncode(channelHandlerContext, channelEvent);
                return;
            }
        }
        super.handleDownstream(channelHandlerContext, channelEvent);
    }

    /* renamed from: org.jboss.netty.handler.codec.compression.JdkZlibEncoder$2 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87032 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ChannelFuture finishEncode(final ChannelHandlerContext channelHandlerContext, final ChannelEvent channelEvent) {
        if (!this.finished.compareAndSet(false, true)) {
            if (channelEvent != null) {
                channelHandlerContext.sendDownstream(channelEvent);
            }
            return Channels.succeededFuture(channelHandlerContext.getChannel());
        }
        ChannelBuffer channelBuffer = ChannelBuffers.EMPTY_BUFFER;
        synchronized (this.deflater) {
            this.deflater.finish();
            int deflate = !this.deflater.finished() ? this.deflater.deflate(this.out, 0, this.out.length) : 0;
            int i = this.gzip ? deflate + 8 : deflate;
            if (i > 0) {
                channelBuffer = channelHandlerContext.getChannel().getConfig().getBufferFactory().getBuffer(i);
                channelBuffer.writeBytes(this.out, 0, deflate);
                if (this.gzip) {
                    int value = (int) this.crc.getValue();
                    int totalIn = this.deflater.getTotalIn();
                    channelBuffer.writeByte(value);
                    channelBuffer.writeByte(value >>> 8);
                    channelBuffer.writeByte(value >>> 16);
                    channelBuffer.writeByte(value >>> 24);
                    channelBuffer.writeByte(totalIn);
                    channelBuffer.writeByte(totalIn >>> 8);
                    channelBuffer.writeByte(totalIn >>> 16);
                    channelBuffer.writeByte(totalIn >>> 24);
                }
            }
            this.deflater.end();
        }
        ChannelFuture future = Channels.future(channelHandlerContext.getChannel());
        Channels.write(channelHandlerContext, future, channelBuffer);
        if (channelEvent != null) {
            future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.compression.JdkZlibEncoder.1
                @Override // org.jboss.netty.channel.ChannelFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    channelHandlerContext.sendDownstream(channelEvent);
                }
            });
        }
        return future;
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }
}
