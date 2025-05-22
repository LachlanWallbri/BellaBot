package org.jboss.netty.logging;

/* loaded from: classes7.dex */
public abstract class AbstractInternalLogger implements InternalLogger {

    /* renamed from: org.jboss.netty.logging.AbstractInternalLogger$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87331 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$logging$InternalLogLevel = new int[InternalLogLevel.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$logging$InternalLogLevel[InternalLogLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$logging$InternalLogLevel[InternalLogLevel.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$logging$InternalLogLevel[InternalLogLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$logging$InternalLogLevel[InternalLogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        int i = C87331.$SwitchMap$org$jboss$netty$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            return isDebugEnabled();
        }
        if (i == 2) {
            return isInfoEnabled();
        }
        if (i == 3) {
            return isWarnEnabled();
        }
        if (i == 4) {
            return isErrorEnabled();
        }
        throw new Error();
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        int i = C87331.$SwitchMap$org$jboss$netty$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            debug(str, th);
            return;
        }
        if (i == 2) {
            info(str, th);
        } else if (i == 3) {
            warn(str, th);
        } else {
            if (i == 4) {
                error(str, th);
                return;
            }
            throw new Error();
        }
    }

    @Override // org.jboss.netty.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str) {
        int i = C87331.$SwitchMap$org$jboss$netty$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            debug(str);
            return;
        }
        if (i == 2) {
            info(str);
        } else if (i == 3) {
            warn(str);
        } else {
            if (i == 4) {
                error(str);
                return;
            }
            throw new Error();
        }
    }
}
