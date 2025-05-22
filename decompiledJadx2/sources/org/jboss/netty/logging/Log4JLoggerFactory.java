package org.jboss.netty.logging;

import org.apache.log4j.Logger;

/* loaded from: classes7.dex */
public class Log4JLoggerFactory extends InternalLoggerFactory {
    @Override // org.jboss.netty.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new Log4JLogger(Logger.getLogger(str));
    }
}
