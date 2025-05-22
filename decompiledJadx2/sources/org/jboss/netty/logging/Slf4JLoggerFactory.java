package org.jboss.netty.logging;

import org.slf4j.LoggerFactory;

/* loaded from: classes7.dex */
public class Slf4JLoggerFactory extends InternalLoggerFactory {
    @Override // org.jboss.netty.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new Slf4JLogger(LoggerFactory.getLogger(str));
    }
}
