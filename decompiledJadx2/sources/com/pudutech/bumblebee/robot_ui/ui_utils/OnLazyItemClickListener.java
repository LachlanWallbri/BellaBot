package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: OnLazyItemClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B%\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J(\u0010\u0018\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007H&R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyItemClickListener;", "Lcom/chad/library/adapter/base/BaseQuickAdapter$OnItemClickListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "(Ljava/util/Map;I)V", "lastPosition", "mLastClickTime", "", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "timeInterval_ms", "onItemClick", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", RequestParameters.POSITION, "onSingleItemClick", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class OnLazyItemClickListener implements BaseQuickAdapter.OnItemClickListener {
    private int lastPosition;
    private long mLastClickTime;
    private final Map<String, Object> param;
    private final int priority;
    private final long timeInterval_ms;
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    public OnLazyItemClickListener() {
        this(null, 0, 3, 0 == true ? 1 : 0);
    }

    public abstract void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position);

    public OnLazyItemClickListener(Map<String, ? extends Object> param, int i) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.param = param;
        this.priority = i;
        this.timeInterval_ms = 300L;
        this.lastPosition = -1;
    }

    public /* synthetic */ OnLazyItemClickListener(Map map, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Pdlog.m3273d(TAG, "OnLazyItemClickListener----");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > this.timeInterval_ms || this.lastPosition != position) {
            onSingleItemClick(adapter, view, position);
            this.lastPosition = position;
            this.mLastClickTime = currentTimeMillis;
            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
            try {
                ClickReport clickReport = ClickReport.INSTANCE;
                ViewParent parent = view.getParent();
                if (parent == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                clickReport.onClickEvent((ViewGroup) parent, MapsKt.mapOf(TuplesKt.m3968to(TrackKey.RECYCLERVIEW_POSITION, Integer.valueOf(position))), 0);
                return;
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "onItemClick:" + e.getMessage() + ' ');
                ClickReport.INSTANCE.onClickEvent(view, MapsKt.mapOf(TuplesKt.m3968to(TrackKey.RECYCLERVIEW_POSITION, Integer.valueOf(position))), 0);
                return;
            }
        }
        Pdlog.m3274e(TAG, "快速重复点击了----");
    }
}
