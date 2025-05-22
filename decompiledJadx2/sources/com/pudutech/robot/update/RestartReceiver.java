package com.pudutech.robot.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RestartReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/update/RestartReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RestartReceiver extends BroadcastReceiver {
    private final String TAG;

    public RestartReceiver() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Pdlog.m3273d(this.TAG, "onReceive");
        if (intent == null || context == null || !Intrinsics.areEqual(intent.getAction(), "android.intent.action.MY_PACKAGE_REPLACED")) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onReceive , need restart app ");
        Pdlog.m3274e(this.TAG, "ac is null , need restart app");
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
    }
}
