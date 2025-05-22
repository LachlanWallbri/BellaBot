package io.grpc.netty.shaded.io.netty.util.internal.logging;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class InternalLoggerFactory {
    private static volatile InternalLoggerFactory defaultFactory;

    protected abstract InternalLogger newInstance(String str);

    private static InternalLoggerFactory newDefaultFactory(String str) {
        try {
            try {
                try {
                    Slf4JLoggerFactory slf4JLoggerFactory = new Slf4JLoggerFactory(true);
                    slf4JLoggerFactory.newInstance(str).debug("Using SLF4J as the default logging framework");
                    return slf4JLoggerFactory;
                } catch (Throwable unused) {
                    InternalLoggerFactory internalLoggerFactory = Log4JLoggerFactory.INSTANCE;
                    internalLoggerFactory.newInstance(str).debug("Using Log4J as the default logging framework");
                    return internalLoggerFactory;
                }
            } catch (Throwable unused2) {
                InternalLoggerFactory internalLoggerFactory2 = JdkLoggerFactory.INSTANCE;
                internalLoggerFactory2.newInstance(str).debug("Using java.util.logging as the default logging framework");
                return internalLoggerFactory2;
            }
        } catch (Throwable unused3) {
            InternalLoggerFactory internalLoggerFactory3 = Log4J2LoggerFactory.INSTANCE;
            internalLoggerFactory3.newInstance(str).debug("Using Log4J2 as the default logging framework");
            return internalLoggerFactory3;
        }
    }

    public static InternalLoggerFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = newDefaultFactory(InternalLoggerFactory.class.getName());
        }
        return defaultFactory;
    }

    public static void setDefaultFactory(InternalLoggerFactory internalLoggerFactory) {
        defaultFactory = (InternalLoggerFactory) ObjectUtil.checkNotNull(internalLoggerFactory, "defaultFactory");
    }

    public static InternalLogger getInstance(Class<?> cls) {
        return getInstance(cls.getName());
    }

    public static InternalLogger getInstance(String str) {
        return getDefaultFactory().newInstance(str);
    }
}
