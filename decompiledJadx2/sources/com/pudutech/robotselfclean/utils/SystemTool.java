package com.pudutech.robotselfclean.utils;

import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: SystemTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robotselfclean/utils/SystemTool;", "", "()V", "TAG", "", "execCommand", "Landroid/util/Pair;", "", "command", "use_su", "", "waitFor", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SystemTool {
    public static final SystemTool INSTANCE = new SystemTool();
    public static final String TAG = "SystemTool";

    private SystemTool() {
    }

    public final Pair<Integer, String> execCommand(String command, boolean use_su) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(command);
        sb.append(use_su ? " with su" : " with sh");
        objArr[0] = sb.toString();
        Pdlog.m3273d("SystemTool", objArr);
        String[] strArr = new String[3];
        strArr[0] = use_su ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = command;
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            if (!waitFor(30L, timeUnit, proc)) {
                Pair<Integer, String> create = Pair.create(-1, "exec commond timeout.");
                Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(-1, \"exec commond timeout.\")");
                return create;
            }
            InputStream inputStream = proc.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();
            int exitValue = proc.exitValue();
            Pdlog.m3273d("SystemTool", "su exit value = " + exitValue + ' ' + ((Object) sb2));
            Pair<Integer, String> create2 = Pair.create(Integer.valueOf(exitValue), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create(exitValue, sb.toString())");
            return create2;
        } catch (Throwable th) {
            Pdlog.m3277w("SystemTool", new Object[]{th});
            Pair<Integer, String> create3 = Pair.create(-1, "exec commond failure.");
            Intrinsics.checkExpressionValueIsNotNull(create3, "Pair.create<Int, String>… \"exec commond failure.\")");
            return create3;
        }
    }

    private final boolean waitFor(long timeout, TimeUnit unit, Process process) throws InterruptedException {
        long nanoTime = System.nanoTime();
        long nanos = unit.toNanos(timeout);
        do {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(RangesKt.coerceAtMost(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = unit.toNanos(timeout) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }
}
