package androidx.test.internal.util;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.test.internal.util.ProcSummary;

/* loaded from: classes.dex */
public final class LogUtil {
    private static volatile String myProcName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Supplier<T> {
        T get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ String lambda$logDebug$0$LogUtil(String str) {
        return str;
    }

    public static void logDebug(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier(message) { // from class: androidx.test.internal.util.LogUtil$$Lambda$0
            private final String arg$1;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.arg$1 = message;
            }

            @Override // androidx.test.internal.util.LogUtil.Supplier
            public Object get() {
                return LogUtil.lambda$logDebug$0$LogUtil(this.arg$1);
            }
        }, args);
    }

    private static void logDebug(String tag, Supplier<String> msgSupplier, Object... args) {
        if (isLoggable(tag, 3)) {
            Log.d(tag, String.format(msgSupplier.get(), args));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ String lambda$logDebugWithProcess$1$LogUtil(String str) {
        String procName = procName();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(procName).length());
        sb.append(str);
        sb.append(" in ");
        sb.append(procName);
        return sb.toString();
    }

    public static void logDebugWithProcess(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier(message) { // from class: androidx.test.internal.util.LogUtil$$Lambda$1
            private final String arg$1;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.arg$1 = message;
            }

            @Override // androidx.test.internal.util.LogUtil.Supplier
            public Object get() {
                return LogUtil.lambda$logDebugWithProcess$1$LogUtil(this.arg$1);
            }
        }, args);
    }

    private static final String procName() {
        String str;
        String str2 = myProcName;
        if (str2 != null) {
            return str2;
        }
        try {
            str = ProcSummary.summarize("self").cmdline;
        } catch (ProcSummary.SummaryException unused) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return (str.length() <= 64 || !str.contains("-classpath")) ? str : "robolectric";
    }

    private static boolean isLoggable(String tag, final int level) {
        if (tag.length() > 23) {
            tag = tag.substring(0, 22);
        }
        return Log.isLoggable(tag, level);
    }
}
