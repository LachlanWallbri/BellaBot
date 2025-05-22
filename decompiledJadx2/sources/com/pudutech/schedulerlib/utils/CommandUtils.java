package com.pudutech.schedulerlib.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.DataOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: CommandUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/CommandUtils;", "", "()V", "TAG", "", "executeCommand", "", "command", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CommandUtils {
    public static final CommandUtils INSTANCE = new CommandUtils();
    private static final String TAG = "CommandUtils";

    private CommandUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0090 A[Catch: Exception -> 0x0096, TryCatch #4 {Exception -> 0x0096, blocks: (B:35:0x008b, B:29:0x0090, B:30:0x0093), top: B:34:0x008b }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean executeCommand(String command) {
        DataOutputStream dataOutputStream;
        Exception e;
        Intrinsics.checkParameterIsNotNull(command, "command");
        Process process = (Process) null;
        DataOutputStream dataOutputStream2 = (DataOutputStream) null;
        try {
            process = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
            Intrinsics.checkExpressionValueIsNotNull(process, "process");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
        } catch (Exception e2) {
            dataOutputStream = dataOutputStream2;
            e = e2;
        } catch (Throwable th) {
            th = th;
            if (dataOutputStream2 != null) {
            }
            if (process == null) {
            }
            process.destroy();
            throw th;
        }
        try {
            try {
                dataOutputStream.writeBytes(command + '\n');
                dataOutputStream.writeBytes(ShellUtils.COMMAND_EXIT);
                dataOutputStream.flush();
                process.waitFor();
                try {
                    dataOutputStream.close();
                    process.destroy();
                } catch (Exception unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = dataOutputStream;
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (Exception unused2) {
                        throw th;
                    }
                }
                if (process == null) {
                    Intrinsics.throwNpe();
                }
                process.destroy();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Pdlog.m3273d(TAG, "RootCommand failure command=" + command + " error:" + e.getMessage());
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (Exception unused3) {
                    return false;
                }
            }
            if (process == null) {
                Intrinsics.throwNpe();
            }
            process.destroy();
            return false;
        }
    }
}
