package org.jboss.netty.handler.codec.compression;

import java.util.concurrent.atomic.AtomicBoolean;
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
import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;

/* loaded from: classes7.dex */
public class ZlibEncoder extends OneToOneEncoder implements LifeCycleAwareChannelHandler {
    private static final byte[] EMPTY_ARRAY = new byte[0];
    private volatile ChannelHandlerContext ctx;
    private final AtomicBoolean finished;

    /* renamed from: z */
    private final ZStream f10027z;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public ZlibEncoder() {
        this(6);
    }

    public ZlibEncoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public ZlibEncoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 6);
    }

    public ZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        this(zlibWrapper, i, 15, 8);
    }

    public ZlibEncoder(ZlibWrapper zlibWrapper, int i, int i2, int i3) {
        this.f10027z = new ZStream();
        this.finished = new AtomicBoolean();
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        if (zlibWrapper == ZlibWrapper.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not allowed for compression.");
        }
        synchronized (this.f10027z) {
            int deflateInit = this.f10027z.deflateInit(i, i2, i3, ZlibUtil.convertWrapperType(zlibWrapper));
            if (deflateInit != 0) {
                ZlibUtil.fail(this.f10027z, "initialization failure", deflateInit);
            }
        }
    }

    public ZlibEncoder(byte[] bArr) {
        this(6, bArr);
    }

    public ZlibEncoder(int i, byte[] bArr) {
        this(i, 15, 8, bArr);
    }

    public ZlibEncoder(int i, int i2, int i3, byte[] bArr) {
        this.f10027z = new ZStream();
        this.finished = new AtomicBoolean();
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        if (bArr == null) {
            throw new NullPointerException("dictionary");
        }
        synchronized (this.f10027z) {
            int deflateInit = this.f10027z.deflateInit(i, i2, i3, JZlib.W_ZLIB);
            if (deflateInit != 0) {
                ZlibUtil.fail(this.f10027z, "initialization failure", deflateInit);
            } else {
                int deflateSetDictionary = this.f10027z.deflateSetDictionary(bArr, bArr.length);
                if (deflateSetDictionary != 0) {
                    ZlibUtil.fail(this.f10027z, "failed to set the dictionary", deflateSetDictionary);
                }
            }
        }
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
        ChannelBuffer channelBuffer;
        if (!(obj instanceof ChannelBuffer) || this.finished.get()) {
            return obj;
        }
        synchronized (this.f10027z) {
            try {
                ChannelBuffer channelBuffer2 = (ChannelBuffer) obj;
                byte[] bArr = new byte[channelBuffer2.readableBytes()];
                channelBuffer2.readBytes(bArr);
                this.f10027z.next_in = bArr;
                this.f10027z.next_in_index = 0;
                this.f10027z.avail_in = bArr.length;
                byte[] bArr2 = new byte[((int) Math.ceil(bArr.length * 1.001d)) + 12];
                this.f10027z.next_out = bArr2;
                this.f10027z.next_out_index = 0;
                this.f10027z.avail_out = bArr2.length;
                int deflate = this.f10027z.deflate(2);
                if (deflate != 0) {
                    ZlibUtil.fail(this.f10027z, "compression failure", deflate);
                }
                if (this.f10027z.next_out_index != 0) {
                    channelBuffer = channelHandlerContext.getChannel().getConfig().getBufferFactory().getBuffer(channelBuffer2.order(), bArr2, 0, this.f10027z.next_out_index);
                } else {
                    channelBuffer = ChannelBuffers.EMPTY_BUFFER;
                }
            } finally {
                this.f10027z.next_in = null;
                this.f10027z.next_out = null;
            }
        }
        return channelBuffer;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder, org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87052.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if ((i == 1 || i == 2 || i == 3) && (Boolean.FALSE.equals(channelStateEvent.getValue()) || channelStateEvent.getValue() == null)) {
                finishEncode(channelHandlerContext, channelEvent);
                return;
            }
        }
        super.handleDownstream(channelHandlerContext, channelEvent);
    }

    /* renamed from: org.jboss.netty.handler.codec.compression.ZlibEncoder$2 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87052 {
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
        ChannelFuture future;
        ChannelBuffer channelBuffer;
        if (!this.finished.compareAndSet(false, true)) {
            if (channelEvent != null) {
                channelHandlerContext.sendDownstream(channelEvent);
            }
            return Channels.succeededFuture(channelHandlerContext.getChannel());
        }
        synchronized (this.f10027z) {
            try {
                this.f10027z.next_in = EMPTY_ARRAY;
                this.f10027z.next_in_index = 0;
                this.f10027z.avail_in = 0;
                byte[] bArr = new byte[32];
                this.f10027z.next_out = bArr;
                this.f10027z.next_out_index = 0;
                this.f10027z.avail_out = bArr.length;
                int deflate = this.f10027z.deflate(4);
                if (deflate != 0 && deflate != 1) {
                    future = Channels.failedFuture(channelHandlerContext.getChannel(), ZlibUtil.exception(this.f10027z, "compression failure", deflate));
                    channelBuffer = null;
                } else if (this.f10027z.next_out_index != 0) {
                    future = Channels.future(channelHandlerContext.getChannel());
                    channelBuffer = channelHandlerContext.getChannel().getConfig().getBufferFactory().getBuffer(bArr, 0, this.f10027z.next_out_index);
                } else {
                    future = Channels.future(channelHandlerContext.getChannel());
                    channelBuffer = ChannelBuffers.EMPTY_BUFFER;
                }
            } finally {
                this.f10027z.deflateEnd();
                this.f10027z.next_in = null;
                this.f10027z.next_out = null;
            }
        }
        if (channelBuffer != null) {
            Channels.write(channelHandlerContext, future, channelBuffer);
        }
        if (channelEvent != null) {
            future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.compression.ZlibEncoder.1
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
