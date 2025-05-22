package com.pudutech.remotemaintenance.handler.aliyun;

import android.util.Pair;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AliyunExecShellCmdMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J$\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002J \u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunExecShellCmdMsgHandler;", "Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunAbstractMsgHandler;", "()V", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "iotInterface", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "execCommand", "Landroid/util/Pair;", "", "", "command", "use_su", "", "stopExecCmd", "waitFor", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunExecShellCmdMsgHandler extends AliyunAbstractMsgHandler {
    public static final String TAG = "AliyunExecShellCmdMsgHandler";
    private static boolean isExecutingCmd;

    @Override // com.pudutech.remotemaintenance.handler.AbstractMsgHandler
    public void action(AliyunIoTMsg msg, AliyunIoTManager iotInterface) {
        JSONObject parseObject;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(iotInterface, "iotInterface");
        boolean z = true;
        Pdlog.m3273d(TAG, "msg[" + msg + ']');
        String sessionId = msg.getSessionId();
        String content = msg.getContent();
        String str = content;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z || (parseObject = JSON.parseObject(content)) == null) {
            return;
        }
        String string = parseObject.getString("cmd");
        Boolean bool = parseObject.getBoolean(IoTConfig.PARAM_IS_SU);
        boolean booleanValue = bool != null ? bool.booleanValue() : false;
        if (string != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1(string, null, this, booleanValue, sessionId, string, iotInterface), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Integer, String> execCommand(String command, boolean use_su) {
        int exitValue;
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
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                try {
                    exitValue = proc.exitValue();
                    break;
                } catch (IllegalThreadStateException unused) {
                    Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
                    InputStream inputStream = proc.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    while (scanner.hasNextLine()) {
                        sb2.append(scanner.nextLine());
                        sb2.append("\n");
                    }
                    inputStream.close();
                    Thread.sleep(100L);
                }
            }
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            if (!waitFor(HardwareConfig.RGBDFwUpdateTimeOut, timeUnit, proc)) {
                Pair<Integer, String> create = Pair.create(-1, "exec command[" + command + "] timeout.");
                Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(-1, \"exec command[$command] timeout.\")");
                return create;
            }
            Pdlog.m3273d(TAG, "su exit value = " + exitValue + ' ' + ((Object) sb2));
            Pair<Integer, String> create2 = Pair.create(Integer.valueOf(exitValue), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create(exitValue, sb.toString())");
            return create2;
        } catch (Throwable th) {
            Pdlog.m3277w(TAG, new Object[]{th});
            Pair<Integer, String> create3 = Pair.create(-2, "exec commond failure.");
            Intrinsics.checkExpressionValueIsNotNull(create3, "Pair.create<Int, String>… \"exec commond failure.\")");
            return create3;
        }
    }

    private final boolean waitFor(long timeout, TimeUnit unit, Process process) throws InterruptedException {
        long nanoTime = System.nanoTime();
        long nanos = unit.toNanos(timeout);
        while (isExecutingCmd) {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(RangesKt.coerceAtMost(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = unit.toNanos(timeout) - (System.nanoTime() - nanoTime);
                if (nanos <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopExecCmd() {
        isExecutingCmd = false;
    }
}
