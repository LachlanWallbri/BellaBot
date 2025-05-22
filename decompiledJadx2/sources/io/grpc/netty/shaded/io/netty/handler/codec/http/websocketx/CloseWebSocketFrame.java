package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CloseWebSocketFrame extends WebSocketFrame {
    public CloseWebSocketFrame() {
        super(Unpooled.buffer(0));
    }

    public CloseWebSocketFrame(WebSocketCloseStatus webSocketCloseStatus) {
        this(webSocketCloseStatus.code(), webSocketCloseStatus.reasonText());
    }

    public CloseWebSocketFrame(WebSocketCloseStatus webSocketCloseStatus, String str) {
        this(webSocketCloseStatus.code(), str);
    }

    public CloseWebSocketFrame(int i, String str) {
        this(true, 0, i, str);
    }

    public CloseWebSocketFrame(boolean z, int i) {
        this(z, i, Unpooled.buffer(0));
    }

    public CloseWebSocketFrame(boolean z, int i, int i2, String str) {
        super(z, i, newBinaryData(i2, str));
    }

    private static ByteBuf newBinaryData(int i, String str) {
        if (str == null) {
            str = "";
        }
        ByteBuf buffer = Unpooled.buffer(str.length() + 2);
        buffer.writeShort(i);
        if (!str.isEmpty()) {
            buffer.writeCharSequence(str, CharsetUtil.UTF_8);
        }
        buffer.readerIndex(0);
        return buffer;
    }

    public CloseWebSocketFrame(boolean z, int i, ByteBuf byteBuf) {
        super(z, i, byteBuf);
    }

    public int statusCode() {
        ByteBuf content = content();
        if (content == null || content.capacity() == 0) {
            return -1;
        }
        content.readerIndex(0);
        return content.getShort(0);
    }

    public String reasonText() {
        ByteBuf content = content();
        if (content == null || content.capacity() <= 2) {
            return "";
        }
        content.readerIndex(2);
        String byteBuf = content.toString(CharsetUtil.UTF_8);
        content.readerIndex(0);
        return byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public CloseWebSocketFrame copy() {
        return (CloseWebSocketFrame) super.copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public CloseWebSocketFrame duplicate() {
        return (CloseWebSocketFrame) super.duplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public CloseWebSocketFrame retainedDuplicate() {
        return (CloseWebSocketFrame) super.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public CloseWebSocketFrame replace(ByteBuf byteBuf) {
        return new CloseWebSocketFrame(isFinalFragment(), rsv(), byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CloseWebSocketFrame retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CloseWebSocketFrame retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CloseWebSocketFrame touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CloseWebSocketFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
