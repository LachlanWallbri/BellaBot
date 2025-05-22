package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpRequestEncoder extends HttpObjectEncoder<HttpRequest> {
    private static final char QUESTION_MARK = '?';
    private static final char SLASH = '/';
    private static final int SLASH_AND_SPACE_SHORT = 12064;
    private static final int SPACE_SLASH_AND_SPACE_MEDIUM = 2109216;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectEncoder, io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return super.acceptOutboundMessage(obj) && !(obj instanceof HttpResponse);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectEncoder
    public void encodeInitialLine(ByteBuf byteBuf, HttpRequest httpRequest) throws Exception {
        ByteBufUtil.copy(httpRequest.method().asciiName(), byteBuf);
        String uri = httpRequest.uri();
        if (uri.isEmpty()) {
            ByteBufUtil.writeMediumBE(byteBuf, SPACE_SLASH_AND_SPACE_MEDIUM);
        } else {
            int indexOf = uri.indexOf("://");
            boolean z = false;
            CharSequence charSequence = uri;
            if (indexOf != -1) {
                char charAt = uri.charAt(0);
                charSequence = uri;
                if (charAt != '/') {
                    int i = indexOf + 3;
                    int indexOf2 = uri.indexOf(63, i);
                    if (indexOf2 == -1) {
                        int lastIndexOf = uri.lastIndexOf(47);
                        charSequence = uri;
                        if (lastIndexOf < i) {
                            z = true;
                            charSequence = uri;
                        }
                    } else {
                        int lastIndexOf2 = uri.lastIndexOf(47, indexOf2);
                        charSequence = uri;
                        if (lastIndexOf2 < i) {
                            charSequence = new StringBuilder(uri).insert(indexOf2, '/');
                        }
                    }
                }
            }
            byteBuf.writeByte(32).writeCharSequence(charSequence, CharsetUtil.UTF_8);
            if (z) {
                ByteBufUtil.writeShortBE(byteBuf, SLASH_AND_SPACE_SHORT);
            } else {
                byteBuf.writeByte(32);
            }
        }
        httpRequest.protocolVersion().encode(byteBuf);
        ByteBufUtil.writeShortBE(byteBuf, 3338);
    }
}
