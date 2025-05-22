package io.netty.handler.codec.compression;

import io.netty.handler.codec.DecoderException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DecompressionException extends DecoderException {
    private static final long serialVersionUID = 3546272712208105199L;

    public DecompressionException() {
    }

    public DecompressionException(String str, Throwable th) {
        super(str, th);
    }

    public DecompressionException(String str) {
        super(str);
    }

    public DecompressionException(Throwable th) {
        super(th);
    }
}
