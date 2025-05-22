package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.HttpConversionUtil;
import io.netty.util.ReferenceCountUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class HttpToHttp2ConnectionHandler extends Http2ConnectionHandler {
    private int currentStreamId;
    private final boolean validateHeaders;

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        this.validateHeaders = z;
    }

    private int getStreamId(HttpHeaders httpHeaders) throws Exception {
        return httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), connection().local().incrementAndGetNextStreamId());
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b8, code lost:
    
        if (r11 != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cd, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a6 A[Catch: all -> 0x00b3, TRY_LEAVE, TryCatch #0 {all -> 0x00b3, blocks: (B:30:0x0093, B:32:0x00a6), top: B:29:0x0093 }] */
    /* JADX WARN: Type inference failed for: r3v15, types: [io.netty.handler.codec.http.HttpHeaders] */
    /* JADX WARN: Type inference failed for: r4v7, types: [io.netty.handler.codec.http2.Http2Headers] */
    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelOutboundHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContext2;
        boolean z;
        EmptyHttpHeaders emptyHttpHeaders;
        EmptyHttp2Headers emptyHttp2Headers;
        boolean z2;
        boolean z3;
        if (!(obj instanceof HttpMessage) && !(obj instanceof HttpContent)) {
            channelHandlerContext.write(obj, channelPromise);
            return;
        }
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        boolean z4 = false;
        try {
            Http2ConnectionEncoder encoder = encoder();
            if (obj instanceof HttpMessage) {
                HttpMessage httpMessage = (HttpMessage) obj;
                this.currentStreamId = getStreamId(httpMessage.headers());
                Http2Headers http2Headers = HttpConversionUtil.toHttp2Headers(httpMessage, this.validateHeaders);
                z = (obj instanceof FullHttpMessage) && !((FullHttpMessage) obj).content().isReadable();
                writeHeaders(channelHandlerContext, encoder, this.currentStreamId, httpMessage.headers(), http2Headers, z, simpleChannelPromiseAggregator);
            } else {
                z = false;
            }
            if (z || !(obj instanceof HttpContent)) {
                z4 = true;
            } else {
                EmptyHttpHeaders emptyHttpHeaders2 = EmptyHttpHeaders.INSTANCE;
                EmptyHttp2Headers emptyHttp2Headers2 = EmptyHttp2Headers.INSTANCE;
                if (obj instanceof LastHttpContent) {
                    ?? trailingHeaders = ((LastHttpContent) obj).trailingHeaders();
                    emptyHttpHeaders = trailingHeaders;
                    emptyHttp2Headers = HttpConversionUtil.toHttp2Headers((HttpHeaders) trailingHeaders, this.validateHeaders);
                    z2 = true;
                } else {
                    emptyHttpHeaders = emptyHttpHeaders2;
                    emptyHttp2Headers = emptyHttp2Headers2;
                    z2 = false;
                }
                ByteBuf content = ((HttpContent) obj).content();
                try {
                    if (z2) {
                        if (emptyHttpHeaders.isEmpty()) {
                            z3 = true;
                            encoder.writeData(channelHandlerContext, this.currentStreamId, content, 0, z3, simpleChannelPromiseAggregator.newPromise());
                            if (!emptyHttpHeaders.isEmpty()) {
                                writeHeaders(channelHandlerContext, encoder, this.currentStreamId, emptyHttpHeaders, emptyHttp2Headers, true, simpleChannelPromiseAggregator);
                            }
                        }
                    }
                    encoder.writeData(channelHandlerContext, this.currentStreamId, content, 0, z3, simpleChannelPromiseAggregator.newPromise());
                    if (!emptyHttpHeaders.isEmpty()) {
                    }
                } catch (Throwable th) {
                    th = th;
                    channelHandlerContext2 = channelHandlerContext;
                    try {
                        onError(channelHandlerContext2, true, th);
                        simpleChannelPromiseAggregator.setFailure(th);
                    } finally {
                        if (z4) {
                            ReferenceCountUtil.release(obj);
                        }
                        simpleChannelPromiseAggregator.doneAllocatingPromises();
                    }
                }
                z3 = false;
            }
        } catch (Throwable th2) {
            th = th2;
            channelHandlerContext2 = channelHandlerContext;
            z4 = true;
        }
    }

    private static void writeHeaders(ChannelHandlerContext channelHandlerContext, Http2ConnectionEncoder http2ConnectionEncoder, int i, HttpHeaders httpHeaders, Http2Headers http2Headers, boolean z, Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator) {
        http2ConnectionEncoder.writeHeaders(channelHandlerContext, i, http2Headers, httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_DEPENDENCY_ID.text(), 0), httpHeaders.getShort(HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT.text(), (short) 16), false, 0, z, simpleChannelPromiseAggregator.newPromise());
    }
}
