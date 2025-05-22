package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.EmptyHttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.HttpConversionUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpToHttp2ConnectionHandler extends Http2ConnectionHandler {
    private int currentStreamId;
    private final boolean validateHeaders;

    protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        this.validateHeaders = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpToHttp2ConnectionHandler(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z, boolean z2) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z2);
        this.validateHeaders = z;
    }

    private int getStreamId(HttpHeaders httpHeaders) throws Exception {
        return httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), connection().local().incrementAndGetNextStreamId());
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00cd, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b8, code lost:
    
        if (r12 != false) goto L45;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v15, types: [io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders] */
    /* JADX WARN: Type inference failed for: r4v7, types: [io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContext2;
        boolean z;
        EmptyHttpHeaders emptyHttpHeaders;
        EmptyHttp2Headers emptyHttp2Headers;
        boolean z2;
        if (!(obj instanceof HttpMessage) && !(obj instanceof HttpContent)) {
            channelHandlerContext.write(obj, channelPromise);
            return;
        }
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        boolean z3 = false;
        boolean z4 = true;
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
            if (!z && (obj instanceof HttpContent)) {
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
                encoder.writeData(channelHandlerContext, this.currentStreamId, ((HttpContent) obj).content(), 0, z2 && emptyHttpHeaders.isEmpty(), simpleChannelPromiseAggregator.newPromise());
                try {
                    if (!emptyHttpHeaders.isEmpty()) {
                        writeHeaders(channelHandlerContext, encoder, this.currentStreamId, emptyHttpHeaders, emptyHttp2Headers, true, simpleChannelPromiseAggregator);
                    }
                    z4 = false;
                } catch (Throwable th) {
                    th = th;
                    channelHandlerContext2 = channelHandlerContext;
                    try {
                        onError(channelHandlerContext2, true, th);
                        simpleChannelPromiseAggregator.setFailure(th);
                    } finally {
                        if (z3) {
                            ReferenceCountUtil.release(obj);
                        }
                        simpleChannelPromiseAggregator.doneAllocatingPromises();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            channelHandlerContext2 = channelHandlerContext;
            z3 = true;
        }
    }

    private static void writeHeaders(ChannelHandlerContext channelHandlerContext, Http2ConnectionEncoder http2ConnectionEncoder, int i, HttpHeaders httpHeaders, Http2Headers http2Headers, boolean z, Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator) {
        http2ConnectionEncoder.writeHeaders(channelHandlerContext, i, http2Headers, httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_DEPENDENCY_ID.text(), 0), httpHeaders.getShort(HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT.text(), (short) 16), false, 0, z, simpleChannelPromiseAggregator.newPromise());
    }
}
