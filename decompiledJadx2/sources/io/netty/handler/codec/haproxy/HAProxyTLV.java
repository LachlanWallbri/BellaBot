package io.netty.handler.codec.haproxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class HAProxyTLV extends DefaultByteBufHolder {
    private final Type type;
    private final byte typeByteValue;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public enum Type {
        PP2_TYPE_ALPN,
        PP2_TYPE_AUTHORITY,
        PP2_TYPE_SSL,
        PP2_TYPE_SSL_VERSION,
        PP2_TYPE_SSL_CN,
        PP2_TYPE_NETNS,
        OTHER;

        public static Type typeForByteValue(byte b) {
            if (b == 1) {
                return PP2_TYPE_ALPN;
            }
            if (b == 2) {
                return PP2_TYPE_AUTHORITY;
            }
            if (b != 48) {
                switch (b) {
                    case 32:
                        return PP2_TYPE_SSL;
                    case 33:
                        return PP2_TYPE_SSL_VERSION;
                    case 34:
                        return PP2_TYPE_SSL_CN;
                    default:
                        return OTHER;
                }
            }
            return PP2_TYPE_NETNS;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HAProxyTLV(Type type, byte b, ByteBuf byteBuf) {
        super(byteBuf);
        ObjectUtil.checkNotNull(type, "type");
        this.type = type;
        this.typeByteValue = b;
    }

    public Type type() {
        return this.type;
    }

    public byte typeByteValue() {
        return this.typeByteValue;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public HAProxyTLV copy() {
        return replace(content().copy());
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public HAProxyTLV duplicate() {
        return replace(content().duplicate());
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public HAProxyTLV retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.buffer.ByteBufHolder
    public HAProxyTLV replace(ByteBuf byteBuf) {
        return new HAProxyTLV(this.type, this.typeByteValue, byteBuf);
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public HAProxyTLV retain() {
        super.retain();
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public HAProxyTLV retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public HAProxyTLV touch() {
        super.touch();
        return this;
    }

    @Override // io.netty.buffer.DefaultByteBufHolder, io.netty.util.ReferenceCounted
    public HAProxyTLV touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
