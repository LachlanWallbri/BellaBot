package com.pudutech.peanut.robot_ui.manager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.util.finishapp.AppStatusTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RestartReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/RestartReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RestartReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Pdlog.m3273d(this.TAG, "onReceive");
        if (intent == null || context == null || !Intrinsics.areEqual(intent.getAction(), "android.intent.action.MY_PACKAGE_REPLACED")) {
            return;
        }
        Activity topActivity = AppStatusTracker.getTopActivity();
        Pdlog.m3273d(this.TAG, "onReceive , need restart app , top activity is " + topActivity);
        if (topActivity == null) {
            Pdlog.m3274e(this.TAG, "ac is null , need restart app");
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
        }
    }
}
