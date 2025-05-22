package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ChannelException extends RuntimeException {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 2908618315971075004L;

    public ChannelException() {
    }

    public ChannelException(String str, Throwable th) {
        super(str, th);
    }

    public ChannelException(String str) {
        super(str);
    }

    public ChannelException(Throwable th) {
        super(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ChannelException(String str, Throwable th, boolean z) {
        super(str, th, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChannelException newStatic(String str, Throwable th) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new ChannelException(str, th, true);
        }
        return new ChannelException(str, th);
    }
}
