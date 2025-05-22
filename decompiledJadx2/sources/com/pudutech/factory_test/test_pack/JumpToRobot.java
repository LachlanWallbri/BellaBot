package com.pudutech.factory_test.test_pack;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.BuildConfig;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JumpToRobot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/JumpToRobot;", "", "()V", "TAG", "", "killFactoryTest", "", "context", "Landroid/content/Context;", "killMirsdk", "launchRobotApp", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class JumpToRobot {
    public static final JumpToRobot INSTANCE = new JumpToRobot();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private JumpToRobot() {
    }

    public final void launchRobotApp(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Iterator it = CollectionsKt.arrayListOf("com.pudutech.pdrobot", BuildConfig.APPLICATION_ID, "com.pudutech.recycle.robot", "com.pudutech.robot.ninetails", "com.pudutech.robot.peanut").iterator();
            while (it.hasNext()) {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage((String) it.next());
                if (launchIntentForPackage != null) {
                    killMirsdk(context);
                    context.startActivity(launchIntentForPackage);
                    return;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, String.valueOf(e));
        }
    }

    public final void killMirsdk(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            Pdlog.m3273d(TAG, "runningAppProcessInfo = " + runningAppProcessInfo.processName);
            if (Intrinsics.areEqual("com.pudutech.mirsdk", runningAppProcessInfo.processName)) {
                Pdlog.m3273d(TAG, "kill = " + runningAppProcessInfo.processName + " ; pid = " + runningAppProcessInfo.pid);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    public final void killFactoryTest(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            Pdlog.m3273d(TAG, "runningAppProcessInfo = " + runningAppProcessInfo.processName);
            if (Intrinsics.areEqual("com.pudutech.factory_test", runningAppProcessInfo.processName)) {
                Pdlog.m3273d(TAG, "kill = " + runningAppProcessInfo.processName + " ; pid = " + runningAppProcessInfo.pid);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }
}
