package com.pudutech.peanut.robot_ui.listener;

import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnLazyItemClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J(\u0010\u000f\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/listener/OnLazyItemClickListener;", "Lcom/chad/library/adapter/base/BaseQuickAdapter$OnItemClickListener;", "()V", "lastPosition", "", "mLastClickTime", "", "timeInterval_ms", "onItemClick", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", RequestParameters.POSITION, "onSingleItemClick", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class OnLazyItemClickListener implements BaseQuickAdapter.OnItemClickListener {
    private long mLastClickTime;
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private final long timeInterval_ms = 300;
    private int lastPosition = -1;

    public abstract void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position);

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Pdlog.m3274e(TAG, "OnLazyItemClickListener----");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > this.timeInterval_ms || this.lastPosition != position) {
            onSingleItemClick(adapter, view, position);
            this.lastPosition = position;
            this.mLastClickTime = currentTimeMillis;
            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
            return;
        }
        Pdlog.m3274e(TAG, "快速重复点击了----");
    }
}
