package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.spdy.SpdyHeaders;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.netty.util.ReferenceCountUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SpdyHttpDecoder extends MessageToMessageDecoder<SpdyFrame> {
    private final int maxContentLength;
    private final Map<Integer, FullHttpMessage> messageMap;
    private final int spdyVersion;
    private final boolean validateHeaders;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, SpdyFrame spdyFrame, List list) throws Exception {
        decode2(channelHandlerContext, spdyFrame, (List<Object>) list);
    }

    public SpdyHttpDecoder(SpdyVersion spdyVersion, int i) {
        this(spdyVersion, i, new HashMap(), true);
    }

    public SpdyHttpDecoder(SpdyVersion spdyVersion, int i, boolean z) {
        this(spdyVersion, i, new HashMap(), z);
    }

    protected SpdyHttpDecoder(SpdyVersion spdyVersion, int i, Map<Integer, FullHttpMessage> map) {
        this(spdyVersion, i, map, true);
    }

    protected SpdyHttpDecoder(SpdyVersion spdyVersion, int i, Map<Integer, FullHttpMessage> map, boolean z) {
        if (spdyVersion == null) {
            throw new NullPointerException("version");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxContentLength must be a positive integer: " + i);
        }
        this.spdyVersion = spdyVersion.getVersion();
        this.maxContentLength = i;
        this.messageMap = map;
        this.validateHeaders = z;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        Iterator<Map.Entry<Integer, FullHttpMessage>> it = this.messageMap.entrySet().iterator();
        while (it.hasNext()) {
            ReferenceCountUtil.safeRelease(it.next().getValue());
        }
        this.messageMap.clear();
        super.channelInactive(channelHandlerContext);
    }

    protected FullHttpMessage putMessage(int i, FullHttpMessage fullHttpMessage) {
        return this.messageMap.put(Integer.valueOf(i), fullHttpMessage);
    }

    protected FullHttpMessage getMessage(int i) {
        return this.messageMap.get(Integer.valueOf(i));
    }

    protected FullHttpMessage removeMessage(int i) {
        return this.messageMap.remove(Integer.valueOf(i));
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, SpdyFrame spdyFrame, List<Object> list) throws Exception {
        if (spdyFrame instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) spdyFrame;
            int streamId = spdySynStreamFrame.streamId();
            if (SpdyCodecUtil.isServerId(streamId)) {
                int associatedStreamId = spdySynStreamFrame.associatedStreamId();
                if (associatedStreamId == 0) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.INVALID_STREAM));
                    return;
                }
                if (spdySynStreamFrame.isLast()) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.PROTOCOL_ERROR));
                    return;
                }
                if (spdySynStreamFrame.isTruncated()) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.INTERNAL_ERROR));
                    return;
                }
                try {
                    FullHttpRequest createHttpRequest = createHttpRequest(spdySynStreamFrame, channelHandlerContext.alloc());
                    createHttpRequest.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, streamId);
                    createHttpRequest.headers().setInt(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID, associatedStreamId);
                    createHttpRequest.headers().setInt(SpdyHttpHeaders.Names.PRIORITY, spdySynStreamFrame.priority());
                    list.add(createHttpRequest);
                    return;
                } catch (Throwable unused) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.PROTOCOL_ERROR));
                    return;
                }
            }
            if (spdySynStreamFrame.isTruncated()) {
                DefaultSpdySynReplyFrame defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(streamId);
                defaultSpdySynReplyFrame.setLast(true);
                SpdyHeaders headers = defaultSpdySynReplyFrame.headers();
                headers.setInt(SpdyHeaders.HttpNames.STATUS, HttpResponseStatus.REQUEST_HEADER_FIELDS_TOO_LARGE.code());
                headers.setObject((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (Object) HttpVersion.HTTP_1_0);
                channelHandlerContext.writeAndFlush(defaultSpdySynReplyFrame);
                return;
            }
            try {
                FullHttpRequest createHttpRequest2 = createHttpRequest(spdySynStreamFrame, channelHandlerContext.alloc());
                createHttpRequest2.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, streamId);
                if (spdySynStreamFrame.isLast()) {
                    list.add(createHttpRequest2);
                } else {
                    putMessage(streamId, createHttpRequest2);
                }
                return;
            } catch (Throwable unused2) {
                DefaultSpdySynReplyFrame defaultSpdySynReplyFrame2 = new DefaultSpdySynReplyFrame(streamId);
                defaultSpdySynReplyFrame2.setLast(true);
                SpdyHeaders headers2 = defaultSpdySynReplyFrame2.headers();
                headers2.setInt(SpdyHeaders.HttpNames.STATUS, HttpResponseStatus.BAD_REQUEST.code());
                headers2.setObject((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (Object) HttpVersion.HTTP_1_0);
                channelHandlerContext.writeAndFlush(defaultSpdySynReplyFrame2);
                return;
            }
        }
        if (spdyFrame instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) spdyFrame;
            int streamId2 = spdySynReplyFrame.streamId();
            if (spdySynReplyFrame.isTruncated()) {
                channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId2, SpdyStreamStatus.INTERNAL_ERROR));
                return;
            }
            try {
                FullHttpResponse createHttpResponse = createHttpResponse(spdySynReplyFrame, channelHandlerContext.alloc(), this.validateHeaders);
                createHttpResponse.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, streamId2);
                if (spdySynReplyFrame.isLast()) {
                    HttpUtil.setContentLength(createHttpResponse, 0L);
                    list.add(createHttpResponse);
                } else {
                    putMessage(streamId2, createHttpResponse);
                }
                return;
            } catch (Throwable unused3) {
                channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId2, SpdyStreamStatus.PROTOCOL_ERROR));
                return;
            }
        }
        if (spdyFrame instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) spdyFrame;
            int streamId3 = spdyHeadersFrame.streamId();
            FullHttpMessage message = getMessage(streamId3);
            if (message == null) {
                if (SpdyCodecUtil.isServerId(streamId3)) {
                    if (spdyHeadersFrame.isTruncated()) {
                        channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId3, SpdyStreamStatus.INTERNAL_ERROR));
                        return;
                    }
                    try {
                        FullHttpResponse createHttpResponse2 = createHttpResponse(spdyHeadersFrame, channelHandlerContext.alloc(), this.validateHeaders);
                        createHttpResponse2.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, streamId3);
                        if (spdyHeadersFrame.isLast()) {
                            HttpUtil.setContentLength(createHttpResponse2, 0L);
                            list.add(createHttpResponse2);
                        } else {
                            putMessage(streamId3, createHttpResponse2);
                        }
                        return;
                    } catch (Throwable unused4) {
                        channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(streamId3, SpdyStreamStatus.PROTOCOL_ERROR));
                        return;
                    }
                }
                return;
            }
            if (!spdyHeadersFrame.isTruncated()) {
                for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                    message.headers().add(entry.getKey(), entry.getValue());
                }
            }
            if (spdyHeadersFrame.isLast()) {
                HttpUtil.setContentLength(message, message.content().readableBytes());
                removeMessage(streamId3);
                list.add(message);
                return;
            }
            return;
        }
        if (spdyFrame instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) spdyFrame;
            int streamId4 = spdyDataFrame.streamId();
            FullHttpMessage message2 = getMessage(streamId4);
            if (message2 == null) {
                return;
            }
            ByteBuf content = message2.content();
            if (content.readableBytes() > this.maxContentLength - spdyDataFrame.content().readableBytes()) {
                removeMessage(streamId4);
                throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
            }
            ByteBuf content2 = spdyDataFrame.content();
            content.writeBytes(content2, content2.readerIndex(), content2.readableBytes());
            if (spdyDataFrame.isLast()) {
                HttpUtil.setContentLength(message2, content.readableBytes());
                removeMessage(streamId4);
                list.add(message2);
                return;
            }
            return;
        }
        if (spdyFrame instanceof SpdyRstStreamFrame) {
            removeMessage(((SpdyRstStreamFrame) spdyFrame).streamId());
        }
    }

    private static FullHttpRequest createHttpRequest(SpdyHeadersFrame spdyHeadersFrame, ByteBufAllocator byteBufAllocator) throws Exception {
        SpdyHeaders headers = spdyHeadersFrame.headers();
        HttpMethod valueOf = HttpMethod.valueOf(headers.getAsString(SpdyHeaders.HttpNames.METHOD));
        String asString = headers.getAsString(SpdyHeaders.HttpNames.PATH);
        HttpVersion valueOf2 = HttpVersion.valueOf(headers.getAsString(SpdyHeaders.HttpNames.VERSION));
        headers.remove(SpdyHeaders.HttpNames.METHOD);
        headers.remove(SpdyHeaders.HttpNames.PATH);
        headers.remove(SpdyHeaders.HttpNames.VERSION);
        ByteBuf buffer = byteBufAllocator.buffer();
        try {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(valueOf2, valueOf, asString, buffer);
            headers.remove(SpdyHeaders.HttpNames.SCHEME);
            CharSequence charSequence = headers.get(SpdyHeaders.HttpNames.HOST);
            headers.remove(SpdyHeaders.HttpNames.HOST);
            defaultFullHttpRequest.headers().set(HttpHeaderNames.HOST, charSequence);
            for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                defaultFullHttpRequest.headers().add(entry.getKey(), entry.getValue());
            }
            HttpUtil.setKeepAlive(defaultFullHttpRequest, true);
            defaultFullHttpRequest.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            return defaultFullHttpRequest;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    private static FullHttpResponse createHttpResponse(SpdyHeadersFrame spdyHeadersFrame, ByteBufAllocator byteBufAllocator, boolean z) throws Exception {
        SpdyHeaders headers = spdyHeadersFrame.headers();
        HttpResponseStatus parseLine = HttpResponseStatus.parseLine(headers.get(SpdyHeaders.HttpNames.STATUS));
        HttpVersion valueOf = HttpVersion.valueOf(headers.getAsString(SpdyHeaders.HttpNames.VERSION));
        headers.remove(SpdyHeaders.HttpNames.STATUS);
        headers.remove(SpdyHeaders.HttpNames.VERSION);
        ByteBuf buffer = byteBufAllocator.buffer();
        try {
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(valueOf, parseLine, buffer, z);
            for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                defaultFullHttpResponse.headers().add(entry.getKey(), entry.getValue());
            }
            HttpUtil.setKeepAlive(defaultFullHttpResponse, true);
            defaultFullHttpResponse.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            defaultFullHttpResponse.headers().remove(HttpHeaderNames.TRAILER);
            return defaultFullHttpResponse;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }
}
