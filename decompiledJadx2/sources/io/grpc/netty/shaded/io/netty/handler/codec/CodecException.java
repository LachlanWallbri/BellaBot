package io.grpc.netty.shaded.io.netty.handler.codec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CodecException extends RuntimeException {
    private static final long serialVersionUID = -1464830400709348473L;

    public CodecException() {
    }

    public CodecException(String str, Throwable th) {
        super(str, th);
    }

    public CodecException(String str) {
        super(str);
    }

    public CodecException(Throwable th) {
        super(th);
    }
}
