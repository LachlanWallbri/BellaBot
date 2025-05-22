package io.netty.util.internal.logging;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class InternalLoggerFactory {
    private static volatile InternalLoggerFactory defaultFactory;

    protected abstract InternalLogger newInstance(String str);

    private static InternalLoggerFactory newDefaultFactory(String str) {
        try {
            try {
                Slf4JLoggerFactory slf4JLoggerFactory = new Slf4JLoggerFactory(true);
                slf4JLoggerFactory.newInstance(str).debug("Using SLF4J as the default logging framework");
                return slf4JLoggerFactory;
            } catch (Throwable unused) {
                InternalLoggerFactory internalLoggerFactory = JdkLoggerFactory.INSTANCE;
                internalLoggerFactory.newInstance(str).debug("Using java.util.logging as the default logging framework");
                return internalLoggerFactory;
            }
        } catch (Throwable unused2) {
            InternalLoggerFactory internalLoggerFactory2 = Log4JLoggerFactory.INSTANCE;
            internalLoggerFactory2.newInstance(str).debug("Using Log4J as the default logging framework");
            return internalLoggerFactory2;
        }
    }

    public static InternalLoggerFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = newDefaultFactory(InternalLoggerFactory.class.getName());
        }
        return defaultFactory;
    }

    public static void setDefaultFactory(InternalLoggerFactory internalLoggerFactory) {
        if (internalLoggerFactory == null) {
            throw new NullPointerException("defaultFactory");
        }
        defaultFactory = internalLoggerFactory;
    }

    public static InternalLogger getInstance(Class<?> cls) {
        return getInstance(cls.getName());
    }

    public static InternalLogger getInstance(String str) {
        return getDefaultFactory().newInstance(str);
    }
}
