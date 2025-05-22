package org.jboss.netty.handler.codec.replay;

import java.lang.Enum;
import java.net.SocketAddress;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/* loaded from: classes7.dex */
public abstract class ReplayingDecoder<T extends Enum<T>> extends FrameDecoder {
    private int checkpoint;
    private boolean needsCleanup;
    private final ReplayingDecoderBuffer replayable;
    private T state;

    protected abstract Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, T t) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReplayingDecoder() {
        this((Enum) null);
    }

    protected ReplayingDecoder(boolean z) {
        this(null, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReplayingDecoder(T t) {
        this(t, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReplayingDecoder(T t, boolean z) {
        super(z);
        this.replayable = new ReplayingDecoderBuffer(this);
        this.state = t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    public ChannelBuffer internalBuffer() {
        return super.internalBuffer();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkpoint() {
        ChannelBuffer channelBuffer = this.cumulation;
        if (channelBuffer != null) {
            this.checkpoint = channelBuffer.readerIndex();
        } else {
            this.checkpoint = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkpoint(T t) {
        checkpoint();
        setState(t);
    }

    protected T getState() {
        return this.state;
    }

    protected T setState(T t) {
        T t2 = this.state;
        this.state = t;
        return t2;
    }

    protected Object decodeLast(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, T t) throws Exception {
        return decode(channelHandlerContext, channel, channelBuffer, t);
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected final Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        return decode(channelHandlerContext, channel, channelBuffer, this.state);
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected final Object decodeLast(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        return decodeLast(channelHandlerContext, channel, channelBuffer, this.state);
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        boolean z;
        Object message = messageEvent.getMessage();
        if (!(message instanceof ChannelBuffer)) {
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        ChannelBuffer channelBuffer = (ChannelBuffer) message;
        if (channelBuffer.readable()) {
            this.needsCleanup = true;
            if (this.cumulation == null) {
                this.cumulation = channelBuffer;
                int readerIndex = channelBuffer.readerIndex();
                int readableBytes = channelBuffer.readableBytes();
                try {
                    callDecode(channelHandlerContext, messageEvent.getChannel(), channelBuffer, this.replayable, messageEvent.getRemoteAddress());
                    int readableBytes2 = channelBuffer.readableBytes();
                    if (readableBytes2 > 0) {
                        int capacity = channelBuffer.capacity();
                        z = readableBytes2 != capacity && capacity > getMaxCumulationBufferCapacity();
                        int i = this.checkpoint;
                        if (i > 0) {
                            int i2 = readableBytes - (i - readerIndex);
                            if (z) {
                                ChannelBuffer newCumulationBuffer = newCumulationBuffer(channelHandlerContext, i2);
                                this.cumulation = newCumulationBuffer;
                                newCumulationBuffer.writeBytes(channelBuffer, this.checkpoint, i2);
                                return;
                            }
                            this.cumulation = channelBuffer.slice(i, i2);
                            return;
                        }
                        if (i != 0) {
                            if (z) {
                                ChannelBuffer newCumulationBuffer2 = newCumulationBuffer(channelHandlerContext, channelBuffer.readableBytes());
                                this.cumulation = newCumulationBuffer2;
                                newCumulationBuffer2.writeBytes(channelBuffer);
                                return;
                            }
                            this.cumulation = channelBuffer;
                            return;
                        }
                        if (z) {
                            ChannelBuffer newCumulationBuffer3 = newCumulationBuffer(channelHandlerContext, readableBytes);
                            this.cumulation = newCumulationBuffer3;
                            newCumulationBuffer3.writeBytes(channelBuffer, readerIndex, readableBytes);
                            newCumulationBuffer3.readerIndex(channelBuffer.readerIndex());
                            return;
                        }
                        ChannelBuffer slice = channelBuffer.slice(readerIndex, readableBytes);
                        this.cumulation = slice;
                        slice.readerIndex(channelBuffer.readerIndex());
                        return;
                    }
                    this.cumulation = null;
                    return;
                } catch (Throwable th) {
                    int readableBytes3 = channelBuffer.readableBytes();
                    if (readableBytes3 > 0) {
                        int capacity2 = channelBuffer.capacity();
                        z = readableBytes3 != capacity2 && capacity2 > getMaxCumulationBufferCapacity();
                        int i3 = this.checkpoint;
                        if (i3 > 0) {
                            int i4 = readableBytes - (i3 - readerIndex);
                            if (z) {
                                ChannelBuffer newCumulationBuffer4 = newCumulationBuffer(channelHandlerContext, i4);
                                this.cumulation = newCumulationBuffer4;
                                newCumulationBuffer4.writeBytes(channelBuffer, this.checkpoint, i4);
                            } else {
                                this.cumulation = channelBuffer.slice(i3, i4);
                            }
                        } else if (i3 == 0) {
                            if (z) {
                                ChannelBuffer newCumulationBuffer5 = newCumulationBuffer(channelHandlerContext, readableBytes);
                                this.cumulation = newCumulationBuffer5;
                                newCumulationBuffer5.writeBytes(channelBuffer, readerIndex, readableBytes);
                                newCumulationBuffer5.readerIndex(channelBuffer.readerIndex());
                            } else {
                                ChannelBuffer slice2 = channelBuffer.slice(readerIndex, readableBytes);
                                this.cumulation = slice2;
                                slice2.readerIndex(channelBuffer.readerIndex());
                            }
                        } else if (z) {
                            ChannelBuffer newCumulationBuffer6 = newCumulationBuffer(channelHandlerContext, channelBuffer.readableBytes());
                            this.cumulation = newCumulationBuffer6;
                            newCumulationBuffer6.writeBytes(channelBuffer);
                        } else {
                            this.cumulation = channelBuffer;
                        }
                    } else {
                        this.cumulation = null;
                    }
                    throw th;
                }
            }
            ChannelBuffer appendToCumulation = appendToCumulation(channelBuffer);
            try {
                callDecode(channelHandlerContext, messageEvent.getChannel(), appendToCumulation, this.replayable, messageEvent.getRemoteAddress());
            } finally {
                updateCumulation(channelHandlerContext, appendToCumulation);
            }
        }
    }

    private void callDecode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, ChannelBuffer channelBuffer2, SocketAddress socketAddress) throws Exception {
        while (channelBuffer.readable()) {
            int readerIndex = channelBuffer.readerIndex();
            this.checkpoint = readerIndex;
            Object obj = null;
            T t = this.state;
            try {
                obj = decode(channelHandlerContext, channel, channelBuffer2, t);
            } catch (ReplayError unused) {
                int i = this.checkpoint;
                if (i >= 0) {
                    channelBuffer.readerIndex(i);
                }
            }
            if (obj == null) {
                if (readerIndex == channelBuffer.readerIndex() && t == this.state) {
                    throw new IllegalStateException("null cannot be returned if no data is consumed and state didn't change.");
                    break;
                }
            } else {
                if (obj == null) {
                    return;
                }
                if (readerIndex == channelBuffer.readerIndex() && t == this.state) {
                    throw new IllegalStateException("decode() method must consume at least one byte if it returned a decoded message (caused by: " + getClass() + ")");
                }
                unfoldAndFireMessageReceived(channelHandlerContext, socketAddress, obj);
            }
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected void cleanup(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        ChannelBuffer channelBuffer;
        try {
            channelBuffer = this.cumulation;
        } catch (ReplayError unused) {
        } catch (Throwable th) {
            channelHandlerContext.sendUpstream(channelStateEvent);
            throw th;
        }
        if (this.needsCleanup) {
            this.needsCleanup = false;
            this.replayable.terminate();
            if (channelBuffer != null && channelBuffer.readable()) {
                callDecode(channelHandlerContext, channelStateEvent.getChannel(), channelBuffer, this.replayable, null);
            }
            Object decodeLast = decodeLast(channelHandlerContext, channelStateEvent.getChannel(), this.replayable, this.state);
            this.cumulation = null;
            if (decodeLast != null) {
                unfoldAndFireMessageReceived(channelHandlerContext, null, decodeLast);
            }
            channelHandlerContext.sendUpstream(channelStateEvent);
            return;
        }
        channelHandlerContext.sendUpstream(channelStateEvent);
    }
}
