package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2FrameReader;
import io.netty.handler.codec.http2.Http2HeadersDecoder;
import io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttp2FrameReader implements Http2FrameReader, Http2FrameSizePolicy, Http2FrameReader.Configuration {
    private Http2Flags flags;
    private byte frameType;
    private HeadersContinuation headersContinuation;
    private final Http2HeadersDecoder headersDecoder;
    private int maxFrameSize;
    private int payloadLength;
    private boolean readError;
    private boolean readingHeaders;
    private int streamId;

    private static int lengthWithoutTrailingPadding(int i, int i2) {
        return i2 == 0 ? i : i - (i2 - 1);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameReader
    public Http2FrameReader.Configuration configuration() {
        return this;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameReader.Configuration
    public Http2FrameSizePolicy frameSizePolicy() {
        return this;
    }

    public DefaultHttp2FrameReader() {
        this(true);
    }

    public DefaultHttp2FrameReader(boolean z) {
        this(new DefaultHttp2HeadersDecoder(z));
    }

    public DefaultHttp2FrameReader(Http2HeadersDecoder http2HeadersDecoder) {
        this.readingHeaders = true;
        this.headersDecoder = http2HeadersDecoder;
        this.maxFrameSize = 16384;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameReader.Configuration
    public Http2HeadersDecoder.Configuration headersConfiguration() {
        return this.headersDecoder.configuration();
    }

    @Override // io.netty.handler.codec.http2.Http2FrameSizePolicy
    public void maxFrameSize(int i) throws Http2Exception {
        if (!Http2CodecUtil.isMaxFrameSizeValid(i)) {
            throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Invalid MAX_FRAME_SIZE specified in sent settings: %d", Integer.valueOf(i));
        }
        this.maxFrameSize = i;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameSizePolicy
    public int maxFrameSize() {
        return this.maxFrameSize;
    }

    @Override // io.netty.handler.codec.http2.Http2FrameReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        closeHeadersContinuation();
    }

    private void closeHeadersContinuation() {
        HeadersContinuation headersContinuation = this.headersContinuation;
        if (headersContinuation != null) {
            headersContinuation.close();
            this.headersContinuation = null;
        }
    }

    @Override // io.netty.handler.codec.http2.Http2FrameReader
    public void readFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        if (this.readError) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        do {
            try {
                if (this.readingHeaders) {
                    processHeaderState(byteBuf);
                    if (this.readingHeaders) {
                        return;
                    }
                }
                processPayloadState(channelHandlerContext, byteBuf, http2FrameListener);
                if (!this.readingHeaders) {
                    return;
                }
            } catch (Http2Exception e) {
                this.readError = !Http2Exception.isStreamError(e);
                throw e;
            } catch (RuntimeException e2) {
                this.readError = true;
                throw e2;
            } catch (Throwable th) {
                this.readError = true;
                PlatformDependent.throwException(th);
                return;
            }
        } while (byteBuf.isReadable());
    }

    private void processHeaderState(ByteBuf byteBuf) throws Http2Exception {
        if (byteBuf.readableBytes() < 9) {
            return;
        }
        int readUnsignedMedium = byteBuf.readUnsignedMedium();
        this.payloadLength = readUnsignedMedium;
        if (readUnsignedMedium > this.maxFrameSize) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length: %d exceeds maximum: %d", Integer.valueOf(this.payloadLength), Integer.valueOf(this.maxFrameSize));
        }
        this.frameType = byteBuf.readByte();
        this.flags = new Http2Flags(byteBuf.readUnsignedByte());
        this.streamId = Http2CodecUtil.readUnsignedInt(byteBuf);
        this.readingHeaders = false;
        switch (this.frameType) {
            case 0:
                verifyDataFrame();
                return;
            case 1:
                verifyHeadersFrame();
                return;
            case 2:
                verifyPriorityFrame();
                return;
            case 3:
                verifyRstStreamFrame();
                return;
            case 4:
                verifySettingsFrame();
                return;
            case 5:
                verifyPushPromiseFrame();
                return;
            case 6:
                verifyPingFrame();
                return;
            case 7:
                verifyGoAwayFrame();
                return;
            case 8:
                verifyWindowUpdateFrame();
                return;
            case 9:
                verifyContinuationFrame();
                return;
            default:
                verifyUnknownFrame();
                return;
        }
    }

    private void processPayloadState(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        int readableBytes = byteBuf.readableBytes();
        int i = this.payloadLength;
        if (readableBytes < i) {
            return;
        }
        ByteBuf readSlice = byteBuf.readSlice(i);
        this.readingHeaders = true;
        switch (this.frameType) {
            case 0:
                readDataFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 1:
                readHeadersFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 2:
                readPriorityFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 3:
                readRstStreamFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 4:
                readSettingsFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 5:
                readPushPromiseFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 6:
                readPingFrame(channelHandlerContext, readSlice.readLong(), http2FrameListener);
                return;
            case 7:
                readGoAwayFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 8:
                readWindowUpdateFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
            case 9:
                readContinuationFrame(readSlice, http2FrameListener);
                return;
            default:
                readUnknownFrame(channelHandlerContext, readSlice, http2FrameListener);
                return;
        }
    }

    private void verifyDataFrame() throws Http2Exception {
        verifyAssociatedWithAStream();
        verifyNotProcessingHeaders();
        verifyPayloadLength(this.payloadLength);
        if (this.payloadLength < this.flags.getPaddingPresenceFieldLength()) {
            throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyHeadersFrame() throws Http2Exception {
        verifyAssociatedWithAStream();
        verifyNotProcessingHeaders();
        verifyPayloadLength(this.payloadLength);
        if (this.payloadLength >= this.flags.getPaddingPresenceFieldLength() + this.flags.getNumPriorityBytes()) {
            return;
        }
        throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Frame length too small." + this.payloadLength, new Object[0]);
    }

    private void verifyPriorityFrame() throws Http2Exception {
        verifyAssociatedWithAStream();
        verifyNotProcessingHeaders();
        if (this.payloadLength != 5) {
            throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyRstStreamFrame() throws Http2Exception {
        verifyAssociatedWithAStream();
        verifyNotProcessingHeaders();
        if (this.payloadLength != 4) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifySettingsFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
        verifyPayloadLength(this.payloadLength);
        if (this.streamId != 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.", new Object[0]);
        }
        if (this.flags.ack() && this.payloadLength > 0) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Ack settings frame must have an empty payload.", new Object[0]);
        }
        if (this.payloadLength % 6 > 0) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d invalid.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyPushPromiseFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
        verifyPayloadLength(this.payloadLength);
        if (this.payloadLength < this.flags.getPaddingPresenceFieldLength() + 4) {
            throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyPingFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
        if (this.streamId != 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.", new Object[0]);
        }
        if (this.payloadLength != 8) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d incorrect size for ping.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyGoAwayFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
        verifyPayloadLength(this.payloadLength);
        if (this.streamId != 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A stream ID must be zero.", new Object[0]);
        }
        if (this.payloadLength < 8) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyWindowUpdateFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
        verifyStreamOrConnectionId(this.streamId, "Stream ID");
        if (this.payloadLength != 4) {
            throw Http2Exception.connectionError(Http2Error.FRAME_SIZE_ERROR, "Invalid frame length %d.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyContinuationFrame() throws Http2Exception {
        verifyAssociatedWithAStream();
        verifyPayloadLength(this.payloadLength);
        HeadersContinuation headersContinuation = this.headersContinuation;
        if (headersContinuation == null) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received %s frame but not currently processing headers.", Byte.valueOf(this.frameType));
        }
        if (this.streamId != headersContinuation.getStreamId()) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Continuation stream ID does not match pending headers. Expected %d, but received %d.", Integer.valueOf(this.headersContinuation.getStreamId()), Integer.valueOf(this.streamId));
        }
        if (this.payloadLength < this.flags.getPaddingPresenceFieldLength()) {
            throw Http2Exception.streamError(this.streamId, Http2Error.FRAME_SIZE_ERROR, "Frame length %d too small for padding.", Integer.valueOf(this.payloadLength));
        }
    }

    private void verifyUnknownFrame() throws Http2Exception {
        verifyNotProcessingHeaders();
    }

    private void readDataFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        int readPadding = readPadding(byteBuf);
        verifyPadding(readPadding);
        http2FrameListener.onDataRead(channelHandlerContext, this.streamId, byteBuf.readSlice(lengthWithoutTrailingPadding(byteBuf.readableBytes(), readPadding)), readPadding, this.flags.endOfStream());
        byteBuf.skipBytes(byteBuf.readableBytes());
    }

    private void readHeadersFrame(final ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        final int i = this.streamId;
        final Http2Flags http2Flags = this.flags;
        final int readPadding = readPadding(byteBuf);
        verifyPadding(readPadding);
        if (this.flags.priorityPresent()) {
            long readUnsignedInt = byteBuf.readUnsignedInt();
            final boolean z = (2147483648L & readUnsignedInt) != 0;
            final int i2 = (int) (readUnsignedInt & 2147483647L);
            int i3 = this.streamId;
            if (i2 == i3) {
                throw Http2Exception.streamError(i3, Http2Error.PROTOCOL_ERROR, "A stream cannot depend on itself.", new Object[0]);
            }
            final short readUnsignedByte = (short) (byteBuf.readUnsignedByte() + 1);
            ByteBuf readSlice = byteBuf.readSlice(lengthWithoutTrailingPadding(byteBuf.readableBytes(), readPadding));
            HeadersContinuation headersContinuation = new HeadersContinuation() { // from class: io.netty.handler.codec.http2.DefaultHttp2FrameReader.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
                public int getStreamId() {
                    return i;
                }

                @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
                public void processFragment(boolean z2, ByteBuf byteBuf2, Http2FrameListener http2FrameListener2) throws Http2Exception {
                    HeadersBlockBuilder headersBlockBuilder = headersBlockBuilder();
                    headersBlockBuilder.addFragment(byteBuf2, channelHandlerContext.alloc(), z2);
                    if (z2) {
                        http2FrameListener2.onHeadersRead(channelHandlerContext, i, headersBlockBuilder.headers(), i2, readUnsignedByte, z, readPadding, http2Flags.endOfStream());
                    }
                }
            };
            this.headersContinuation = headersContinuation;
            headersContinuation.processFragment(this.flags.endOfHeaders(), readSlice, http2FrameListener);
            resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
            return;
        }
        this.headersContinuation = new HeadersContinuation() { // from class: io.netty.handler.codec.http2.DefaultHttp2FrameReader.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
            public int getStreamId() {
                return i;
            }

            @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
            public void processFragment(boolean z2, ByteBuf byteBuf2, Http2FrameListener http2FrameListener2) throws Http2Exception {
                HeadersBlockBuilder headersBlockBuilder = headersBlockBuilder();
                headersBlockBuilder.addFragment(byteBuf2, channelHandlerContext.alloc(), z2);
                if (z2) {
                    http2FrameListener2.onHeadersRead(channelHandlerContext, i, headersBlockBuilder.headers(), readPadding, http2Flags.endOfStream());
                }
            }
        };
        this.headersContinuation.processFragment(this.flags.endOfHeaders(), byteBuf.readSlice(lengthWithoutTrailingPadding(byteBuf.readableBytes(), readPadding)), http2FrameListener);
        resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
    }

    private void resetHeadersContinuationIfEnd(boolean z) {
        if (z) {
            closeHeadersContinuation();
        }
    }

    private void readPriorityFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        long readUnsignedInt = byteBuf.readUnsignedInt();
        boolean z = (2147483648L & readUnsignedInt) != 0;
        int i = (int) (readUnsignedInt & 2147483647L);
        int i2 = this.streamId;
        if (i == i2) {
            throw Http2Exception.streamError(i2, Http2Error.PROTOCOL_ERROR, "A stream cannot depend on itself.", new Object[0]);
        }
        http2FrameListener.onPriorityRead(channelHandlerContext, this.streamId, i, (short) (byteBuf.readUnsignedByte() + 1), z);
    }

    private void readRstStreamFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        http2FrameListener.onRstStreamRead(channelHandlerContext, this.streamId, byteBuf.readUnsignedInt());
    }

    private void readSettingsFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        if (this.flags.ack()) {
            http2FrameListener.onSettingsAckRead(channelHandlerContext);
            return;
        }
        int i = this.payloadLength / 6;
        Http2Settings http2Settings = new Http2Settings();
        for (int i2 = 0; i2 < i; i2++) {
            char readUnsignedShort = (char) byteBuf.readUnsignedShort();
            try {
                http2Settings.put(readUnsignedShort, Long.valueOf(byteBuf.readUnsignedInt()));
            } catch (IllegalArgumentException e) {
                if (readUnsignedShort == 4) {
                    throw Http2Exception.connectionError(Http2Error.FLOW_CONTROL_ERROR, e, e.getMessage(), new Object[0]);
                }
                if (readUnsignedShort == 5) {
                    throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, e, e.getMessage(), new Object[0]);
                }
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, e, e.getMessage(), new Object[0]);
            }
        }
        http2FrameListener.onSettingsRead(channelHandlerContext, http2Settings);
    }

    private void readPushPromiseFrame(final ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        final int i = this.streamId;
        final int readPadding = readPadding(byteBuf);
        verifyPadding(readPadding);
        final int readUnsignedInt = Http2CodecUtil.readUnsignedInt(byteBuf);
        this.headersContinuation = new HeadersContinuation() { // from class: io.netty.handler.codec.http2.DefaultHttp2FrameReader.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
            public int getStreamId() {
                return i;
            }

            @Override // io.netty.handler.codec.http2.DefaultHttp2FrameReader.HeadersContinuation
            public void processFragment(boolean z, ByteBuf byteBuf2, Http2FrameListener http2FrameListener2) throws Http2Exception {
                headersBlockBuilder().addFragment(byteBuf2, channelHandlerContext.alloc(), z);
                if (z) {
                    http2FrameListener2.onPushPromiseRead(channelHandlerContext, i, readUnsignedInt, headersBlockBuilder().headers(), readPadding);
                }
            }
        };
        this.headersContinuation.processFragment(this.flags.endOfHeaders(), byteBuf.readSlice(lengthWithoutTrailingPadding(byteBuf.readableBytes(), readPadding)), http2FrameListener);
        resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
    }

    private void readPingFrame(ChannelHandlerContext channelHandlerContext, long j, Http2FrameListener http2FrameListener) throws Http2Exception {
        if (this.flags.ack()) {
            http2FrameListener.onPingAckRead(channelHandlerContext, j);
        } else {
            http2FrameListener.onPingRead(channelHandlerContext, j);
        }
    }

    private static void readGoAwayFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        http2FrameListener.onGoAwayRead(channelHandlerContext, Http2CodecUtil.readUnsignedInt(byteBuf), byteBuf.readUnsignedInt(), byteBuf.readSlice(byteBuf.readableBytes()));
    }

    private void readWindowUpdateFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        int readUnsignedInt = Http2CodecUtil.readUnsignedInt(byteBuf);
        if (readUnsignedInt == 0) {
            throw Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, "Received WINDOW_UPDATE with delta 0 for stream: %d", Integer.valueOf(this.streamId));
        }
        http2FrameListener.onWindowUpdateRead(channelHandlerContext, this.streamId, readUnsignedInt);
    }

    private void readContinuationFrame(ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        this.headersContinuation.processFragment(this.flags.endOfHeaders(), byteBuf.readSlice(byteBuf.readableBytes()), http2FrameListener);
        resetHeadersContinuationIfEnd(this.flags.endOfHeaders());
    }

    private void readUnknownFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception {
        http2FrameListener.onUnknownFrame(channelHandlerContext, this.frameType, this.streamId, this.flags, byteBuf.readSlice(byteBuf.readableBytes()));
    }

    private int readPadding(ByteBuf byteBuf) {
        if (this.flags.paddingPresent()) {
            return byteBuf.readUnsignedByte() + 1;
        }
        return 0;
    }

    private void verifyPadding(int i) throws Http2Exception {
        if (lengthWithoutTrailingPadding(this.payloadLength, i) < 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Frame payload too small for padding.", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public abstract class HeadersContinuation {
        private final HeadersBlockBuilder builder;

        abstract int getStreamId();

        abstract void processFragment(boolean z, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception;

        private HeadersContinuation() {
            this.builder = new HeadersBlockBuilder();
        }

        final HeadersBlockBuilder headersBlockBuilder() {
            return this.builder;
        }

        final void close() {
            this.builder.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public class HeadersBlockBuilder {
        private ByteBuf headerBlock;

        protected HeadersBlockBuilder() {
        }

        private void headerSizeExceeded() throws Http2Exception {
            close();
            Http2CodecUtil.headerListSizeExceeded(DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway());
        }

        final void addFragment(ByteBuf byteBuf, ByteBufAllocator byteBufAllocator, boolean z) throws Http2Exception {
            if (this.headerBlock == null) {
                if (byteBuf.readableBytes() > DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway()) {
                    headerSizeExceeded();
                }
                if (z) {
                    this.headerBlock = byteBuf.retain();
                    return;
                }
                ByteBuf buffer = byteBufAllocator.buffer(byteBuf.readableBytes());
                this.headerBlock = buffer;
                buffer.writeBytes(byteBuf);
                return;
            }
            if (DefaultHttp2FrameReader.this.headersDecoder.configuration().maxHeaderListSizeGoAway() - byteBuf.readableBytes() < this.headerBlock.readableBytes()) {
                headerSizeExceeded();
            }
            if (this.headerBlock.isWritable(byteBuf.readableBytes())) {
                this.headerBlock.writeBytes(byteBuf);
                return;
            }
            ByteBuf buffer2 = byteBufAllocator.buffer(this.headerBlock.readableBytes() + byteBuf.readableBytes());
            buffer2.writeBytes(this.headerBlock);
            buffer2.writeBytes(byteBuf);
            this.headerBlock.release();
            this.headerBlock = buffer2;
        }

        Http2Headers headers() throws Http2Exception {
            try {
                return DefaultHttp2FrameReader.this.headersDecoder.decodeHeaders(DefaultHttp2FrameReader.this.streamId, this.headerBlock);
            } finally {
                close();
            }
        }

        void close() {
            ByteBuf byteBuf = this.headerBlock;
            if (byteBuf != null) {
                byteBuf.release();
                this.headerBlock = null;
            }
            DefaultHttp2FrameReader.this.headersContinuation = null;
        }
    }

    private void verifyNotProcessingHeaders() throws Http2Exception {
        if (this.headersContinuation != null) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received frame of type %s while processing headers on stream %d.", Byte.valueOf(this.frameType), Integer.valueOf(this.headersContinuation.getStreamId()));
        }
    }

    private void verifyPayloadLength(int i) throws Http2Exception {
        if (i > this.maxFrameSize) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Total payload length %d exceeds max frame length.", Integer.valueOf(i));
        }
    }

    private void verifyAssociatedWithAStream() throws Http2Exception {
        if (this.streamId == 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Frame of type %s must be associated with a stream.", Byte.valueOf(this.frameType));
        }
    }

    private static void verifyStreamOrConnectionId(int i, String str) throws Http2Exception {
        if (i < 0) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "%s must be >= 0", str);
        }
    }
}
