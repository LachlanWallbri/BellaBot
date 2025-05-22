package io.grpc.netty.shaded.io.netty.util.internal.logging;

import org.apache.logging.log4j.LogManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Log4J2LoggerFactory extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new Log4J2LoggerFactory();

    @Deprecated
    public Log4J2LoggerFactory() {
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new Log4J2Logger(LogManager.getLogger(str));
    }
}
