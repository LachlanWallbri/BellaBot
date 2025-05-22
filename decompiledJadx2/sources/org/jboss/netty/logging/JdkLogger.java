package org.jboss.netty.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes7.dex */
class JdkLogger extends AbstractInternalLogger {
    private final Logger logger;
    private final String loggerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkLogger(Logger logger, String str) {
        this.logger = logger;
        this.loggerName = str;
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str) {
        this.logger.logp(Level.FINE, this.loggerName, (String) null, str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void debug(String str, Throwable th) {
        this.logger.logp(Level.FINE, this.loggerName, (String) null, str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str) {
        this.logger.logp(Level.SEVERE, this.loggerName, (String) null, str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void error(String str, Throwable th) {
        this.logger.logp(Level.SEVERE, this.loggerName, (String) null, str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str) {
        this.logger.logp(Level.INFO, this.loggerName, (String) null, str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void info(String str, Throwable th) {
        this.logger.logp(Level.INFO, this.loggerName, (String) null, str, th);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isErrorEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str) {
        this.logger.logp(Level.WARNING, this.loggerName, (String) null, str);
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void warn(String str, Throwable th) {
        this.logger.logp(Level.WARNING, this.loggerName, (String) null, str, th);
    }

    public String toString() {
        return this.loggerName;
    }
}
