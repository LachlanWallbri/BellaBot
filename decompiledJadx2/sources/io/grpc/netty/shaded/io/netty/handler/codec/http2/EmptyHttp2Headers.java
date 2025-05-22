package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.EmptyHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EmptyHttp2Headers extends EmptyHeaders<CharSequence, CharSequence, Http2Headers> implements Http2Headers {
    public static final EmptyHttp2Headers INSTANCE = new EmptyHttp2Headers();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public /* bridge */ /* synthetic */ Iterator valueIterator(CharSequence charSequence) {
        return super.valueIterator((EmptyHttp2Headers) charSequence);
    }

    private EmptyHttp2Headers() {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public EmptyHttp2Headers method(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public EmptyHttp2Headers scheme(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public EmptyHttp2Headers authority(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public EmptyHttp2Headers path(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public EmptyHttp2Headers status(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence method() {
        return get(Http2Headers.PseudoHeaderName.METHOD.value());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence scheme() {
        return get(Http2Headers.PseudoHeaderName.SCHEME.value());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence authority() {
        return get(Http2Headers.PseudoHeaderName.AUTHORITY.value());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence path() {
        return get(Http2Headers.PseudoHeaderName.PATH.value());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence status() {
        return get(Http2Headers.PseudoHeaderName.STATUS.value());
    }
}
