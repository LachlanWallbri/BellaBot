package io.netty.util.internal.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class JdkLogger extends AbstractInternalLogger {
    static final String SELF = JdkLogger.class.getName();
    static final String SUPER = AbstractInternalLogger.class.getName();
    private static final long serialVersionUID = -1767272577989225979L;
    final transient Logger logger;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkLogger(Logger logger) {
        super(logger.getName());
        this.logger = logger;
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isTraceEnabled() {
        return this.logger.isLoggable(Level.FINEST);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str) {
        if (this.logger.isLoggable(Level.FINEST)) {
            log(SELF, Level.FINEST, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object obj) {
        if (this.logger.isLoggable(Level.FINEST)) {
            FormattingTuple format = MessageFormatter.format(str, obj);
            log(SELF, Level.FINEST, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object obj, Object obj2) {
        if (this.logger.isLoggable(Level.FINEST)) {
            FormattingTuple format = MessageFormatter.format(str, obj, obj2);
            log(SELF, Level.FINEST, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object... objArr) {
        if (this.logger.isLoggable(Level.FINEST)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            log(SELF, Level.FINEST, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Throwable th) {
        if (this.logger.isLoggable(Level.FINEST)) {
            log(SELF, Level.FINEST, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str) {
        if (this.logger.isLoggable(Level.FINE)) {
            log(SELF, Level.FINE, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object obj) {
        if (this.logger.isLoggable(Level.FINE)) {
            FormattingTuple format = MessageFormatter.format(str, obj);
            log(SELF, Level.FINE, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object obj, Object obj2) {
        if (this.logger.isLoggable(Level.FINE)) {
            FormattingTuple format = MessageFormatter.format(str, obj, obj2);
            log(SELF, Level.FINE, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object... objArr) {
        if (this.logger.isLoggable(Level.FINE)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            log(SELF, Level.FINE, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Throwable th) {
        if (this.logger.isLoggable(Level.FINE)) {
            log(SELF, Level.FINE, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str) {
        if (this.logger.isLoggable(Level.INFO)) {
            log(SELF, Level.INFO, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object obj) {
        if (this.logger.isLoggable(Level.INFO)) {
            FormattingTuple format = MessageFormatter.format(str, obj);
            log(SELF, Level.INFO, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object obj, Object obj2) {
        if (this.logger.isLoggable(Level.INFO)) {
            FormattingTuple format = MessageFormatter.format(str, obj, obj2);
            log(SELF, Level.INFO, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object... objArr) {
        if (this.logger.isLoggable(Level.INFO)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            log(SELF, Level.INFO, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Throwable th) {
        if (this.logger.isLoggable(Level.INFO)) {
            log(SELF, Level.INFO, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str) {
        if (this.logger.isLoggable(Level.WARNING)) {
            log(SELF, Level.WARNING, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object obj) {
        if (this.logger.isLoggable(Level.WARNING)) {
            FormattingTuple format = MessageFormatter.format(str, obj);
            log(SELF, Level.WARNING, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object obj, Object obj2) {
        if (this.logger.isLoggable(Level.WARNING)) {
            FormattingTuple format = MessageFormatter.format(str, obj, obj2);
            log(SELF, Level.WARNING, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object... objArr) {
        if (this.logger.isLoggable(Level.WARNING)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            log(SELF, Level.WARNING, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Throwable th) {
        if (this.logger.isLoggable(Level.WARNING)) {
            log(SELF, Level.WARNING, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isErrorEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            log(SELF, Level.SEVERE, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object obj) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            FormattingTuple format = MessageFormatter.format(str, obj);
            log(SELF, Level.SEVERE, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object obj, Object obj2) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            FormattingTuple format = MessageFormatter.format(str, obj, obj2);
            log(SELF, Level.SEVERE, format.getMessage(), format.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object... objArr) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            log(SELF, Level.SEVERE, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Throwable th) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            log(SELF, Level.SEVERE, str, th);
        }
    }

    private void log(String str, Level level, String str2, Throwable th) {
        LogRecord logRecord = new LogRecord(level, str2);
        logRecord.setLoggerName(name());
        logRecord.setThrown(th);
        fillCallerData(str, logRecord);
        this.logger.log(logRecord);
    }

    private static void fillCallerData(String str, LogRecord logRecord) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i = 0;
        while (true) {
            if (i >= stackTrace.length) {
                i = -1;
                break;
            }
            String className = stackTrace[i].getClassName();
            if (className.equals(str) || className.equals(SUPER)) {
                break;
            } else {
                i++;
            }
        }
        while (true) {
            i++;
            if (i >= stackTrace.length) {
                i = -1;
                break;
            }
            String className2 = stackTrace[i].getClassName();
            if (!className2.equals(str) && !className2.equals(SUPER)) {
                break;
            }
        }
        if (i != -1) {
            StackTraceElement stackTraceElement = stackTrace[i];
            logRecord.setSourceClassName(stackTraceElement.getClassName());
            logRecord.setSourceMethodName(stackTraceElement.getMethodName());
        }
    }
}
