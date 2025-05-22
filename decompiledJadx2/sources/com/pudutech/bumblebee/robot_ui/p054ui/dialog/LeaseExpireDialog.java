package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: LeaseExpireDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LeaseExpireDialog;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onForceClose", "Lkotlin/Function0;", "", "getOnForceClose", "()Lkotlin/jvm/functions/Function0;", "setOnForceClose", "(Lkotlin/jvm/functions/Function0;)V", "touchJob", "Lkotlinx/coroutines/Job;", "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LeaseExpireDialog extends ShowTipMsgDialog {
    private Function0<Unit> onForceClose;
    private Job touchJob;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LeaseExpireDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function0<Unit> getOnForceClose() {
        return this.onForceClose;
    }

    public final void setOnForceClose(Function0<Unit> function0) {
        this.onForceClose = function0;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanCancel(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
    
        if (r0 != 6) goto L13;
     */
    @Override // android.app.Dialog, android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(ev, "ev");
        int actionMasked = ev.getActionMasked();
        if (actionMasked != 3) {
            if (actionMasked == 5) {
                if (this.touchJob == null) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LeaseExpireDialog$dispatchTouchEvent$1(this, null), 3, null);
                    this.touchJob = launch$default;
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LeaseExpireDialog$dispatchTouchEvent$2(this, null), 3, null);
        return super.dispatchTouchEvent(ev);
    }
}
