package com.pudutech.peanut.robot_ui.listener;

import android.view.View;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnLazyClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "Landroid/view/View$OnClickListener;", "()V", "lastViewId", "", "mLastClickTime", "", "timeInterval_ms", "onClick", "", "v", "Landroid/view/View;", "onSingleClick", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class OnLazyClickListener implements View.OnClickListener {
    private long mLastClickTime;
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private final long timeInterval_ms = 600;
    private int lastViewId = -1;

    public abstract void onSingleClick();

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > this.timeInterval_ms || this.lastViewId != v.getId()) {
            onSingleClick();
            this.mLastClickTime = currentTimeMillis;
            this.lastViewId = v.getId();
            return;
        }
        Pdlog.m3274e(TAG, "快速重复点击了----");
    }
}
