package io.netty.handler.codec;

import io.netty.util.Signal;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DecoderResult {
    private final Throwable cause;
    protected static final Signal SIGNAL_UNFINISHED = Signal.valueOf(DecoderResult.class, "UNFINISHED");
    protected static final Signal SIGNAL_SUCCESS = Signal.valueOf(DecoderResult.class, "SUCCESS");
    public static final DecoderResult UNFINISHED = new DecoderResult(SIGNAL_UNFINISHED);
    public static final DecoderResult SUCCESS = new DecoderResult(SIGNAL_SUCCESS);

    public static DecoderResult failure(Throwable th) {
        if (th == null) {
            throw new NullPointerException("cause");
        }
        return new DecoderResult(th);
    }

    protected DecoderResult(Throwable th) {
        if (th == null) {
            throw new NullPointerException("cause");
        }
        this.cause = th;
    }

    public boolean isFinished() {
        return this.cause != SIGNAL_UNFINISHED;
    }

    public boolean isSuccess() {
        return this.cause == SIGNAL_SUCCESS;
    }

    public boolean isFailure() {
        Throwable th = this.cause;
        return (th == SIGNAL_SUCCESS || th == SIGNAL_UNFINISHED) ? false : true;
    }

    public Throwable cause() {
        if (isFailure()) {
            return this.cause;
        }
        return null;
    }

    public String toString() {
        if (!isFinished()) {
            return "unfinished";
        }
        if (isSuccess()) {
            return "success";
        }
        String th = cause().toString();
        StringBuilder sb = new StringBuilder(th.length() + 17);
        sb.append("failure(");
        sb.append(th);
        sb.append(')');
        return sb.toString();
    }
}
