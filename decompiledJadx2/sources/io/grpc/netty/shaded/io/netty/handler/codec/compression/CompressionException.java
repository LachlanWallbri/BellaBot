package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.EncoderException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CompressionException extends EncoderException {
    private static final long serialVersionUID = 5603413481274811897L;

    public CompressionException() {
    }

    public CompressionException(String str, Throwable th) {
        super(str, th);
    }

    public CompressionException(String str) {
        super(str);
    }

    public CompressionException(Throwable th) {
        super(th);
    }
}
