package com.aliyun.alink.p022h2.netty;

import com.aliyun.alink.p022h2.p025b.C0879a;
import io.netty.util.internal.logging.AbstractInternalLogger;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NettyHttp2Logger extends AbstractInternalLogger {
    static final String SELF = NettyHttp2Logger.class.getName();
    private Level logLevel;

    /* JADX INFO: Access modifiers changed from: protected */
    public NettyHttp2Logger(String str) {
        super(str);
        this.logLevel = Level.FINEST;
    }

    public Level getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(Level level) {
        this.logLevel = level;
    }

    private boolean isLoggable(Level level) {
        return this.logLevel.intValue() <= level.intValue();
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isTraceEnabled() {
        return isLoggable(Level.FINEST);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str) {
        if (isLoggable(Level.FINEST)) {
            log(SELF, Level.FINEST, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object obj) {
        if (isLoggable(Level.FINEST)) {
            C0888a m258a = C0893f.m258a(str, obj);
            log(SELF, Level.FINEST, m258a.m249a(), m258a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object obj, Object obj2) {
        if (isLoggable(Level.FINEST)) {
            C0888a m259a = C0893f.m259a(str, obj, obj2);
            log(SELF, Level.FINEST, m259a.m249a(), m259a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Object... objArr) {
        if (isLoggable(Level.FINEST)) {
            C0888a m260a = C0893f.m260a(str, objArr);
            log(SELF, Level.FINEST, m260a.m249a(), m260a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void trace(String str, Throwable th) {
        if (isLoggable(Level.FINEST)) {
            log(SELF, Level.FINEST, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isDebugEnabled() {
        return isLoggable(Level.FINE);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str) {
        if (isLoggable(Level.FINE)) {
            log(SELF, Level.FINE, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object obj) {
        if (isLoggable(Level.FINE)) {
            C0888a m258a = C0893f.m258a(str, obj);
            log(SELF, Level.FINE, m258a.m249a(), m258a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object obj, Object obj2) {
        if (isLoggable(Level.FINE)) {
            C0888a m259a = C0893f.m259a(str, obj, obj2);
            log(SELF, Level.FINE, m259a.m249a(), m259a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Object... objArr) {
        if (isLoggable(Level.FINE)) {
            C0888a m260a = C0893f.m260a(str, objArr);
            log(SELF, Level.FINE, m260a.m249a(), m260a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void debug(String str, Throwable th) {
        if (isLoggable(Level.FINE)) {
            log(SELF, Level.FINE, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isInfoEnabled() {
        return isLoggable(Level.INFO);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str) {
        if (isLoggable(Level.INFO)) {
            log(SELF, Level.INFO, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object obj) {
        if (isLoggable(Level.INFO)) {
            C0888a m258a = C0893f.m258a(str, obj);
            log(SELF, Level.INFO, m258a.m249a(), m258a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object obj, Object obj2) {
        if (isLoggable(Level.INFO)) {
            C0888a m259a = C0893f.m259a(str, obj, obj2);
            log(SELF, Level.INFO, m259a.m249a(), m259a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Object... objArr) {
        if (isLoggable(Level.INFO)) {
            C0888a m260a = C0893f.m260a(str, objArr);
            log(SELF, Level.INFO, m260a.m249a(), m260a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void info(String str, Throwable th) {
        if (isLoggable(Level.INFO)) {
            log(SELF, Level.INFO, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isWarnEnabled() {
        return isLoggable(Level.WARNING);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str) {
        if (isLoggable(Level.WARNING)) {
            log(SELF, Level.WARNING, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object obj) {
        if (isLoggable(Level.WARNING)) {
            C0888a m258a = C0893f.m258a(str, obj);
            log(SELF, Level.WARNING, m258a.m249a(), m258a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object obj, Object obj2) {
        if (isLoggable(Level.WARNING)) {
            C0888a m259a = C0893f.m259a(str, obj, obj2);
            log(SELF, Level.WARNING, m259a.m249a(), m259a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Object... objArr) {
        if (isLoggable(Level.WARNING)) {
            C0888a m260a = C0893f.m260a(str, objArr);
            log(SELF, Level.WARNING, m260a.m249a(), m260a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void warn(String str, Throwable th) {
        if (isLoggable(Level.WARNING)) {
            log(SELF, Level.WARNING, str, th);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public boolean isErrorEnabled() {
        return isLoggable(Level.SEVERE);
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str) {
        if (isLoggable(Level.SEVERE)) {
            log(SELF, Level.SEVERE, str, (Throwable) null);
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object obj) {
        if (isLoggable(Level.SEVERE)) {
            C0888a m258a = C0893f.m258a(str, obj);
            log(SELF, Level.SEVERE, m258a.m249a(), m258a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object obj, Object obj2) {
        if (isLoggable(Level.SEVERE)) {
            C0888a m259a = C0893f.m259a(str, obj, obj2);
            log(SELF, Level.SEVERE, m259a.m249a(), m259a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Object... objArr) {
        if (isLoggable(Level.SEVERE)) {
            C0888a m260a = C0893f.m260a(str, objArr);
            log(SELF, Level.SEVERE, m260a.m249a(), m260a.m250b());
        }
    }

    @Override // io.netty.util.internal.logging.InternalLogger
    public void error(String str, Throwable th) {
        if (isLoggable(Level.SEVERE)) {
            log(SELF, Level.SEVERE, str, th);
        }
    }

    private void log(String str, Level level, String str2, Throwable th) {
        LogRecord logRecord = new LogRecord(level, str2);
        logRecord.setLoggerName(name());
        logRecord.setThrown(th);
        if (level == Level.SEVERE) {
            C0879a.m210d(str, str2 + th);
            return;
        }
        if (level == Level.WARNING) {
            C0879a.m209c(str, str2 + th);
            return;
        }
        if (level == Level.FINE || level == Level.FINER || level == Level.FINEST || level == Level.ALL) {
            C0879a.m206a(str, str2 + th);
            return;
        }
        if (level == Level.CONFIG || level == Level.INFO) {
            C0879a.m208b(str, str2 + th);
        }
    }
}
