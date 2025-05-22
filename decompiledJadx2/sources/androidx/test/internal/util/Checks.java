package androidx.test.internal.util;

import android.os.Looper;
import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.ThreadChecker;
import java.util.List;

/* loaded from: classes.dex */
public final class Checks {
    private static final ThreadChecker THREAD_CHECKER;

    private Checks() {
    }

    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkMainThread() {
        THREAD_CHECKER.checkMainThread();
    }

    public static void checkNotMainThread() {
        THREAD_CHECKER.checkNotMainThread();
    }

    static {
        List loadService = ServiceLoaderWrapper.loadService(ThreadChecker.class);
        if (loadService.isEmpty()) {
            THREAD_CHECKER = new ThreadChecker() { // from class: androidx.test.internal.util.Checks.1
                @Override // androidx.test.internal.platform.ThreadChecker
                public void checkMainThread() {
                    Checks.checkState(Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called off the main application thread (on: %s)", Thread.currentThread().getName());
                }

                @Override // androidx.test.internal.platform.ThreadChecker
                public void checkNotMainThread() {
                    Checks.checkState(!Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called on the main application thread (on: %s)", Thread.currentThread().getName());
                }
            };
        } else {
            if (loadService.size() == 1) {
                THREAD_CHECKER = (ThreadChecker) loadService.get(0);
                return;
            }
            throw new IllegalStateException(String.format("Found more than one %s implementations.", ThreadChecker.class.getName()));
        }
    }

    private static String format(String template, Object... args) {
        int indexOf;
        String valueOf = String.valueOf(template);
        StringBuilder sb = new StringBuilder(valueOf.length() + (args.length * 16));
        int i = 0;
        int i2 = 0;
        while (i < args.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append(valueOf.substring(i2, indexOf));
            sb.append(args[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append(valueOf.substring(i2));
        if (i < args.length) {
            sb.append(" [");
            sb.append(args[i]);
            for (int i3 = i + 1; i3 < args.length; i3++) {
                sb.append(", ");
                sb.append(args[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
