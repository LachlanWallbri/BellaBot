package com.pudutech.peanut.robot_ui.ui_utils;

import android.os.SystemClock;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingleClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "Landroid/view/View$OnClickListener;", "onSingleClick", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "lastClickTimestamp_ms", "", "getOnSingleClick", "()Lkotlin/jvm/functions/Function0;", "onClick", "v", "Landroid/view/View;", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SingleClickListener implements View.OnClickListener {
    private long lastClickTimestamp_ms;
    private final Function0<Unit> onSingleClick;
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    public SingleClickListener(Function0<Unit> onSingleClick) {
        Intrinsics.checkParameterIsNotNull(onSingleClick, "onSingleClick");
        this.onSingleClick = onSingleClick;
        this.lastClickTimestamp_ms = -1L;
    }

    public final Function0<Unit> getOnSingleClick() {
        return this.onSingleClick;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Pdlog.m3276v(TAG, "onClick v=" + v);
        if (SystemClock.elapsedRealtime() - this.lastClickTimestamp_ms < 600) {
            Pdlog.m3277w(TAG, "click " + v + " too fast in " + (SystemClock.elapsedRealtime() - this.lastClickTimestamp_ms));
        } else {
            this.onSingleClick.invoke();
            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
        }
        this.lastClickTimestamp_ms = SystemClock.elapsedRealtime();
    }
}
