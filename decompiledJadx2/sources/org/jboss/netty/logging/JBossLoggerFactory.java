package org.jboss.netty.logging;

import org.jboss.logging.Logger;

/* loaded from: classes7.dex */
public class JBossLoggerFactory extends InternalLoggerFactory {
    @Override // org.jboss.netty.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new JBossLogger(Logger.getLogger(str));
    }
}
