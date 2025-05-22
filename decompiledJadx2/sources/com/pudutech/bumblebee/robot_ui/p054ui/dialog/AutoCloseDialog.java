package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoCloseDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0004J\b\u0010\u0019\u001a\u00020\u0018H\u0016R\u0014\u0010\b\u001a\u00020\tX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/AutoCloseDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "closeTime", "", "getCloseTime", "()J", "mHandler", "Landroid/os/Handler;", "runnable", "Ljava/lang/Runnable;", "dispatchKeyEvent", "", "event", "Landroid/view/KeyEvent;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "resetAutoClose", "", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class AutoCloseDialog extends Dialog {
    private final long closeTime;
    private final Handler mHandler;
    private final Runnable runnable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoCloseDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.closeTime = 10000L;
        this.mHandler = new Handler();
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.AutoCloseDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                AutoCloseDialog.this.dismiss();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoCloseDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.closeTime = 10000L;
        this.mHandler = new Handler();
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.AutoCloseDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                AutoCloseDialog.this.dismiss();
            }
        };
    }

    protected long getCloseTime() {
        return this.closeTime;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return super.dispatchKeyEvent(event);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkParameterIsNotNull(ev, "ev");
        resetAutoClose();
        return super.dispatchTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void resetAutoClose() {
        this.mHandler.removeCallbacks(this.runnable);
        this.mHandler.postDelayed(this.runnable, getCloseTime());
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        resetAutoClose();
    }
}
