package io.grpc.netty.shaded.io.netty.util.internal.logging;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class Log4J2Logger extends ExtendedLoggerWrapper implements InternalLogger {
    private static final boolean VARARGS_ONLY = ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.logging.Log4J2Logger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Boolean run() {
            try {
                Logger.class.getMethod("debug", String.class, Object.class);
                return false;
            } catch (NoSuchMethodException unused) {
                return true;
            } catch (SecurityException unused2) {
                return false;
            }
        }
    })).booleanValue();
    private static final long serialVersionUID = 5485418394879791397L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Log4J2Logger(Logger logger) {
        super((ExtendedLogger) logger, logger.getName(), logger.getMessageFactory());
        if (VARARGS_ONLY) {
            throw new UnsupportedOperationException("Log4J2 version mismatch");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public String name() {
        return getName();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void trace(Throwable th) {
        log(Level.TRACE, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void debug(Throwable th) {
        log(Level.DEBUG, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void info(Throwable th) {
        log(Level.INFO, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void warn(Throwable th) {
        log(Level.WARN, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void error(Throwable th) {
        log(Level.ERROR, "Unexpected exception:", th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        return isEnabled(toLevel(internalLogLevel));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str) {
        log(toLevel(internalLogLevel), str);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj) {
        log(toLevel(internalLogLevel), str, obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj, Object obj2) {
        log(toLevel(internalLogLevel), str, obj, obj2);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object... objArr) {
        log(toLevel(internalLogLevel), str, objArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        log(toLevel(internalLogLevel), str, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, Throwable th) {
        log(toLevel(internalLogLevel), "Unexpected exception:", th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.util.internal.logging.Log4J2Logger$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C68082 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$internal$logging$InternalLogLevel = new int[InternalLogLevel.values().length];

        static {
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.TRACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static Level toLevel(InternalLogLevel internalLogLevel) {
        int i = C68082.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            return Level.INFO;
        }
        if (i == 2) {
            return Level.DEBUG;
        }
        if (i == 3) {
            return Level.WARN;
        }
        if (i == 4) {
            return Level.ERROR;
        }
        if (i == 5) {
            return Level.TRACE;
        }
        throw new Error();
    }
}
