package io.grpc.netty.shaded.io.netty.util.internal.logging;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.ObjectStreamException;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractInternalLogger implements InternalLogger, Serializable {
    static final String EXCEPTION_MESSAGE = "Unexpected exception:";
    private static final long serialVersionUID = -6382972526573193470L;
    private final String name;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractInternalLogger(String str) {
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public String name() {
        return this.name;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.util.internal.logging.AbstractInternalLogger$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C68061 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$internal$logging$InternalLogLevel = new int[InternalLogLevel.values().length];

        static {
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.WARN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$util$internal$logging$InternalLogLevel[InternalLogLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public boolean isEnabled(InternalLogLevel internalLogLevel) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            return isTraceEnabled();
        }
        if (i == 2) {
            return isDebugEnabled();
        }
        if (i == 3) {
            return isInfoEnabled();
        }
        if (i == 4) {
            return isWarnEnabled();
        }
        if (i == 5) {
            return isErrorEnabled();
        }
        throw new Error();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void trace(Throwable th) {
        trace(EXCEPTION_MESSAGE, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void debug(Throwable th) {
        debug(EXCEPTION_MESSAGE, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void info(Throwable th) {
        info(EXCEPTION_MESSAGE, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void warn(Throwable th) {
        warn(EXCEPTION_MESSAGE, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void error(Throwable th) {
        error(EXCEPTION_MESSAGE, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Throwable th) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, th);
            return;
        }
        if (i == 2) {
            debug(str, th);
            return;
        }
        if (i == 3) {
            info(str, th);
        } else if (i == 4) {
            warn(str, th);
        } else {
            if (i == 5) {
                error(str, th);
                return;
            }
            throw new Error();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, Throwable th) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(th);
            return;
        }
        if (i == 2) {
            debug(th);
            return;
        }
        if (i == 3) {
            info(th);
        } else if (i == 4) {
            warn(th);
        } else {
            if (i == 5) {
                error(th);
                return;
            }
            throw new Error();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str);
            return;
        }
        if (i == 2) {
            debug(str);
            return;
        }
        if (i == 3) {
            info(str);
        } else if (i == 4) {
            warn(str);
        } else {
            if (i == 5) {
                error(str);
                return;
            }
            throw new Error();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, obj);
            return;
        }
        if (i == 2) {
            debug(str, obj);
            return;
        }
        if (i == 3) {
            info(str, obj);
        } else if (i == 4) {
            warn(str, obj);
        } else {
            if (i == 5) {
                error(str, obj);
                return;
            }
            throw new Error();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object obj, Object obj2) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, obj, obj2);
            return;
        }
        if (i == 2) {
            debug(str, obj, obj2);
            return;
        }
        if (i == 3) {
            info(str, obj, obj2);
        } else if (i == 4) {
            warn(str, obj, obj2);
        } else {
            if (i == 5) {
                error(str, obj, obj2);
                return;
            }
            throw new Error();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger
    public void log(InternalLogLevel internalLogLevel, String str, Object... objArr) {
        int i = C68061.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[internalLogLevel.ordinal()];
        if (i == 1) {
            trace(str, objArr);
            return;
        }
        if (i == 2) {
            debug(str, objArr);
            return;
        }
        if (i == 3) {
            info(str, objArr);
        } else if (i == 4) {
            warn(str, objArr);
        } else {
            if (i == 5) {
                error(str, objArr);
                return;
            }
            throw new Error();
        }
    }

    protected Object readResolve() throws ObjectStreamException {
        return InternalLoggerFactory.getInstance(name());
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + '(' + name() + ')';
    }
}
