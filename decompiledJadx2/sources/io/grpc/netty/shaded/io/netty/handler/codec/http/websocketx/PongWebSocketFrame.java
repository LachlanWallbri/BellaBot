package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class PongWebSocketFrame extends WebSocketFrame {
    public PongWebSocketFrame() {
        super(Unpooled.buffer(0));
    }

    public PongWebSocketFrame(ByteBuf byteBuf) {
        super(byteBuf);
    }

    public PongWebSocketFrame(boolean z, int i, ByteBuf byteBuf) {
        super(z, i, byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PongWebSocketFrame copy() {
        return (PongWebSocketFrame) super.copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PongWebSocketFrame duplicate() {
        return (PongWebSocketFrame) super.duplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PongWebSocketFrame retainedDuplicate() {
        return (PongWebSocketFrame) super.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PongWebSocketFrame replace(ByteBuf byteBuf) {
        return new PongWebSocketFrame(isFinalFragment(), rsv(), byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PongWebSocketFrame retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PongWebSocketFrame retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PongWebSocketFrame touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame, io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PongWebSocketFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
