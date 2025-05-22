package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultLastHttpContent extends DefaultHttpContent implements LastHttpContent {
    private final HttpHeaders trailingHeaders;
    private final boolean validateHeaders;

    public DefaultLastHttpContent() {
        this(Unpooled.buffer(0));
    }

    public DefaultLastHttpContent(ByteBuf byteBuf) {
        this(byteBuf, true);
    }

    public DefaultLastHttpContent(ByteBuf byteBuf, boolean z) {
        super(byteBuf);
        this.trailingHeaders = new TrailingHttpHeaders(z);
        this.validateHeaders = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent copy() {
        return replace(content().copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent duplicate() {
        return replace(content().duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public LastHttpContent replace(ByteBuf byteBuf) {
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(byteBuf, this.validateHeaders);
        defaultLastHttpContent.trailingHeaders().set(trailingHeaders());
        return defaultLastHttpContent;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public LastHttpContent touch(Object obj) {
        super.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent
    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(StringUtil.NEWLINE);
        appendHeaders(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    private void appendHeaders(StringBuilder sb) {
        Iterator<Map.Entry<String, String>> it = trailingHeaders().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            sb.append(next.getKey());
            sb.append(": ");
            sb.append(next.getValue());
            sb.append(StringUtil.NEWLINE);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class TrailingHttpHeaders extends DefaultHttpHeaders {
        private static final DefaultHeaders.NameValidator<CharSequence> TrailerNameValidator = new DefaultHeaders.NameValidator<CharSequence>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultLastHttpContent.TrailingHttpHeaders.1
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.DefaultHeaders.NameValidator
            public void validateName(CharSequence charSequence) {
                DefaultHttpHeaders.HttpNameValidator.validateName(charSequence);
                if (HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(charSequence) || HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(charSequence) || HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(charSequence)) {
                    throw new IllegalArgumentException("prohibited trailing header: " + ((Object) charSequence));
                }
            }
        };

        TrailingHttpHeaders(boolean z) {
            super(z, z ? TrailerNameValidator : DefaultHeaders.NameValidator.NOT_NULL);
        }
    }
}
