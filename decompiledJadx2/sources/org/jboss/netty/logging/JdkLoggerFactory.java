package org.jboss.netty.logging;

import java.util.logging.Logger;

/* loaded from: classes7.dex */
public class JdkLoggerFactory extends InternalLoggerFactory {
    @Override // org.jboss.netty.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new JdkLogger(Logger.getLogger(str), str);
    }
}
