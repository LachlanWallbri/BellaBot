package org.jboss.netty.logging;

import org.apache.commons.logging.LogFactory;

/* loaded from: classes7.dex */
public class CommonsLoggerFactory extends InternalLoggerFactory {
    @Override // org.jboss.netty.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new CommonsLogger(LogFactory.getLog(str), str);
    }
}
