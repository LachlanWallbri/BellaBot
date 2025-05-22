package io.netty.handler.codec;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class PrematureChannelClosureException extends CodecException {
    private static final long serialVersionUID = 4907642202594703094L;

    public PrematureChannelClosureException() {
    }

    public PrematureChannelClosureException(String str, Throwable th) {
        super(str, th);
    }

    public PrematureChannelClosureException(String str) {
        super(str);
    }

    public PrematureChannelClosureException(Throwable th) {
        super(th);
    }
}
