package io.netty.util.internal.logging;

import org.apache.commons.logging.LogFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
@Deprecated
/* loaded from: classes.dex */
public class CommonsLoggerFactory extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new CommonsLoggerFactory();

    @Deprecated
    public CommonsLoggerFactory() {
    }

    @Override // io.netty.util.internal.logging.InternalLoggerFactory
    public InternalLogger newInstance(String str) {
        return new CommonsLogger(LogFactory.getLog(str), str);
    }
}
