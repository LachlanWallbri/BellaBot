package io.grpc.netty.shaded.io.netty.handler.logging;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogLevel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum LogLevel {
    TRACE(InternalLogLevel.TRACE),
    DEBUG(InternalLogLevel.DEBUG),
    INFO(InternalLogLevel.INFO),
    WARN(InternalLogLevel.WARN),
    ERROR(InternalLogLevel.ERROR);

    private final InternalLogLevel internalLevel;

    LogLevel(InternalLogLevel internalLogLevel) {
        this.internalLevel = internalLogLevel;
    }

    public InternalLogLevel toInternalLevel() {
        return this.internalLevel;
    }
}
