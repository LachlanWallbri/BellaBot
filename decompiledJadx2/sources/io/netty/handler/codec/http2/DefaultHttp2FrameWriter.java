package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.collection.CharObjectMap;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttp2FrameWriter implements Http2FrameWriter, Http2FrameSizePolicy, Http2FrameWriter.Configuration {
    private static final String STREAM_DEPENDENCY = "Stream Dependency";
    private static final String STREAM_ID = "Stream ID";
    private static final ByteBuf ZERO_BUFFER = Unpooled.unreleasableBuffer(Unpooled.directBuffer(255).writeZero(255)).asReadOnly();
    private final Http2HeadersEncoder headersEncoder;
    private int maxFrameSize;

    private static int paddingBytes(int i) {
        return i - 1;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public Http2FrameWriter.Configuration configuration() {
        return this;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter.Configuration
    public Http2FrameSizePolicy frameSizePolicy() {
        return this;
    }

    public DefaultHttp2FrameWriter() {
        this(new DefaultHttp2HeadersEncoder());
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        this(new DefaultHttp2HeadersEncoder(sensitivityDetector));
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder.SensitivityDetector sensitivityDetector, boolean z) {
        this(new DefaultHttp2HeadersEncoder(sensitivityDetector, z));
    }

    public DefaultHttp2FrameWriter(Http2HeadersEncoder http2HeadersEncoder) {
        this.headersEncoder = http2HeadersEncoder;
        this.maxFrameSize = 16384;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter.Configuration
    public Http2HeadersEncoder.Configuration headersConfiguration() {
        return this.headersEncoder.configuration();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameSizePolicy
    public void maxFrameSize(int i) throws Http2Exception {
        if (!Http2CodecUtil.isMaxFrameSizeValid(i)) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid MAX_FRAME_SIZE specified in sent settings: %d", Integer.valueOf(i));
        }
        this.maxFrameSize = i;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameSizePolicy
    public int maxFrameSize() {
        return this.maxFrameSize;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0169 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013d A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #1 {all -> 0x0157, blocks: (B:27:0x00d5, B:32:0x00fd, B:35:0x0104, B:38:0x011a, B:57:0x012c, B:58:0x0137, B:60:0x013d), top: B:26:0x00d5 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0103  */
    @Override // io.netty.handler.codec.http2.Http2DataWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        Throwable th;
        ByteBuf byteBuf2;
        ByteBuf byteBuf3;
        ByteBuf slice;
        ByteBuf byteBuf4;
        int i3;
        int i4;
        int min;
        int min2;
        ByteBuf byteBuf5 = byteBuf;
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        try {
            verifyStreamId(i, STREAM_ID);
            Http2CodecUtil.verifyPadding(i2);
            int readableBytes = byteBuf.readableBytes();
            Http2Flags http2Flags = new Http2Flags();
            http2Flags.endOfStream(false);
            http2Flags.paddingPresent(false);
            if (readableBytes > this.maxFrameSize) {
                byteBuf3 = channelHandlerContext.alloc().buffer(9);
                try {
                    Http2CodecUtil.writeFrameHeaderInternal(byteBuf3, this.maxFrameSize, (byte) 0, http2Flags, i);
                    do {
                        channelHandlerContext.write(byteBuf3.retainedSlice(), simpleChannelPromiseAggregator.newPromise());
                        channelHandlerContext.write(byteBuf5.readRetainedSlice(this.maxFrameSize), simpleChannelPromiseAggregator.newPromise());
                        readableBytes -= this.maxFrameSize;
                    } while (readableBytes > this.maxFrameSize);
                } catch (Throwable th2) {
                    th = th2;
                    byteBuf2 = byteBuf3;
                    if (byteBuf2 != null) {
                    }
                    if (byteBuf5 != null) {
                    }
                    return simpleChannelPromiseAggregator;
                }
            } else {
                byteBuf3 = null;
            }
            try {
                if (i2 == 0) {
                    if (byteBuf3 != null) {
                        byteBuf3.release();
                    }
                    ByteBuf buffer = channelHandlerContext.alloc().buffer(9);
                    http2Flags.endOfStream(z);
                    Http2CodecUtil.writeFrameHeaderInternal(buffer, readableBytes, (byte) 0, http2Flags, i);
                    channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
                    channelHandlerContext.write(byteBuf5.readSlice(readableBytes), simpleChannelPromiseAggregator.newPromise());
                } else {
                    if (readableBytes == this.maxFrameSize) {
                        readableBytes -= this.maxFrameSize;
                        if (byteBuf3 == null) {
                            slice = channelHandlerContext.alloc().buffer(9);
                            Http2CodecUtil.writeFrameHeaderInternal(slice, this.maxFrameSize, (byte) 0, http2Flags, i);
                        } else {
                            slice = byteBuf3.slice();
                            byteBuf3 = null;
                        }
                        channelHandlerContext.write(slice, simpleChannelPromiseAggregator.newPromise());
                        channelHandlerContext.write(byteBuf5.readSlice(this.maxFrameSize), simpleChannelPromiseAggregator.newPromise());
                        byteBuf4 = byteBuf3;
                        byteBuf5 = null;
                    } else if (byteBuf3 != null) {
                        byteBuf3.release();
                        i3 = readableBytes;
                        byteBuf4 = null;
                        i4 = i2;
                        while (true) {
                            try {
                                min = Math.min(i3, this.maxFrameSize);
                                boolean z2 = true;
                                min2 = Math.min(i4, Math.max(0, (this.maxFrameSize - 1) - min));
                                i4 -= min2;
                                i3 -= min;
                                ByteBuf buffer2 = channelHandlerContext.alloc().buffer(10);
                                http2Flags.endOfStream(!z && i3 == 0 && i4 == 0);
                                if (min2 > 0) {
                                    z2 = false;
                                }
                                http2Flags.paddingPresent(z2);
                                Http2CodecUtil.writeFrameHeaderInternal(buffer2, min2 + min, (byte) 0, http2Flags, i);
                                writePaddingLength(buffer2, min2);
                                channelHandlerContext.write(buffer2, simpleChannelPromiseAggregator.newPromise());
                                if (min != 0) {
                                    if (i3 == 0) {
                                        try {
                                            channelHandlerContext.write(byteBuf5.readSlice(min), simpleChannelPromiseAggregator.newPromise());
                                            byteBuf5 = null;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            byteBuf2 = byteBuf4;
                                            byteBuf5 = null;
                                            if (byteBuf2 != null) {
                                                byteBuf2.release();
                                            }
                                            if (byteBuf5 != null) {
                                                try {
                                                    byteBuf5.release();
                                                } finally {
                                                    simpleChannelPromiseAggregator.setFailure(th);
                                                    simpleChannelPromiseAggregator.doneAllocatingPromises();
                                                }
                                            }
                                            return simpleChannelPromiseAggregator;
                                        }
                                    } else {
                                        channelHandlerContext.write(byteBuf5.readRetainedSlice(min), simpleChannelPromiseAggregator.newPromise());
                                    }
                                }
                                if (paddingBytes(min2) > 0) {
                                    channelHandlerContext.write(ZERO_BUFFER.slice(0, paddingBytes(min2)), simpleChannelPromiseAggregator.newPromise());
                                }
                                if (i3 != 0 && i4 == 0) {
                                    break;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                byteBuf2 = byteBuf4;
                                if (byteBuf2 != null) {
                                }
                                if (byteBuf5 != null) {
                                }
                                return simpleChannelPromiseAggregator;
                            }
                        }
                    } else {
                        byteBuf4 = byteBuf3;
                    }
                    i3 = readableBytes;
                    i4 = i2;
                    while (true) {
                        min = Math.min(i3, this.maxFrameSize);
                        boolean z22 = true;
                        min2 = Math.min(i4, Math.max(0, (this.maxFrameSize - 1) - min));
                        i4 -= min2;
                        i3 -= min;
                        ByteBuf buffer22 = channelHandlerContext.alloc().buffer(10);
                        http2Flags.endOfStream(!z && i3 == 0 && i4 == 0);
                        if (min2 > 0) {
                        }
                        http2Flags.paddingPresent(z22);
                        Http2CodecUtil.writeFrameHeaderInternal(buffer22, min2 + min, (byte) 0, http2Flags, i);
                        writePaddingLength(buffer22, min2);
                        channelHandlerContext.write(buffer22, simpleChannelPromiseAggregator.newPromise());
                        if (min != 0) {
                        }
                        if (paddingBytes(min2) > 0) {
                        }
                        if (i3 != 0) {
                        }
                    }
                }
                return simpleChannelPromiseAggregator.doneAllocatingPromises();
            } catch (Throwable th5) {
                th = th5;
                byteBuf2 = byteBuf3;
            }
        } catch (Throwable th6) {
            th = th6;
            byteBuf2 = null;
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        return writeHeadersInternal(channelHandlerContext, i, http2Headers, i2, z, false, 0, (short) 0, false, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        return writeHeadersInternal(channelHandlerContext, i, http2Headers, i3, z2, true, i2, s, z, channelPromise);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        try {
            verifyStreamId(i, STREAM_ID);
            verifyStreamId(i2, STREAM_DEPENDENCY);
            verifyWeight(s);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(14);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 5, (byte) 2, new Http2Flags(), i);
            if (z) {
                i2 = (int) (i2 | 2147483648L);
            }
            buffer.writeInt(i2);
            buffer.writeByte(s - 1);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        try {
            verifyStreamId(i, STREAM_ID);
            verifyErrorCode(j);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(13);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 4, (byte) 3, new Http2Flags(), i);
            buffer.writeInt((int) j);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettings(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings, ChannelPromise channelPromise) {
        try {
            ObjectUtil.checkNotNull(http2Settings, "settings");
            int size = http2Settings.size() * 6;
            ByteBuf buffer = channelHandlerContext.alloc().buffer((http2Settings.size() * 6) + 9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, size, (byte) 4, new Http2Flags(), 0);
            for (CharObjectMap.PrimitiveEntry<Long> primitiveEntry : http2Settings.entries()) {
                buffer.writeChar(primitiveEntry.key());
                buffer.writeInt(primitiveEntry.value().intValue());
            }
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        try {
            ByteBuf buffer = channelHandlerContext.alloc().buffer(9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 0, (byte) 4, new Http2Flags().ack(true), 0);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        Http2Flags ack = z ? new Http2Flags().ack(true) : new Http2Flags();
        ByteBuf buffer = channelHandlerContext.alloc().buffer(17);
        Http2CodecUtil.writeFrameHeaderInternal(buffer, 8, (byte) 6, ack, 0);
        buffer.writeLong(j);
        return channelHandlerContext.write(buffer, channelPromise);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a1, code lost:
    
        if (r13 != null) goto L26;
     */
    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ChannelFuture writePushPromise(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3, ChannelPromise channelPromise) {
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        ByteBuf byteBuf = null;
        try {
            try {
                verifyStreamId(i, STREAM_ID);
                verifyStreamId(i2, "Promised Stream ID");
                Http2CodecUtil.verifyPadding(i3);
                byteBuf = channelHandlerContext.alloc().buffer();
                this.headersEncoder.encodeHeaders(i, http2Headers, byteBuf);
                boolean z = true;
                Http2Flags paddingPresent = new Http2Flags().paddingPresent(i3 > 0);
                int i4 = i3 + 4;
                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(Math.min(byteBuf.readableBytes(), this.maxFrameSize - i4));
                if (byteBuf.isReadable()) {
                    z = false;
                }
                paddingPresent.endOfHeaders(z);
                int readableBytes = readRetainedSlice.readableBytes() + i4;
                ByteBuf buffer = channelHandlerContext.alloc().buffer(14);
                Http2CodecUtil.writeFrameHeaderInternal(buffer, readableBytes, (byte) 5, paddingPresent, i);
                writePaddingLength(buffer, i3);
                buffer.writeInt(i2);
                channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
                channelHandlerContext.write(readRetainedSlice, simpleChannelPromiseAggregator.newPromise());
                if (paddingBytes(i3) > 0) {
                    channelHandlerContext.write(ZERO_BUFFER.slice(0, paddingBytes(i3)), simpleChannelPromiseAggregator.newPromise());
                }
                if (!paddingPresent.endOfHeaders()) {
                    writeContinuationFrames(channelHandlerContext, i, byteBuf, i3, simpleChannelPromiseAggregator);
                }
            } catch (Http2Exception e) {
                simpleChannelPromiseAggregator.setFailure((Throwable) e);
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
                simpleChannelPromiseAggregator.doneAllocatingPromises();
                PlatformDependent.throwException(th);
                if (byteBuf != null) {
                }
                return simpleChannelPromiseAggregator.doneAllocatingPromises();
            }
        } finally {
            if (0 != 0) {
                byteBuf.release();
            }
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        try {
            verifyStreamOrConnectionId(i, "Last Stream ID");
            verifyErrorCode(j);
            int readableBytes = byteBuf.readableBytes() + 8;
            ByteBuf buffer = channelHandlerContext.alloc().buffer(17);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, readableBytes, (byte) 7, new Http2Flags(), 0);
            buffer.writeInt(i);
            buffer.writeInt((int) j);
            channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
            try {
                channelHandlerContext.write(byteBuf, simpleChannelPromiseAggregator.newPromise());
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
            }
            return simpleChannelPromiseAggregator.doneAllocatingPromises();
        } catch (Throwable th2) {
            try {
                byteBuf.release();
                return simpleChannelPromiseAggregator;
            } finally {
                simpleChannelPromiseAggregator.setFailure(th2);
                simpleChannelPromiseAggregator.doneAllocatingPromises();
            }
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        try {
            verifyStreamOrConnectionId(i, STREAM_ID);
            verifyWindowSizeIncrement(i2);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(13);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, 4, (byte) 8, new Http2Flags(), i);
            buffer.writeInt(i2);
            return channelHandlerContext.write(buffer, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        try {
            verifyStreamOrConnectionId(i, STREAM_ID);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(9);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, byteBuf.readableBytes(), b, http2Flags, i);
            channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
            try {
                channelHandlerContext.write(byteBuf, simpleChannelPromiseAggregator.newPromise());
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
            }
            return simpleChannelPromiseAggregator.doneAllocatingPromises();
        } catch (Throwable th2) {
            try {
                byteBuf.release();
                return simpleChannelPromiseAggregator;
            } finally {
                simpleChannelPromiseAggregator.setFailure(th2);
                simpleChannelPromiseAggregator.doneAllocatingPromises();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00da, code lost:
    
        if (r7 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ee, code lost:
    
        if (r7 == null) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ChannelFuture writeHeadersInternal(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, boolean z2, int i3, short s, boolean z3, ChannelPromise channelPromise) {
        int i4 = i3;
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        ByteBuf byteBuf = null;
        try {
            try {
                try {
                    verifyStreamId(i, STREAM_ID);
                    if (z2) {
                        verifyStreamOrConnectionId(i4, STREAM_DEPENDENCY);
                        Http2CodecUtil.verifyPadding(i2);
                        verifyWeight(s);
                    }
                    byteBuf = channelHandlerContext.alloc().buffer();
                    this.headersEncoder.encodeHeaders(i, http2Headers, byteBuf);
                    Http2Flags paddingPresent = new Http2Flags().endOfStream(z).priorityPresent(z2).paddingPresent(i2 > 0);
                    int numPriorityBytes = paddingPresent.getNumPriorityBytes() + i2;
                    ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(Math.min(byteBuf.readableBytes(), this.maxFrameSize - numPriorityBytes));
                    paddingPresent.endOfHeaders(!byteBuf.isReadable());
                    int readableBytes = readRetainedSlice.readableBytes() + numPriorityBytes;
                    ByteBuf buffer = channelHandlerContext.alloc().buffer(15);
                    Http2CodecUtil.writeFrameHeaderInternal(buffer, readableBytes, (byte) 1, paddingPresent, i);
                    writePaddingLength(buffer, i2);
                    if (z2) {
                        if (z3) {
                            i4 = (int) (i4 | 2147483648L);
                        }
                        buffer.writeInt(i4);
                        buffer.writeByte(s - 1);
                    }
                    channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
                    channelHandlerContext.write(readRetainedSlice, simpleChannelPromiseAggregator.newPromise());
                    if (paddingBytes(i2) > 0) {
                        channelHandlerContext.write(ZERO_BUFFER.slice(0, paddingBytes(i2)), simpleChannelPromiseAggregator.newPromise());
                    }
                    if (!paddingPresent.endOfHeaders()) {
                        writeContinuationFrames(channelHandlerContext, i, byteBuf, i2, simpleChannelPromiseAggregator);
                    }
                } catch (Http2Exception e) {
                    simpleChannelPromiseAggregator.setFailure((Throwable) e);
                }
            } catch (Throwable th) {
                simpleChannelPromiseAggregator.setFailure(th);
                simpleChannelPromiseAggregator.doneAllocatingPromises();
                PlatformDependent.throwException(th);
                if (byteBuf != null) {
                    byteBuf.release();
                }
                return simpleChannelPromiseAggregator.doneAllocatingPromises();
            }
        } catch (Throwable th2) {
            if (byteBuf != null) {
                byteBuf.release();
            }
            throw th2;
        }
    }

    private ChannelFuture writeContinuationFrames(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator) {
        Http2Flags paddingPresent = new Http2Flags().paddingPresent(i2 > 0);
        int i3 = this.maxFrameSize - i2;
        if (i3 <= 0) {
            return simpleChannelPromiseAggregator.setFailure((Throwable) new IllegalArgumentException("Padding [" + i2 + "] is too large for max frame size [" + this.maxFrameSize + "]"));
        }
        if (byteBuf.isReadable()) {
            int min = Math.min(byteBuf.readableBytes(), i3) + i2;
            ByteBuf buffer = channelHandlerContext.alloc().buffer(10);
            Http2CodecUtil.writeFrameHeaderInternal(buffer, min, (byte) 9, paddingPresent, i);
            writePaddingLength(buffer, i2);
            do {
                int min2 = Math.min(byteBuf.readableBytes(), i3);
                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(min2);
                int i4 = min2 + i2;
                if (byteBuf.isReadable()) {
                    channelHandlerContext.write(buffer.retain(), simpleChannelPromiseAggregator.newPromise());
                } else {
                    paddingPresent = paddingPresent.endOfHeaders(true);
                    buffer.release();
                    buffer = channelHandlerContext.alloc().buffer(10);
                    Http2CodecUtil.writeFrameHeaderInternal(buffer, i4, (byte) 9, paddingPresent, i);
                    writePaddingLength(buffer, i2);
                    channelHandlerContext.write(buffer, simpleChannelPromiseAggregator.newPromise());
                }
                channelHandlerContext.write(readRetainedSlice, simpleChannelPromiseAggregator.newPromise());
                if (paddingBytes(i2) > 0) {
                    channelHandlerContext.write(ZERO_BUFFER.slice(0, paddingBytes(i2)), simpleChannelPromiseAggregator.newPromise());
                }
            } while (byteBuf.isReadable());
        }
        return simpleChannelPromiseAggregator;
    }

    private static void writePaddingLength(ByteBuf byteBuf, int i) {
        if (i > 0) {
            byteBuf.writeByte(i - 1);
        }
    }

    private static void verifyStreamId(int i, String str) {
        if (i > 0) {
            return;
        }
        throw new IllegalArgumentException(str + " must be > 0");
    }

    private static void verifyStreamOrConnectionId(int i, String str) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " must be >= 0");
    }

    private static void verifyWeight(short s) {
        if (s < 1 || s > 256) {
            throw new IllegalArgumentException("Invalid weight: " + ((int) s));
        }
    }

    private static void verifyErrorCode(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("Invalid errorCode: " + j);
        }
    }

    private static void verifyWindowSizeIncrement(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("WindowSizeIncrement must be >= 0");
        }
    }

    private static void verifyPingPayload(ByteBuf byteBuf) {
        if (byteBuf == null || byteBuf.readableBytes() != 8) {
            throw new IllegalArgumentException("Opaque data must be 8 bytes");
        }
    }
}
