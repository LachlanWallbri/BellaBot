package io.grpc.internal;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ReflectionLongAdderCounter implements LongCounter {
    private static final Method addMethod;
    private static final Constructor<?> defaultConstructor;
    private static final RuntimeException initializationException;
    private static final Logger logger = Logger.getLogger(ReflectionLongAdderCounter.class.getName());
    private static final Object[] one;
    private static final Method sumMethod;
    private final Object instance;

    static {
        Method method;
        Method method2;
        Constructor<?> constructor;
        Class<?> cls;
        try {
            cls = Class.forName("java.util.concurrent.atomic.LongAdder");
            method = cls.getMethod(TmpConstant.GROUP_OP_ADD, Long.TYPE);
            try {
                method2 = cls.getMethod("sum", new Class[0]);
            } catch (Throwable th) {
                th = th;
                method2 = null;
            }
        } catch (Throwable th2) {
            th = th2;
            method = null;
            method2 = null;
        }
        try {
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    constructor = null;
                    break;
                }
                constructor = constructors[i];
                if (constructor.getParameterTypes().length == 0) {
                    break;
                } else {
                    i++;
                }
            }
            th = null;
        } catch (Throwable th3) {
            th = th3;
            logger.log(Level.FINE, "LongAdder can not be found via reflection, this is normal for JDK7 and below", th);
            constructor = null;
            if (th != null) {
            }
            defaultConstructor = null;
            addMethod = null;
            sumMethod = null;
            initializationException = new RuntimeException(th);
            one = new Object[]{1L};
        }
        if (th != null && constructor != null) {
            defaultConstructor = constructor;
            addMethod = method;
            sumMethod = method2;
            initializationException = null;
        } else {
            defaultConstructor = null;
            addMethod = null;
            sumMethod = null;
            initializationException = new RuntimeException(th);
        }
        one = new Object[]{1L};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReflectionLongAdderCounter() {
        RuntimeException runtimeException = initializationException;
        if (runtimeException != null) {
            throw runtimeException;
        }
        try {
            this.instance = defaultConstructor.newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAvailable() {
        return initializationException == null;
    }

    @Override // io.grpc.internal.LongCounter
    public void add(long j) {
        try {
            addMethod.invoke(this.instance, j == 1 ? one : new Object[]{Long.valueOf(j)});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // io.grpc.internal.LongCounter
    public long value() {
        try {
            return ((Long) sumMethod.invoke(this.instance, new Object[0])).longValue();
        } catch (IllegalAccessException unused) {
            throw new RuntimeException();
        } catch (InvocationTargetException unused2) {
            throw new RuntimeException();
        }
    }
}
