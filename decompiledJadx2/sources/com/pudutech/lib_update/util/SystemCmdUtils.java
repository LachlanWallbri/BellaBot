package com.pudutech.lib_update.util;

import android.util.Pair;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemCmdUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J$\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lib_update/util/SystemCmdUtils;", "", "()V", "TAG", "", "Tools", "", "execCommand", "Landroid/util/Pair;", "", "command", "use_su", "", "waitFor", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemCmdUtils {
    public static final SystemCmdUtils INSTANCE = new SystemCmdUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    public final void Tools() {
    }

    private SystemCmdUtils() {
    }

    public final Pair<Integer, Object> execCommand(String command, boolean use_su) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String str = TAG;
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(command);
        sb.append(use_su ? " with su" : " with sh");
        strArr[0] = sb.toString();
        netWorkLog.mo3278d(str, strArr.toString());
        String[] strArr2 = new String[3];
        strArr2[0] = use_su ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr2[1] = "-c";
        strArr2[2] = command;
        try {
            Process proc = Runtime.getRuntime().exec(strArr2);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            waitFor(30L, timeUnit, proc);
            InputStream inputStream = proc.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();
            int exitValue = proc.exitValue();
            NetWorkLog.INSTANCE.mo3278d(TAG, new Object[]{"su exit value = " + exitValue + ' ' + ((Object) sb2)}.toString());
            Pair<Integer, Object> create = Pair.create(Integer.valueOf(exitValue), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(exitValue, sb.toString())");
            return create;
        } catch (Throwable th) {
            NetWorkLog.INSTANCE.mo3281w(TAG, new Object[]{th}.toString());
            Pair<Integer, Object> create2 = Pair.create(1, null);
            Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create(1, null as Any?)");
            return create2;
        }
    }

    public final boolean waitFor(long timeout, TimeUnit unit, Process process) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Intrinsics.checkParameterIsNotNull(process, "process");
        long nanoTime = System.nanoTime();
        long nanos = unit.toNanos(timeout);
        do {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(Math.min(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = unit.toNanos(timeout) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }
}
