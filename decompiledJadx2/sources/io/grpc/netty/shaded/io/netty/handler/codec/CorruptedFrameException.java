package io.grpc.netty.shaded.io.netty.handler.codec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CorruptedFrameException extends DecoderException {
    private static final long serialVersionUID = 3918052232492988408L;

    public CorruptedFrameException() {
    }

    public CorruptedFrameException(String str, Throwable th) {
        super(str, th);
    }

    public CorruptedFrameException(String str) {
        super(str);
    }

    public CorruptedFrameException(Throwable th) {
        super(th);
    }
}
