package io.netty.handler.codec.memcache;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultMemcacheContent extends AbstractMemcacheObject implements MemcacheContent {
    private final ByteBuf content;

    public DefaultMemcacheContent(ByteBuf byteBuf) {
        if (byteBuf == null) {
            throw new NullPointerException("Content cannot be null.");
        }
        this.content = byteBuf;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public ByteBuf content() {
        return this.content;
    }

    @Override // io.netty.buffer.ByteBufHolder
    public MemcacheContent copy() {
        return replace(this.content.copy());
    }

    @Override // io.netty.buffer.ByteBufHolder
    public MemcacheContent duplicate() {
        return replace(this.content.duplicate());
    }

    @Override // io.netty.buffer.ByteBufHolder
    public MemcacheContent retainedDuplicate() {
        return replace(this.content.retainedDuplicate());
    }

    @Override // io.netty.buffer.ByteBufHolder
    public MemcacheContent replace(ByteBuf byteBuf) {
        return new DefaultMemcacheContent(byteBuf);
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public MemcacheContent retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public MemcacheContent retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public MemcacheContent touch() {
        super.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public MemcacheContent touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        this.content.release();
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(data: " + content() + ", decoderResult: " + decoderResult() + ')';
    }
}
