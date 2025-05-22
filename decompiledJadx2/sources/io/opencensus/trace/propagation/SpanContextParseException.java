package io.opencensus.trace.propagation;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class SpanContextParseException extends Exception {
    private static final long serialVersionUID = 0;

    public SpanContextParseException(String str) {
        super(str);
    }

    public SpanContextParseException(String str, Throwable th) {
        super(str, th);
    }
}
