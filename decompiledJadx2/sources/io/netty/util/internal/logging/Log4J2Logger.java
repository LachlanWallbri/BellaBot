package io.netty.util.internal.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class Log4J2Logger extends ExtendedLoggerWrapper implements InternalLogger {
    private static final String EXCEPTION_MESSAGE = "Unexpected exception:";
    private static final long serialVersionUID = 5485418394879791397L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Log4J2Logger(Logger logger) {
        super((ExtendedLogger) logger, logger.getName(), logger.getMessageFactory());
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public String name() {
        return getName();
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(Throwable th) {
        log(Level.TRACE, EXCEPTION_MESSAGE, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(Throwable th) {
        log(Level.DEBUG, EXCEPTION_MESSAGE, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(Throwable th) {
        log(Level.INFO, EXCEPTION_MESSAGE, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(Throwable th) {
        log(Level.WARN, EXCEPTION_MESSAGE, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(Throwable th) {
        log(Level.ERROR, EXCEPTION_MESSAGE, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        return isEnabled(toLevel(internalLogLevel));
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str) {
        log(toLevel(internalLogLevel), str);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj) {
        log(toLevel(internalLogLevel), str, obj);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj, Object obj2) {
        log(toLevel(internalLogLevel), str, obj, obj2);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object... objArr) {
        log(toLevel(internalLogLevel), str, objArr);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        log(toLevel(internalLogLevel), str, th);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, Throwable th) {
        log(toLevel(internalLogLevel), EXCEPTION_MESSAGE, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* renamed from: io.netty.util.internal.logging.Log4J2Logger$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C75271 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$internal$logging$InternalLogLevel;

        static {
            int[] iArr = new int[InternalLogLevel.values().length];
            $SwitchMap$io$netty$util$internal$logging$InternalLogLevel = iArr;
            try {
                iArr[InternalLogLevel.INFO.ordinal()] = 1;
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

    protected Level toLevel(InternalLogLevel internalLogLevel) {
        int i = C75271.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
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

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.util.internal.logging.Log4J2Logger$2 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C75282 {
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
}
