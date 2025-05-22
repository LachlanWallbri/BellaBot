package org.jboss.netty.logging;

import org.apache.commons.logging.Log;

/* loaded from: classes7.dex */
class CommonsLogger extends AbstractInternalLogger {
    private final Log logger;
    private final String loggerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommonsLogger(Log log, String str) {
        this.logger = log;
        this.loggerName = str;
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
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
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
        return this.loggerName;
    }
}
