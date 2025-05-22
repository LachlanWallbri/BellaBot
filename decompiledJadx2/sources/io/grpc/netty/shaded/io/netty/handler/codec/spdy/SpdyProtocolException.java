package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SpdyProtocolException extends Exception {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 7870000537743847264L;

    public SpdyProtocolException() {
    }

    public SpdyProtocolException(String str, Throwable th) {
        super(str, th);
    }

    public SpdyProtocolException(String str) {
        super(str);
    }

    public SpdyProtocolException(Throwable th) {
        super(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpdyProtocolException newStatic(String str) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new SpdyProtocolException(str, true);
        }
        return new SpdyProtocolException(str);
    }

    private SpdyProtocolException(String str, boolean z) {
        super(str, null, false, true);
    }
}
