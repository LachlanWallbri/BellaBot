package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.iflytek.aiui.AIUIConstant;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.IllegalReferenceCountException;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.security.PrivateKey;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PemPrivateKey extends AbstractReferenceCounted implements PrivateKey, PemEncoded {
    private static final byte[] BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
    private static final byte[] END_PRIVATE_KEY = "\n-----END PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
    private static final String PKCS8_FORMAT = "PKCS#8";
    private static final long serialVersionUID = 7978017465645018936L;
    private final ByteBuf content;

    @Override // java.security.Key
    public String getFormat() {
        return PKCS8_FORMAT;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded
    public boolean isSensitive() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PemEncoded toPEM(ByteBufAllocator byteBufAllocator, boolean z, PrivateKey privateKey) {
        if (privateKey instanceof PemEncoded) {
            return ((PemEncoded) privateKey).retain();
        }
        byte[] encoded = privateKey.getEncoded();
        if (encoded == null) {
            throw new IllegalArgumentException(privateKey.getClass().getName() + " does not support encoding");
        }
        return toPEM(byteBufAllocator, z, encoded);
    }

    static PemEncoded toPEM(ByteBufAllocator byteBufAllocator, boolean z, byte[] bArr) {
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bArr);
        try {
            ByteBuf base64 = SslUtils.toBase64(byteBufAllocator, wrappedBuffer);
            try {
                int length = BEGIN_PRIVATE_KEY.length + base64.readableBytes() + END_PRIVATE_KEY.length;
                ByteBuf directBuffer = z ? byteBufAllocator.directBuffer(length) : byteBufAllocator.buffer(length);
                try {
                    directBuffer.writeBytes(BEGIN_PRIVATE_KEY);
                    directBuffer.writeBytes(base64);
                    directBuffer.writeBytes(END_PRIVATE_KEY);
                    return new PemValue(directBuffer, true);
                } finally {
                }
            } finally {
                SslUtils.zerooutAndRelease(base64);
            }
        } finally {
            SslUtils.zerooutAndRelease(wrappedBuffer);
        }
    }

    public static PemPrivateKey valueOf(byte[] bArr) {
        return valueOf(Unpooled.wrappedBuffer(bArr));
    }

    public static PemPrivateKey valueOf(ByteBuf byteBuf) {
        return new PemPrivateKey(byteBuf);
    }

    private PemPrivateKey(ByteBuf byteBuf) {
        this.content = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, AIUIConstant.KEY_CONTENT);
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
    public PemPrivateKey copy() {
        return replace(this.content.copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemPrivateKey duplicate() {
        return replace(this.content.duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemPrivateKey retainedDuplicate() {
        return replace(this.content.retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public PemPrivateKey replace(ByteBuf byteBuf) {
        return new PemPrivateKey(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemPrivateKey touch() {
        this.content.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemPrivateKey touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemPrivateKey retain() {
        return (PemPrivateKey) super.retain();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public PemPrivateKey retain(int i) {
        return (PemPrivateKey) super.retain(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        SslUtils.zerooutAndRelease(this.content);
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        throw new UnsupportedOperationException();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        throw new UnsupportedOperationException();
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        release(refCnt());
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return refCnt() == 0;
    }
}
