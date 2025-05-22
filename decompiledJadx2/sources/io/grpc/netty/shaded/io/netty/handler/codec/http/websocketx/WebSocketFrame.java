package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class WebSocketFrame extends DefaultByteBufHolder {
    private final boolean finalFragment;
    private final int rsv;

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public abstract WebSocketFrame replace(ByteBuf byteBuf);

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketFrame(ByteBuf byteBuf) {
        this(true, 0, byteBuf);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketFrame(boolean z, int i, ByteBuf byteBuf) {
        super(byteBuf);
        this.finalFragment = z;
        this.rsv = i;
    }

    public boolean isFinalFragment() {
        return this.finalFragment;
    }

    public int rsv() {
        return this.rsv;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public WebSocketFrame copy() {
        return (WebSocketFrame) super.copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public WebSocketFrame duplicate() {
        return (WebSocketFrame) super.duplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public WebSocketFrame retainedDuplicate() {
        return (WebSocketFrame) super.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public String toString() {
        return StringUtil.simpleClassName(this) + "(data: " + contentToString() + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public WebSocketFrame retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public WebSocketFrame retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public WebSocketFrame touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public WebSocketFrame touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
