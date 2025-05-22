package org.jboss.netty.logging;

import org.apache.log4j.Logger;

/* loaded from: classes7.dex */
class Log4JLogger extends AbstractInternalLogger {
    private final Logger logger;

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isWarnEnabled() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Log4JLogger(Logger logger) {
        this.logger = logger;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str) {
        this.logger.debug(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str, Throwable th) {
        this.logger.debug(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str) {
        this.logger.error(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str, Throwable th) {
        this.logger.error(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str) {
        this.logger.info(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str, Throwable th) {
        this.logger.info(str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str) {
        this.logger.warn(str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str, Throwable th) {
        this.logger.warn(str, th);
    }

    public String toString() {
        return String.valueOf(this.logger.getName());
    }
}
