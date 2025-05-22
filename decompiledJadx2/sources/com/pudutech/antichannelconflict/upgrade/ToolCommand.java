package com.pudutech.antichannelconflict.upgrade;

import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: ToolCommand.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\fJ8\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\fJ \u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/ToolCommand;", "", "()V", "TAG", "", "execCommand", "Landroid/util/Pair;", "", "command", "use_su", "", "callback", "Lkotlin/Function1;", "", "execCommandForCat", "waitFor", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ToolCommand {
    public static final ToolCommand INSTANCE = new ToolCommand();
    public static final String TAG = "ToolCommand";

    private ToolCommand() {
    }

    public final Pair<Integer, String> execCommandForCat(String command, boolean use_su, Function1<? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(command);
        sb.append(use_su ? " with su" : " with sh");
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        String[] strArr = new String[3];
        strArr[0] = use_su ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = command;
        Process process = (Process) null;
        InputStream inputStream = (InputStream) null;
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            InputStream inputStream2 = proc.getInputStream();
            Scanner scanner = new Scanner(inputStream2);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null) {
                    if (nextLine != null) {
                        String obj = StringsKt.trim((CharSequence) nextLine).toString();
                        if (obj != null) {
                            sb2.append(obj);
                            sb2.append("\n");
                            if (StringsKt.startsWith$default(obj, "mcc:", false, 2, (Object) null)) {
                                Pdlog.m3273d(TAG, "result contain(mcc:) :", obj);
                                if (callback != null) {
                                    callback.invoke(obj);
                                }
                                if (proc != null) {
                                    proc.destroy();
                                }
                            }
                            if (StringsKt.startsWith$default(obj, "Revision:", false, 2, (Object) null)) {
                                Pdlog.m3273d(TAG, "result contain(Revision:) :", obj);
                                if (callback != null) {
                                    String sb3 = sb2.toString();
                                    Intrinsics.checkExpressionValueIsNotNull(sb3, "sb.toString()");
                                    callback.invoke(sb3);
                                }
                                if (proc != null) {
                                    proc.destroy();
                                }
                            }
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                }
            }
            Pair<Integer, String> create = Pair.create(Integer.valueOf(proc.exitValue()), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(exitValue, sb.toString())");
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (proc != null) {
                proc.destroy();
            }
            return create;
        } catch (Throwable th) {
            try {
                Pdlog.m3277w(TAG, command + ":" + th.getMessage());
                Pair<Integer, String> create2 = Pair.create(-1, "exec commond failure.");
                Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create<Int, String>… \"exec commond failure.\")");
                return create2;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (process != null) {
                    process.destroy();
                }
            }
        }
    }

    public final Pair<Integer, String> execCommand(String command, boolean use_su, Function1<? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(command);
        sb.append(use_su ? " with su" : " with sh");
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        String[] strArr = new String[3];
        strArr[0] = use_su ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = command;
        InputStream inputStream = (InputStream) null;
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            inputStream = proc.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null) {
                    sb2.append(nextLine);
                    sb2.append("\n");
                    if (callback != null) {
                        callback.invoke(nextLine);
                    }
                    Pdlog.m3273d(TAG, "callback:", nextLine);
                }
            }
            inputStream.close();
            int exitValue = proc.exitValue();
            Pdlog.m3273d(TAG, "su exit value = " + exitValue + ' ' + ((Object) sb2));
            Pair<Integer, String> create = Pair.create(Integer.valueOf(exitValue), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(exitValue, sb.toString())");
            return create;
        } catch (Throwable th) {
            try {
                Pdlog.m3277w(TAG, command + ":" + th.getMessage());
                Pair<Integer, String> create2 = Pair.create(-1, "exec commond failure.");
                Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create<Int, String>… \"exec commond failure.\")");
                if (inputStream != null) {
                    inputStream.close();
                }
                return create2;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
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
