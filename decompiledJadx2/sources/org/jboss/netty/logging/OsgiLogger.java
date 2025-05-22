package org.jboss.netty.logging;

import org.osgi.service.log.LogService;

/* loaded from: classes7.dex */
class OsgiLogger extends AbstractInternalLogger {
    private final InternalLogger fallback;
    private final String name;
    private final OsgiLoggerFactory parent;
    private final String prefix;

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isWarnEnabled() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OsgiLogger(OsgiLoggerFactory osgiLoggerFactory, String str, InternalLogger internalLogger) {
        this.parent = osgiLoggerFactory;
        this.name = str;
        this.fallback = internalLogger;
        this.prefix = "[" + str + "] ";
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(4, this.prefix + str);
            return;
        }
        this.fallback.debug(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str, Throwable th) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(4, this.prefix + str, th);
            return;
        }
        this.fallback.debug(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(1, this.prefix + str);
            return;
        }
        this.fallback.error(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str, Throwable th) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(1, this.prefix + str, th);
            return;
        }
        this.fallback.error(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(3, this.prefix + str);
            return;
        }
        this.fallback.info(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str, Throwable th) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(3, this.prefix + str, th);
            return;
        }
        this.fallback.info(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(2, this.prefix + str);
            return;
        }
        this.fallback.warn(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str, Throwable th) {
        LogService logService = this.parent.getLogService();
        if (logService != null) {
            logService.log(2, this.prefix + str, th);
            return;
        }
        this.fallback.warn(str, th);
    }

    public String toString() {
        return this.name;
    }
}
