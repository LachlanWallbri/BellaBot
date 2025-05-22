package io.netty.handler.codec.compression;

import io.netty.handler.codec.EncoderException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
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
