package io.grpc.netty.shaded.io.netty.buffer;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public abstract class AbstractDerivedByteBuf extends AbstractByteBuf {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDerivedByteBuf(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean isAccessible() {
        return isAccessible0();
    }

    boolean isAccessible0() {
        return unwrap().isAccessible();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final int refCnt() {
        return refCnt0();
    }

    int refCnt0() {
        return unwrap().refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ByteBuf retain() {
        return retain0();
    }

    ByteBuf retain0() {
        unwrap().retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ByteBuf retain(int i) {
        return retain0(i);
    }

    ByteBuf retain0(int i) {
        unwrap().retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ByteBuf touch() {
        return touch0();
    }

    ByteBuf touch0() {
        unwrap().touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final ByteBuf touch(Object obj) {
        return touch0(obj);
    }

    ByteBuf touch0(Object obj) {
        unwrap().touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release() {
        return release0();
    }

    boolean release0() {
        return unwrap().release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public final boolean release(int i) {
        return release0(i);
    }

    boolean release0(int i) {
        return unwrap().release(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadOnly() {
        return unwrap().isReadOnly();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        return nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        return unwrap().nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isContiguous() {
        return unwrap().isContiguous();
    }
}
