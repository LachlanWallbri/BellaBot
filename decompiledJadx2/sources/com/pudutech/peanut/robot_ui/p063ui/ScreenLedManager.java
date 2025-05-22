package com.pudutech.peanut.robot_ui.p063ui;

import android.os.Handler;
import android.os.HandlerThread;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/ScreenLedManager;", "", "()V", "TAG", "", "screenHandler", "Landroid/os/Handler;", "thread", "Landroid/os/HandlerThread;", "init", "", "startScreen", "stopScreen", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ScreenLedManager {
    private static Handler screenHandler;
    public static final ScreenLedManager INSTANCE = new ScreenLedManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final HandlerThread thread = new HandlerThread("RobotLedScreen");

    private ScreenLedManager() {
    }

    public final void init() {
        Pdlog.m3273d(TAG, "init");
        if (!thread.isAlive()) {
            thread.start();
        }
        screenHandler = new Handler(thread.getLooper());
    }

    public final void startScreen() {
        Pdlog.m3274e(TAG, "startScreen");
        Handler handler = screenHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenHandler");
        }
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = screenHandler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenHandler");
        }
        handler2.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.ScreenLedManager$startScreen$1
            @Override // java.lang.Runnable
            public final void run() {
                Tools.execCommand("ledon", true);
            }
        });
    }

    public final void stopScreen() {
        Pdlog.m3274e(TAG, "stopScreen");
        Handler handler = screenHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenHandler");
        }
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = screenHandler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenHandler");
        }
        handler2.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.ScreenLedManager$stopScreen$1
            @Override // java.lang.Runnable
            public final void run() {
                Tools.execCommand("ledoff", true);
            }
        });
    }
}
