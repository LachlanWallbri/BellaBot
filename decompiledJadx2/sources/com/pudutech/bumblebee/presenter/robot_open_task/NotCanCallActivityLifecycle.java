package com.pudutech.bumblebee.presenter.robot_open_task;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* compiled from: RobotOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\u0010\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u0013\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/NotCanCallActivityLifecycle;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "TAG", "", "checkNoCall", "", "activity", "Landroid/app/Activity;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NotCanCallActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int isNoCanCall;
    private final String TAG = "NotCanCallActivityLifecycle";

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    /* compiled from: RobotOpenManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/NotCanCallActivityLifecycle$Companion;", "", "()V", "isNoCanCall", "", "isNoCanCall$module_bumblebee_presenter_robotRelease", "()I", "setNoCanCall$module_bumblebee_presenter_robotRelease", "(I)V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int isNoCanCall$module_bumblebee_presenter_robotRelease() {
            return NotCanCallActivityLifecycle.isNoCanCall;
        }

        public final void setNoCanCall$module_bumblebee_presenter_robotRelease(int i) {
            NotCanCallActivityLifecycle.isNoCanCall = i;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (checkNoCall(activity)) {
            isNoCanCall++;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (checkNoCall(activity)) {
            isNoCanCall--;
        }
    }

    private final boolean checkNoCall(Activity activity) {
        String localClassName;
        if (activity == null || (localClassName = activity.getLocalClassName()) == null) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "checkNoCall: " + localClassName);
        String str = localClassName;
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "mpmodule.HomeActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "activity.MirSDKActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "greeter.GreeterFaceActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "greeter.GreeterMenuActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "SelfCheckActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "LaserRunningLocationLostActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "LaserCheckLocationActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "CheckLocationActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "MapSelectActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "SayHiAnimationActivity", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "DropCheckActivity", false, 2, (Object) null);
    }
}
