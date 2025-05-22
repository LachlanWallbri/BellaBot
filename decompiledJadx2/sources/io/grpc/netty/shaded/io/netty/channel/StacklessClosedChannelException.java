package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import java.nio.channels.ClosedChannelException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class StacklessClosedChannelException extends ClosedChannelException {
    private static final long serialVersionUID = -2214806025529435136L;

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }

    private StacklessClosedChannelException() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StacklessClosedChannelException newInstance(Class<?> cls, String str) {
        return (StacklessClosedChannelException) ThrowableUtil.unknownStackTrace(new StacklessClosedChannelException(), cls, str);
    }
}
