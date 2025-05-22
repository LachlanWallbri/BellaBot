package org.jboss.netty.logging;

import org.jboss.netty.util.internal.StackTraceSimplifier;

/* loaded from: classes7.dex */
public abstract class InternalLoggerFactory {
    private static volatile InternalLoggerFactory defaultFactory = new JdkLoggerFactory();

    public abstract InternalLogger newInstance(String str);

    static {
        StackTraceSimplifier.simplify(new Exception());
    }

    public static InternalLoggerFactory getDefaultFactory() {
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
        final InternalLogger newInstance = getDefaultFactory().newInstance(str);
        return new InternalLogger() { // from class: org.jboss.netty.logging.InternalLoggerFactory.1
            @Override // org.jboss.netty.logging.InternalLogger
            public void debug(String str2) {
                InternalLogger.this.debug(str2);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void debug(String str2, Throwable th) {
                StackTraceSimplifier.simplify(th);
                InternalLogger.this.debug(str2, th);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void error(String str2) {
                InternalLogger.this.error(str2);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void error(String str2, Throwable th) {
                StackTraceSimplifier.simplify(th);
                InternalLogger.this.error(str2, th);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void info(String str2) {
                InternalLogger.this.info(str2);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void info(String str2, Throwable th) {
                StackTraceSimplifier.simplify(th);
                InternalLogger.this.info(str2, th);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public boolean isDebugEnabled() {
                return InternalLogger.this.isDebugEnabled();
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public boolean isErrorEnabled() {
                return InternalLogger.this.isErrorEnabled();
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public boolean isInfoEnabled() {
                return InternalLogger.this.isInfoEnabled();
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public boolean isWarnEnabled() {
                return InternalLogger.this.isWarnEnabled();
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void warn(String str2) {
                InternalLogger.this.warn(str2);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void warn(String str2, Throwable th) {
                StackTraceSimplifier.simplify(th);
                InternalLogger.this.warn(str2, th);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public boolean isEnabled(InternalLogLevel internalLogLevel) {
                return InternalLogger.this.isEnabled(internalLogLevel);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void log(InternalLogLevel internalLogLevel, String str2) {
                InternalLogger.this.log(internalLogLevel, str2);
            }

            @Override // org.jboss.netty.logging.InternalLogger
            public void log(InternalLogLevel internalLogLevel, String str2, Throwable th) {
                StackTraceSimplifier.simplify(th);
                InternalLogger.this.log(internalLogLevel, str2, th);
            }
        };
    }
}
