package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.iflytek.aiui.AIUIConstant;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.IllegalReferenceCountException;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class PemValue extends AbstractReferenceCounted implements PemEncoded {
    private final ByteBuf content;
    private final boolean sensitive;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PemValue(ByteBuf byteBuf, boolean z) {
        this.content = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, AIUIConstant.KEY_CONTENT);
        this.sensitive = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded
    public boolean isSensitive() {
        return this.sensitive;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        int refCnt = refCnt();
        if (refCnt <= 0) {
            throw new IllegalReferenceCountException(refCnt);
        }
        return this.content;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemValue copy() {
        return replace(this.content.copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemValue duplicate() {
        return replace(this.content.duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemValue retainedDuplicate() {
        return replace(this.content.retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemValue replace(ByteBuf byteBuf) {
        return new PemValue(byteBuf, this.sensitive);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemValue touch() {
        return (PemValue) super.touch();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemValue touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemValue retain() {
        return (PemValue) super.retain();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemValue retain(int i) {
        return (PemValue) super.retain(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        if (this.sensitive) {
            SslUtils.zeroout(this.content);
        }
        this.content.release();
    }
}
