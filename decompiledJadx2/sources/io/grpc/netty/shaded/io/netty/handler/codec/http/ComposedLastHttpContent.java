package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ComposedLastHttpContent implements LastHttpContent {
    private DecoderResult result;
    private final HttpHeaders trailingHeaders;

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent retain() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent retain(int i) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent touch() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent touch(Object obj) {
        return this;
    }

    ComposedLastHttpContent(HttpHeaders httpHeaders) {
        this.trailingHeaders = httpHeaders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComposedLastHttpContent(HttpHeaders httpHeaders, DecoderResult decoderResult) {
        this(httpHeaders);
        this.result = decoderResult;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent
    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent copy() {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        defaultLastHttpContent.trailingHeaders().set(trailingHeaders());
        return defaultLastHttpContent;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent duplicate() {
        return copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent retainedDuplicate() {
        return copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent replace(ByteBuf byteBuf) {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(byteBuf);
        defaultLastHttpContent.trailingHeaders().setAll(trailingHeaders());
        return defaultLastHttpContent;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return Unpooled.EMPTY_BUFFER;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.result;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject
    public DecoderResult getDecoderResult() {
        return decoderResult();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult decoderResult) {
        this.result = decoderResult;
    }
}
